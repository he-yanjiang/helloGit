package com.sst.error;
//包装器业务异常实现
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;
    //接收自定义errMsg的方法构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        commonError.setErrMsg(errMsg);
    }
    //直接接受EmBusinessError
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
