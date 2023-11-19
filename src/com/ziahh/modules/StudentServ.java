package com.ziahh.modules;

import com.ziahh.Utils;
import com.ziahh.beans.Course;
import com.ziahh.beans.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentServ {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Student> studentAccounts = new ArrayList<>();
    private Student currentStudent = null;

    public static ArrayList<Student> getStudentAccounts() {
        return studentAccounts;
    }

    public static void init(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(AdminServ.getAllCourses().get(0));
        courses.add(AdminServ.getAllCourses().get(1));
        courses.add(AdminServ.getAllCourses().get(3));
        courses.add(AdminServ.getAllCourses().get(4));
        courses.add(AdminServ.getAllCourses().get(6));
        studentAccounts.add(new Student("黄萎病","18",'男',"114514",courses));
        studentAccounts.add(new Student("绿萎病","24",'男',"114514",courses));
        studentAccounts.add(new Student("蓝萎病","114",'男',"114514",courses));
        studentAccounts.add(new Student("红萎病","16",'男',"114514",courses));
        studentAccounts.add(new Student("紫萎病","12",'男',"114514",courses));
        studentAccounts.add(new Student("青萎病","31",'男',"114514",courses));
        studentAccounts.add(new Student("南通萎病","24",'男',"114514",courses));
        studentAccounts.add(new Student("橙萎病","20",'男',"114514",courses));
    }

    private boolean quitFlag = true;

    public StudentServ(){
    }

    public void run(){
        login();
    }

    public void login(){
        boolean logined = false;

        if (studentAccounts.isEmpty()){
            System.out.println("还没有任何学生！请联系管理员添加！");
            return;
        }

        System.out.println("========> 广东原神大学教务处 学生登录 <========");
        System.out.println("(键入0退出系统)");
        while (true){
            Utils.printMessage("请输入学号：");
            String account = sc.next();
            if (account.equals("0")){ break;} //输入0退出系统
            System.out.println("请输入密码：");
            String password = sc.next();
            //待修改 链接数据库 and MD5校验
            for (Student stu : studentAccounts){
                if (stu.getStudentID().equals(account) && stu.getPassword().equals(password)){
                    logined = true;
                    currentStudent = stu;
                    break;
                }
            }
            if (logined){
                break;
            } else {
                System.out.println("账号或密码错误！");
            }
        }
        if (logined){
            System.out.println("登陆成功！");
            this.start();
        }

    }

    public void start(){
        System.out.println("========> 广东原神大学教务处 学生界面 <========");
        System.out.println("欢迎你！" + currentStudent.getStudentName() + "同学。");
        System.out.println("1.选择课程");
        System.out.println("2.退选课程");
        System.out.println("3.查询课表");
        System.out.println("4.查询个人信息");
        System.out.println("0.退出系统");
        while(quitFlag){
            String in = sc.next();
            switch (in){
                case "1":
                    StudentChooseCourse();
                    break;
                case "2":
                    StudentQuitCourse();
                    break;
                case "3":
                    StudentInquireCourses();
                case "4":
                    StudentInquirePersonalInformation();
                case "0":
                    System.out.println("===退出学生系统===");
                    quitFlag = false;
                    break;
                default:
                    System.out.println("Wrong Arguments!");
            }
        }
        quitFlag = true;
    }

    private void StudentChooseCourse() {
    }

    private void StudentQuitCourse() {
    }

    private void StudentInquireCourses() {
    }

    private void StudentInquirePersonalInformation() {

    }
}
