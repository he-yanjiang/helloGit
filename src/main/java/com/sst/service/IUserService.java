package com.sst.service;

import com.sst.bean.UserDO;
import com.sst.error.BusinessException;
import com.sst.service.model.UserModel;

public interface IUserService {

    String loginByPassword(String telephone, String encrptPassword) throws BusinessException;

    String getToken(String telephone,UserDO userDO);

    UserDO getUserByToken(String token) throws BusinessException;

    void insterUser(UserModel userModel) throws BusinessException;

    void getOtp(String telephone) throws BusinessException;

    String loginByCode(String telephone, String code) throws BusinessException;

    String getRedisOtp(String telephone);
}
