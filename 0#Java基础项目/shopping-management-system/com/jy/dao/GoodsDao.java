package com.jy.dao;

import com.jy.db.DbClose;
import com.jy.db.DbConn;
import com.jy.entity.Goods;
import com.jy.tools.ScannerChoice;

import java.sql.*;
import java.util.ArrayList;

/**
 * 数据库 goods 表操作
 */
public class GoodsDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1. 添加商品到数据库goods表
     *
     * @param goods
     * @return
     */
    public boolean addGoods(Goods goods) {
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "INSERT INTO GOODS(GNAME, GPRICE, GNUM) VALUES(?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods.getGname());
            pstmt.setDouble(2, goods.getGprice());
            pstmt.setInt(3, goods.getGnum());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }

    /**
     * 2.更改商品信息到 Goods 表
     *
     * @param key   选择要更改的商品信息，1是修改名称，2是修改价格，3是修改数量
     * @param goods 商品对象
     * @return boolean
     */
    public boolean updateGoods(int key, Goods goods) {
        boolean bool = false;
        conn = DbConn.getConn();
        switch (key) {
            case 1: // key = 1，修改商品名称
                try {
                    String sql = "UPDATE GOODS SET GNAME = ? WHERE GID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, goods.getGname());
                    pstmt.setInt(2, goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if (rs > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.addClose(pstmt, conn);
                }
                break;

            case 2: // key = 2，修改商品价格
                try {
                    String sql = "UPDATE GOODS SET GPRICE = ? WHERE GID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDouble(1, goods.getGprice());
                    pstmt.setInt(2, goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if (rs > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.addClose(pstmt, conn);
                }
                break;

            case 3: // key = 3，修改商品数量
                try {
                    String sql = "UPDATE GOODS SET GNUM = ? WHERE GID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, goods.getGnum());
                    pstmt.setInt(2, goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if (rs > 0){
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.addClose(pstmt, conn);
                }
                break;
            default:
                break;
        }
        return bool;
    }

    /**
     * 3. 从数据库中删除商品
     * @param gid 商品编号
     * @return boolean
     */
    public boolean deleteGoods(int gid){
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "DELETE FROM GOODS WHERE GID = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gid);
            int rs = pstmt.executeUpdate();
            if (rs > 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }

    /**
     * 4. 展示所有商品信息
     * @return ArrayList<Goods>
     */
    public ArrayList<Goods> displayGoods(){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM GOODS";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int gid = rs.getInt(1);
                String gname = rs.getString(2);
                double gprice = rs.getDouble("gprice"); 		//双引号+主键名,也可用数字表示.
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gid,gname,gprice,gnum);	//创建Goods对象，并赋值.
                goodsList.add(goods);							//添加信息到动态数组中.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return goodsList;
    }

    /**
     * 5. 查询商品信息
     * @param key 查询方式
     * @return ArrayList<Goods>
     */
    public ArrayList<Goods> queryGoods(int key){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getConn();
        switch (key){
            case 1: // 按数量升序查询
                String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
                try {
                    pstmt = conn.prepareStatement(sqlGnum);
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
                break;

            case 2: // 按商品价格升序查询
                String sqlGprice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
                try {
                    pstmt = conn.prepareStatement(sqlGprice);
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
                break;

            case 3: //	key=3商品 关键字 查询
                String nameGet = ScannerChoice.ScannerInfoString();
                //String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE '%'||?||'%'";
                String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE ?";
                String sqlParasGname = "%" + nameGet + "%";
                try {
                    pstmt = conn.prepareStatement(sqlGname);
                    pstmt.setString(1, sqlParasGname);
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
                break;
            default:
                break;
        }
        return goodsList;
    }


}
