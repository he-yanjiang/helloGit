package com.sst.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sst.bean.UserDO;
import com.sst.bean.UserPasswordDO;
import com.sst.error.BusinessException;
import com.sst.error.EmBusinessError;
import com.sst.mapper.UserDOMapper;
import com.sst.mapper.UserPasswordDOMapper;
import com.sst.service.IUserService;
import com.sst.service.model.UserModel;
import com.sst.validator.ValidationResult;
import com.sst.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String loginByPassword(String telephone, String encrptPassword) throws BusinessException {
        UserDO userDO = this.getUserByPhone(telephone);
        this.checkPassword(userDO,encrptPassword);
        return getToken(telephone,userDO);
    }


    private void checkPassword(UserDO userDO,String encrptPassword) throws BusinessException {
        UserPasswordDO userPasswordDO = userPasswordDOMapper.getPasswordByUserId(userDO.getId());
        if (!StringUtils.equals(userPasswordDO.getEncrptPassword(),encrptPassword)){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        if (convertUserModelFromObject(userDO,userPasswordDO) == null){
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR);
        }
    }

    @Override
    public String loginByCode( String telephone, String code) throws BusinessException {
        //验证用户是否注册
        UserDO userDO = this.getUserByPhone(telephone);
        //获取redis中的otpCode
        String otpCode = this.getRedisOtp(telephone);
        System.out.println(otpCode+"redis中的缓存");
        System.out.println(code+"收到的code");
        if (!StringUtils.equals(code,otpCode)){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL,"验证码错误");
        }
        return getToken(telephone,userDO);
    }

    //通过手机号获取用户信息
    private UserDO getUserByPhone(String telephone) throws BusinessException {
        UserDO userDO = userDOMapper.getUserByPhone(telephone);
        if (userDO == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return userDO;
    }

    @Override
    //获取Token并把user放入缓存中
    public String getToken(String telephone,UserDO userDO){
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, userDO, Duration.ofMinutes(50L));
        return token;
    }

    //通过token获取缓存中的user
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Override
    public UserDO getUserByToken(String token) throws BusinessException {
        UserDO userDO = (UserDO) redisTemplate.opsForValue().get(token);

        if (userDO==null){
            throw new BusinessException(EmBusinessError.USER_INFORMATION_FAILURE);
        }else {
            return userDO;
        }

    }

    //添加用户方法
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insterUser(UserModel userModel) throws BusinessException {
        if (userModel.getTelphone().length()<1||userModel.getEncrptPassword().length()<1){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = new UserDO();
        userDO.setTelphone(userModel.getTelphone());
        try {
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已注册");
        }
        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setUserId(userModel.getId());
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    private UserPasswordDO getPasswordByUserId(int userId){
        return userPasswordDOMapper.getPasswordByUserId(userId);
    }

    //将userDO、userPassword转换为UserModel对象
    private UserModel convertUserModelFromObject(UserDO userDO,UserPasswordDO userPasswordDO){
        if (userDO==null||userPasswordDO==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        return userModel;
    }

    //生成otpCode并发送给用户
    @Override
    public void getOtp(String telephone) throws BusinessException {
        //按照一定规则生成验证码
        String otpCode = getRedisOtp(telephone);
        //如果redis中有，则短信验证码已发送并且有效，直接返回验证码已发送
        if (!StringUtils.isEmpty(otpCode)){
            System.out.println(otpCode);
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,"验证码已发送");
        }
        //如果redis中没有查到验证码，直接生成6位数验证码，此code和短信模板中的变量${code}保持一致
        otpCode = this.getCode();
        //将OTP验证码通过短信通道发送给用户
        //Boolean validation = this.sendMessage(telephone,otpCode);
        System.out.println("短信验证码为："+otpCode);
        Boolean validation = true;
        if (validation){
            redisTemplate.opsForValue().set(telephone, otpCode, 300, TimeUnit.SECONDS);
        }else {
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,"短信发送失败");
        }
    }

    //获取缓存中的otpCode
    @Override
    public String getRedisOtp(String telephone){
        String otpCode ;
        try {
            otpCode =  redisTemplate.opsForValue().get(telephone).toString();
        }catch (Exception exception){
            otpCode = null;
        }
        return otpCode;
    }

    //发送短信实现
    private Boolean sendMessage(String telephone,String otpCode){
        String accessKeyId="LTAI4GFxKBzDD1ekKtUCRzKR";
        String accessSecret="4VNXmjADJLlTZwx0zjAUWVLKNFGd0H";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",telephone );
        request.putQueryParameter("SignName", "防制商城");
        request.putQueryParameter("TemplateCode", "SMS_205125041");
        request.putQueryParameter("TemplateParam", "{\"code\":"+otpCode+"}");

        try {
            CommonResponse response = client.getCommonResponse(request);

            System.out.println(response.getData());
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    //按照一定规则生成Code
    private String getCode(){
        int randomInt = (int) ((Math.random() * 9 + 1) * 100000);
        String otpCode = String.valueOf(randomInt);
        return otpCode;
    }



}
