package com.sst.error;

public enum EmBusinessError implements CommonError{
    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //20000开头为用户信息错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户信息错误"),
    USER_NOT_LOGIN(20003,"用户未登录"),
    USER_INFORMATION_FAILURE(20004,"登录状态失效"),
    //30000开头为商品类信息错误
    COMMODITY_INFORMATION_ERROR(30001,"商品信息错误"),
    CART_UPDATE_FAILED(30002,"购物车更新失败")
    ;

    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
