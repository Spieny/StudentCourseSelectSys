package com.ziahh.modules;

import com.ziahh.Utils;

import java.util.Scanner;

public class MainServ {

    private static Scanner sc = new Scanner(System.in);
    private static AdminServ as = null;
    private static StudentServ st = null;

    public static AdminServ getAdminServ() {
        if (as == null) {
            as= new AdminServ();
        }
        return as;
    }

    public static StudentServ getStudentServ() {
        if (st == null) {
            st = new StudentServ();
        }
        return st;
    }

    public void run(){
        AdminServ.init();
        StudentServ.init();

        while(true){
            System.out.println("========>广东原神大学教务处<========");
            System.out.println("1.管理员登录");
            System.out.println("2.学生登录");
            System.out.println("0.退出系统");
            System.out.println("=================================");
            String in = sc.next();
            switch (in){
                case "1":
                    if (as == null){
                        as = new AdminServ();
                        as.run();
                    } else {
                        as.run();
                    }
                    break;
                case "2":
                    if (st == null){
                        st = new StudentServ();
                        st.run();
                    } else {
                        st.run();
                    }
                    break;
                case "0":
                    System.out.println("退出系统");
                    System.exit(0);
                default:
                    System.out.println("Wrong Arguments!");
            }
        }
    }
}
