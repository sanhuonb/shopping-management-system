package com.jy.tools;

import com.jy.entity.Goods;
import com.jy.page.GoodsPage;
import com.jy.page.MainPage;
import com.jy.page.SalesManPage;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

/**
 * 1、完成操作后选择下一步
 * 2、界面选择操作
 */

public class ScannerChoice {

    /**
     * @return double 键盘获取商品价格，小数点后两位
     */
    public static double ScanerInfo() {
        double num = 0.0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("保留小数点后2位，请输入: ");
            String info = scanner.next();
            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";
            if (info.matches(regex)) {
                num = Double.parseDouble(info);
            } else {
                System.out.println("! 输入有误!");
                continue;
            }
            break;
        } while (true);
        return num;
    }

    /**
     * @return int 键盘获取输入商品数量
     */
    public static int ScannerNum() {
        int num = 0;
        String regex = "[1-9]|([1-9][0-9]+)"; // 商品数量正则表达式
        do {
            Scanner scanner = new Scanner(System.in);
            String info = scanner.next();
            if (info.matches(regex)) {
                num = Integer.parseInt(info);
            } else {
                System.out.println("输入有误");
                continue;
            }
            break;
        } while (true);
        return num;
    }

    /**
     * @return String 获取键盘的输入
     */
    public static String ScannerInfoString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入: ");
        return scanner.next();
    }

    /**
     * 获取用户-更改完商品-下一步
     * 获取用户-删除完商品-下一步
     * 获取用户-添加完商品-下一步
     *
     * @param oper
     */
    public static void changedInfoNext(String oper) throws InterruptedException {
        do {
            System.out.println("是否继续进行-当前操作:(Y / N)");
            String choice = ScannerInfoString();
            if ("y".equals(choice) || "Y".equals(choice)) {
                if ("updateGoodsPage".equals(oper)) {
                    GoodsPage.updateGoodsPage();
                } else if ("deleteGoodsPage".equals(oper)) {
                    GoodsPage.deleteGoodsPage();
                } else if ("addGoodsPage".equals(oper)) {
                    GoodsPage.addGoodsPage();
                }
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.MaintenancePage();
            }
            System.out.println("\n输入有误！请重新输入.");
        } while (true);
    }

    /**
     * 获取用户-更改-完售货员-下一步
     * 获取用户-添加-完售货员-下一步
     * 获取用户-查询-完售货员-下一步
     * 获取用户-删除-完售货员-下一步
     *
     * @param oper 调用者
     */
    public static void choiceSalesManNext(String oper) throws InterruptedException {
        do {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();

            if ("y".equals(choice) || "Y".equals(choice)) {
                //下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同）
                if ("updateSalesMan".equals(oper)) {
                    SalesManPage.updateSalesManPage();
                } else if ("deleteSalesMan".equals(oper)) {
                    SalesManPage.deleteSalesManPage();
                } else if ("addSalesMan".equals(oper)) {
                    SalesManPage.addSalesManPage();
                } else if ("querySalesMan".equals(oper)) {
                    SalesManPage.querySalesManPage();
                }
                //上面的嵌套结束
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.salesManManagementPage();
            }
            System.err.println("\t输入有误！");
        } while (true);
    }
}
