package com.sst.response;

public class CommonReturnType {
    private String status;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static CommonReturnType create(Object data, String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(data);
        return commonReturnType;
    }

    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
}
