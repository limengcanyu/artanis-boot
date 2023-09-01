package com.spring.boot.pulsar;

public class MyMsg {

    private String data;

    public MyMsg(String data) {
        this.data = data;
    }

    public MyMsg() {}

    public String getData() {
        return data;
    }

}
