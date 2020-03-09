package com.jy.entity;

public class Gsales {
    private int gId; // 商品编号
    private int sId; // 营业员编号
    private int sNum; // 销量

    private String gName; // 商品名称
    private double gPrice; // 商品价格
    private int gNum; // 库存？
    private int allSnum; // 单种商品销量总和

    // 购物结算
    public Gsales(int gId, int sId, int sNum) {
        this.gId = gId;
        this.sId = sId;
        this.sNum = sNum;
    }

    // 展现商品列表
    public Gsales(String gName, double gPrice, int gNum, int allSnum) {
        this.gName = gName;
        this.gPrice = gPrice;
        this.gNum = gNum;
        this.allSnum = allSnum;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public double getgPrice() {
        return gPrice;
    }

    public void setgPrice(double gPrice) {
        this.gPrice = gPrice;
    }

    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    public int getAllSnum() {
        return allSnum;
    }

    public void setAllSnum(int allSnum) {
        this.allSnum = allSnum;
    }
}
