package com.jy.dao;

import com.jy.db.DbClose;
import com.jy.db.DbConn;
import com.jy.entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class SalesManDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1.前台收银登陆
     * @param sName 用户名
     * @return ArrayList<SalesMan> sPassWord,sId
     */
    public ArrayList<SalesMan> checkstandLog(String sName){
        ArrayList<SalesMan> SalesManInfo = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        String sql = "SELECT SID, SPASSWORD FROM SALESMAN WHERE SNAME = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String sPassWord = rs.getString("spassword");
                int sId = rs.getInt("sId");
                SalesMan salesMan = new SalesMan(sId, sPassWord);
                SalesManInfo.add(salesMan);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return SalesManInfo;
    }

    /**
     * 2.添加售货员
     * @param salesMan 用户名
     * @return boolean
     */
    public boolean addSalesMan(SalesMan salesMan){
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "INSERT INTO SALESMAN(SNAME, SPASSWORD) VALUES(?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, salesMan.getsName());
            pstmt.setString(2, salesMan.getsPassWord());
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
     * 3. 更改售货员信息
     * @param key 要更改项, key=1:更改姓名，key=2:更改密码
     * @param salesMan 要更改用户
     * @return
     */
    public boolean updateSalesMan(int key, SalesMan salesMan){
        boolean bool = false;
        conn = DbConn.getConn();
        switch (key){
            case 1: // 更改售货员姓名
                String sqlChangeName = "UPDATE SALESMAN SET SNAME = ? WHERE SID = ?";
                try {
                    pstmt = conn.prepareStatement(sqlChangeName);
                    pstmt.setString(1, salesMan.getsName());
                    pstmt.setInt(2, salesMan.getSid());
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

            case 2: // 更改密码
                String sqlChangePassword = "UPDATE SALESMAN SET SPASSWORD = ? WHERE SID = ?";
                try {
                    pstmt = conn.prepareStatement(sqlChangePassword);
                    pstmt.setString(1, salesMan.getsPassWord());
                    pstmt.setInt(2, salesMan.getSid());
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
     * 4. 根据姓名删除售货员信息
     * @param sName 收货员姓名
     * @return
     */
    public boolean deleteSalesMan(String sName){
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "DELETE FROM SALESMAN WHERE SNAME = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
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
     * 5. 模糊查询售货员
     * @param sName 姓名
     * @return
     */
    public ArrayList<SalesMan> querySalesMan(String sName){
        ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        sName = "%" + sName + "%";
        String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int sid = rs.getInt("sid");
                String sname = rs.getString(2);
                String spassword = rs.getString(3);
                SalesMan salesMan = new SalesMan(sid, sname, spassword);
                salesManList.add(salesMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManList;
    }

    /**
     * 6.显示所有售货员
     * @return ArrayList<SalesMan>
     */
    public ArrayList<SalesMan> displaySalesMan(){
        ArrayList<SalesMan> saleManList = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM SALESMAN";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                String spassword = rs.getString("spassword");
                SalesMan salesMan = new SalesMan(sid, sname, spassword);
                saleManList.add(salesMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return saleManList;
    }
}
