package com.keke.roomlib.api;

public abstract  class ResponseListener {
    public abstract void success(Object o);

    public abstract void error(String s);

    public void onCodeError(int errorCode) {

    }
    public void onCodeError(int errorCode,String s) {

    }
}
