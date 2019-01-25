package com.dante.ssm.bean.base;


public class BaseResult {
    private int code;
    private String msg;
    private Object data;


    public static BaseResult success(Object d) {
        BaseResult msg = new BaseResult();
        msg.setMsg("success");
        msg.setCode(200);
        msg.setData(d);
        return msg;
    }

    public static BaseResult result(int code, String content, Object data) {
        BaseResult msg = new BaseResult();
        msg.setMsg(content);
        msg.setCode(code);
        msg.setData(data);
        return msg;
    }

    public static BaseResult fail() {
        BaseResult msg = new BaseResult();
        msg.setMsg("失败");
        msg.setCode(201);
        return msg;
    }

    public static BaseResult fail(String content) {
        BaseResult msg = new BaseResult();
        msg.setMsg(content);
        msg.setCode(201);
        return msg;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
