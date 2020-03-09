package com.jy.entity;

/**
 * goods 商品实体类
 */
public class Goods {
    private int gid; // 数据库 Goods 表主键
    private String gname;
    private double gprice;
    private int gnum;

    // 显示所有商品
    public Goods(int gid, String gname, double gprice, int gnum) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gnum;
    }

    // 添加商品信息
    public Goods(String gname, double gprice, int gnum) {
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gnum;
    }

    //根据编号修改商品的价格
    public Goods(int gid, double gprice){
        this.gid = gid;
        this.gprice = gprice;
    }
    //根据编号修改商品的数量
    public Goods(int gid, int gnum){
        this.gid = gid;
        this.gnum = gnum;
    }
    //根据编号修改商品的名字
    public Goods(int gid, String gname){
        this.gid = gid;
        this.gname = gname;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gnum=" + gnum +
                '}';
    }
}
