package com.sst.controller;

import com.alibaba.druid.util.StringUtils;
import com.sst.error.BusinessException;
import com.sst.error.EmBusinessError;
import com.sst.response.CommonReturnType;
import com.sst.service.IUserService;
import com.sst.service.model.UserModel;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@RestController("user")
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaesController{

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/register")
    public CommonReturnType register(@RequestParam("userMessageCode") String code,
                                     @RequestParam("userTelephone") String telephone,
                                     @RequestParam("userPassword") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        String otpCode = iUserService.getRedisOtp(telephone);

        if (!StringUtils.equals(otpCode,code)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
        }
        UserModel userModel = new UserModel();
        userModel.setTelphone(telephone);
        userModel.setEncrptPassword(EncodeByMd5(password));
        iUserService.insterUser(userModel);
        return CommonReturnType.create(null);
    }

    //用户通过账号密码登录
    @RequestMapping(value = "/loginByPassword",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType loginByPassword(@RequestParam("userTelephone") String telphone,
                                  @RequestParam("userPassword") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        String encrptPassword = EncodeByMd5(password);
        String token = iUserService.loginByPassword(telphone,encrptPassword);
        return CommonReturnType.create(token);
    }


    //手机短信验证登录
    @RequestMapping("/loginByCode")
    public CommonReturnType loginByCode(@RequestParam("userTelephone") String telephone,
                                        @RequestParam("userMessageCode") String code) throws  BusinessException {
        String token = iUserService.loginByCode(telephone,code);
        return CommonReturnType.create(token);
    }


    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType getOtp(@RequestParam("userTelephone")String telephone) throws BusinessException {
        if (telephone==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        iUserService.getOtp(telephone);
        return CommonReturnType.create("短信发送成功");
    }



    private String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newStr = Base64.encodeBase64String(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

}
