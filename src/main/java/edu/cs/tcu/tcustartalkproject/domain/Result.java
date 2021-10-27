package edu.cs.tcu.tcustartalkproject.domain;

import java.io.Serializable;

public class Result implements Serializable {
    // status code, e.g. 200, 500 etc
    private int status;
    // success msg or error msg, e.g. delete success
    private String msg;

    // used to store the returned data, e.g. one user, a list of users
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Result() {
    }

    public Result(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
