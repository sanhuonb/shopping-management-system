package com.jy.entity;

public class SalesMan {
    private int sid;
    private String sName;
    private String sPassWord;

    //验证用户登录
    public SalesMan(int sid, String sPassWord) {
        this.sid = sid;
        this.sPassWord = sPassWord;
    }

    //查询用户、更改用户密码
    public SalesMan(int sid, String sName, String sPassWord) {
        this.sid = sid;
        this.sName = sName;
        this.sPassWord = sPassWord;
    }

    //添加用户
    public SalesMan(String sName, String sPassWord){
        this.sName = sName;
        this.sPassWord = sPassWord;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPassWord() {
        return sPassWord;
    }

    public void setsPassWord(String sPassWord) {
        this.sPassWord = sPassWord;
    }
}
