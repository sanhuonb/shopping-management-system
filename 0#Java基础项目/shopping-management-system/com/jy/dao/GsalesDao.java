package com.jy.dao;

import com.jy.db.DbClose;
import com.jy.db.DbConn;
import com.jy.entity.Gsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GsalesDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        GsalesDao gsalesDao = new GsalesDao();
        ArrayList<Gsales> gsales = gsalesDao.dailyGales();
        for (int i = 0; i < gsales.size(); i++){
            Gsales gsales1 = gsales.get(i);
            System.out.println(gsales1.getgName());
        }
    }

    /**
     * 1.当天卖出的商品
     * @return ArrayList<Gsales> 商品信息,包括 allSum (单种商品的销售总和)
     */
    public ArrayList<Gsales> dailyGales(){
        ArrayList<Gsales> gsalesList = new ArrayList<Gsales>();
        conn = DbConn.getConn();
        //售卖时间=当前时间 trunc(sdate) =trunc(sysdate) 单位：天
        String sql = "select gname, gprice, gnum, allSnum " +
                "from goods as a, " +
                "(select gid salesid,sum(snum) allSnum from gsales where DATE_FORMAT(sdate,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') " +
                "group by gid) as b where gid = salesid";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String gName = rs.getString(1);
                double gPrice = rs.getDouble(2);
                int gNum = rs.getInt(3);
                int allSnum = rs.getInt("allSnum");
                Gsales gsales = new Gsales(gName, gPrice, gNum, allSnum);
                gsalesList.add(gsales);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return gsalesList;
    }
    /**
     * 2.购物结算-向sales表中插入商品数据！
     * @param gSales 售卖商品对象
     * @return boolean
     */
    public boolean shoppingSettlement(Gsales gSales){
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "INSERT INTO GSALES(GID, SID, SNUM) VALUES(?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gSales.getgId());
            pstmt.setInt(2, gSales.getsId());
            pstmt.setInt(3, gSales.getsNum());
            int rs = pstmt.executeUpdate();
            if (rs > 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return bool;
    }
}
