package com.jy.tools;

import com.jy.dao.GoodsDao;
import com.jy.db.DbClose;
import com.jy.db.DbConn;
import com.jy.entity.Goods;
import com.jy.entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 查询 && 打印 函数工具
 */
public class QueryPrint {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 模糊查询并陈列查询信息函数小工具
     * @param oper 调用者
     * @return 查询到的信息的gid，如果返回值等于-1，则代表信息异常
     */
    public static int query(String oper) throws InterruptedException {
        int gid = -1;
        String shopping = ScannerChoice.ScannerInfoString();
        ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(-1, shopping);
        if (goodsList == null || goodsList.size() <= 0){
            System.out.println("\t查无此商品");
            ScannerChoice.changedInfoNext(oper);
        }else {
            Goods good = goodsList.get(0);
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.print("\t" + good.getGid() + "\t\t" + good.getGname() + "\t\t" + good.getGprice() +
                    "\t\t" + good.getGnum());
            if (good.getGnum() == 0){
                System.out.println("\t\t该商品已售空");
            }else if (good.getGnum() < 10){
                System.out.println("\t\t该商品已不足10件");
            }else {
                System.out.println("\t\t-");
            }
            gid = good.getGid();
        }
        return gid;
    }

    /**
     * 模糊查询小工具
     * @return int 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
     */
    public static int querySettlement() {
        int gid = -1;
        ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);
        if (goodsSettlement == null || goodsSettlement.size() == 0){
            System.err.println("\t！！查无此商品 ！！\n");
            gid = -3;
        } else {  //查到有此商品，实现进行 更改商品 信息操作！
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0; i < goodsSettlement.size(); i++){
                Goods goods = goodsSettlement.get(i);
                if (goods.getGnum() >= 0){
                    System.out.print("\t" + goods.getGid() + "\t\t\t\t" + goods.getGname() + "\t\t\t" + goods.getGprice()
                            + "\t\t\t" + goods.getGnum());
                    if (goods.getGnum() == 0){
                        System.out.println("\t\t\t该商品已售空");
                    } else if (goods.getGnum() < 10){
                        System.out.println("\t\t\t该商品已不足10件");
                    } else {
                        System.out.println("\t\t\t-");
                    }
                }
                if (goodsSettlement.size() == 1){
                    gid = goods.getGid();
                } else {
                    gid = -2;
                }
            }
        }
        return gid;
    }

    /**
     * 根据商品 gID or gName 查询商品
     * @param gID 商品id
     * @param gName 商品名称
     * @return 商品信息
     */
    public ArrayList<Goods> queryGoodsKey(int gID, String gName){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM GOODS WHERE GID = ? OR GNAME = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gID);
            pstmt.setString(2, gName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int gid = rs.getInt("gid");
                String gname = rs.getString(2);
                double gprice = rs.getDouble(3);
                int gnum = rs.getInt(4);
                Goods goods = new Goods(gid, gname, gprice, gnum);
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return goodsList;
    }
    /**
     * 精确查询售货员信息
     * @param sName 售货员名字
     * @return
     */
    public ArrayList<SalesMan> querySalesMan(String sName){
        ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM SALESMAN WHERE SNAME = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            rs = pstmt.executeQuery();
            if (rs.next()){
                int sid = rs.getInt("sid");
                String sname = rs.getString(2);
                String sPassword = rs.getString(3);
                SalesMan salesMan = new SalesMan(sid, sname, sPassword);
                SalesManList.add(salesMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return SalesManList;
    }

    public static void main(String[] args) {
       int i =  querySettlement();
        System.out.println("i=" + i);
    }
}
