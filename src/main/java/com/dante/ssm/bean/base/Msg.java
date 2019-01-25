package com.dante.ssm.bean.base;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    public static Msg success() {
        Msg msg = new Msg();
        msg.setMsg("success");
        msg.setCode(200);
        return msg;
    }

    public static Msg success(String content) {
        Msg msg = new Msg();
        msg.setMsg(content);
        msg.setCode(200);
        return msg;
    }

    public static Msg fail() {
        Msg msg = new Msg();
        msg.setMsg("失败");
        msg.setCode(201);
        return msg;
    }

    public static Msg fail(String content) {
        Msg msg = new Msg();
        msg.setMsg(content);
        msg.setCode(201);
        return msg;
    }

    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
