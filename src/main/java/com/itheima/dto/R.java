package com.itheima.dto;

import lombok.Data;

@Data
public class R {

    private int code;
    private Object data;
    private String message;

    public R(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public static R ok(Object data) {
        return new R(999, data, null);
    }

    public static R ok(String dataAndMessage) {
        return new R(999, dataAndMessage, dataAndMessage);
    }

    public static R error(int code, String message) {
        return new R(code, null, message);
    }
}
