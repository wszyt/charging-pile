package com.zyt.charging.api.entity.reponse;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult<T> implements Serializable {
    public static final Integer STATUS_SUCCESS = 200;
    public static final Integer STATUS_FAIL = 500;
    private static final long serialVersionUID = 5685219805243635527L;

    private Integer status;
    private String message;
    private T data;

    private BaseResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResult<T> success() {
        return new BaseResult<T> (STATUS_SUCCESS, "成功", null);
    }

    public static <T> BaseResult<T> success(String message) {
        return new BaseResult<T>(STATUS_SUCCESS, message, null);
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<T>(STATUS_SUCCESS, "成功", data);
    }

    public static <T> BaseResult<T> success(T data, String message) {
        return new BaseResult<T>(STATUS_SUCCESS, message, data);
    }

    public static <T> BaseResult<T> fail() {
        return new BaseResult<T> (STATUS_FAIL, "失败", null);
    }

    public static <T> BaseResult<T> fail(String message) {
        return new BaseResult<T> (STATUS_FAIL, message, null);
    }

    public static <T> BaseResult<T> fail(int status, String message) {
        return new BaseResult<T> (status, message, null);
    }
}
