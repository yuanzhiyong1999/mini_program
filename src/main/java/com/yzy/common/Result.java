package com.yzy.common;

import java.io.Serializable;

import lombok.Data;

/**
 * @author yuanzhiyong
 * @version 1.0.0
 * @ClassName Result.java
 * @Description 统一结果封装
 * @createTime 2021年03月29日 16:57:00
 */
@Data
public class Result implements Serializable {

    private int code;           //200是正常，非200表示异常
    private String msg;
    private int count;
    private Object data;

    public static Result succ(Object data,int count) {

        return succ(200, "操作成功", count,  data);
    }

    public static Result succ(int code, String msg, int count, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setCount(count);
        r.setData(data);
        return r;
    }

    public static Result fail(String msg) {

        return fail(400, msg, 0,null);
    }


    public static Result fail(int code, String msg, int count, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setCount(count);
        r.setData(data);
        return r;
    }
}
