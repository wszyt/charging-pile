package com.zyt.charging.api.entity.reponse;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;
    private static final long serialVersionUID = 5685219805243635527L;

    private int status;
    private String message;
    private Object data;

    public static BaseResponse success() {
        return BaseResponse.createResult (STATUS_SUCCESS, "成功", null);
    }

    public static BaseResponse success(String message) {
        return BaseResponse.createResult (STATUS_SUCCESS, message, null);
    }

    public static BaseResponse success(String message, Object data) {
        return BaseResponse.createResult (STATUS_SUCCESS, message, data);
    }

    public static BaseResponse fail() {
        return BaseResponse.createResult (STATUS_FAIL, "失败", null);
    }

    public static BaseResponse fail(String message) {
        return BaseResponse.createResult (STATUS_FAIL, message, null);
    }

    public static BaseResponse fail(int status, String message) {
        return BaseResponse.createResult (status, message, null);
    }

    private static BaseResponse createResult(int status, String message, Object data) {
        BaseResponse BaseResponse = new BaseResponse ();
        BaseResponse.setStatus (status);
        BaseResponse.setMessage (message);
        BaseResponse.setData (data);
        return BaseResponse;
    }
}
