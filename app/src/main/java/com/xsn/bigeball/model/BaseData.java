package com.xsn.bigeball.model;

import java.io.Serializable;

/**
 * Created by XSN on 2016/8/30.
 */
public class BaseData implements Serializable {


    private int resultCode;
    private Object errorCode;
    private Object msg;


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
