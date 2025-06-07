package com.ruanko.pojo;

public class Result<T> {
    /**
     * 返回代码
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;
    /**
     * 结果对象
     */
    private T data;
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result() {
    }
    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static Result success(String token) {
        return null;
    }

    public static Result fail(int i, String 用户名或密码错误) {
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}