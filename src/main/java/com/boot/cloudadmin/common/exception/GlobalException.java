package com.boot.cloudadmin.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalException extends RuntimeException{
    private String msg;
    private int code = 500;

    public GlobalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public GlobalException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GlobalException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
