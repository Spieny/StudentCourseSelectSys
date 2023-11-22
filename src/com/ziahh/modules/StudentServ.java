package com.ziahh.modules;

import com.ziahh.Utils;
import com.ziahh.beans.Admin;
import com.ziahh.beans.Course;
import com.ziahh.beans.Student;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class StudentServ {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Student> studentAccounts = new ArrayList<>();
    private Student currentStudent = null;
    private String[] lastQueriedResult;

    public static void setStudentAccounts(ArrayList<Student> studentAccounts) {
        StudentServ.studentAccounts = studentAccounts;
    }

    public static ArrayList<Student> getStudentAccounts() {
        return studentAccounts;
    }

    //注入数据，方便测试，后期删掉
    /*public static void init(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(AdminServ.getAllCourses().get(0));
        studentAccounts.add(new Student("黄萎病","18",'男',"114514",courses));
        studentAccounts.add(new Student("绿萎病","24",'男',"114514",courses));
        studentAccounts.add(new Student("蓝萎病","114",'男',"114514",courses));
        studentAccounts.add(new Student("红萎病","16",'男',"114514",courses));
        studentAccounts.add(new Student("紫萎病","12",'男',"114514",courses));
        studentAccounts.add(new Student("青萎病","31",'男',"114514",courses));
        studentAccounts.add(new Student("南通萎病","24",'男',"114514",courses));
        studentAccounts.add(new Student("橙萎病","20",'男',"114514",courses));
    }*/

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
            System.out.println("请输入学号：");
            String account = sc.next();
            if (account.equals("0")){ break;} //输入0退出系统
            System.out.println("请输入密码：");
            String password = sc.next();
            //待修改 MD5校验
            Student stu = getStudentbyID(account);
            if (stu != null && stu.getPassword().equals(password)){
                if (stu.getLoginTimes() >= 3){
                    System.out.println("你的账号登录异常，已被冻结，请联系管理员解封！");
                    continue;
                } else {
                    //如果登录成功，清除该学生的登录异常次数。
                    stu.setLoginTimes(0);
                    logined = true;
                    currentStudent = stu;
                }
            }
            //如果账号密码正确，跳出while循环
            if (logined){
                break;
            } else {
                if (stu != null){
                    stu.setLoginTimes(stu.getLoginTimes() + 1);
                    System.out.println(stu.getPassword());
                }
                System.out.println("账号或密码错误！");
            }
        }
        if (logined){
            System.out.println("登陆成功！");
            this.start();
        }

    }

    public void start(){
        DataWriter.writeAll();
        while(quitFlag){
        System.out.println("========> 广东原神大学教务处 学生界面 <========");
        System.out.println("欢迎你！" + currentStudent.getStudentName() + "同学。");
        System.out.println("1.选择课程 2.退选课程 3.查询课表 4.个人信息");
        System.out.println("5.修改密码");
        System.out.println("0.退出系统");
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
                    break;
                case "4":
                    StudentInquirePersonalInformation();
                    break;
                case "5":
                    StudentChangePassword();
                    break;
                case "0":
                    currentStudent = null; //将已登录的学生对象归为null
                    System.out.println("===退出学生系统===");
                    quitFlag = false;
                    break;
                default:
                    System.out.println("指令有误！请重新输入。");
            }
        }
        quitFlag = true;
    }

    private void StudentChangePassword() {
        while (true){
            System.out.println("请输入您原来的密码：");
            System.out.println("(输入 0 退出修改)");
            String orignalPassword = sc.next();

            if (orignalPassword.equals("0")){return;}

            System.out.println("请输入您的新密码：");
            String newPassword = sc.next();
            System.out.println("请再次输入您的新密码：");
            String confirmPassword = sc.next();
            if (orignalPassword.equals(currentStudent.getPassword())){
                if (confirmPassword.equals(newPassword)){
                    currentStudent.setPassword(newPassword);
                    System.out.println("修改密码成功！请牢记新密码！");
                    DataWriter.writeAll();
                    break;
                } else {
                    System.out.println("两次输入的密码不一致！");
                }
            } else {
                System.out.println("你输入的原密码不正确！");
            }
        }
    }

    private void StudentChooseCourse() {
        int page = 1;
        int size = 5;
        String command;
        while (true){
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            String[] r = Utils.pagedQuery(AdminServ.getAllCourses(),size,page);
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            System.out.println("输入课程ID（c0xxxx）来选择你需要的课程");
            command = sc.next();
            if (Objects.equals(command, "0")){
                break;}
            if (Objects.equals(command, "2") && page < (AdminServ.getAllCourses().size() / size) + 1){
                page++;continue;}
            if (Objects.equals(command, "1") && page > 1){
                page--;continue;}
            Course c = getCourseByID(command);

            if (c == null) {
                System.out.println("此课程ID不存在！请重新输入。");
                continue;
            }
            if (Utils.isStudenetChosenSpecificCourse(currentStudent,c)){
                System.out.println("你已经选择这门课程！请重新选择。");
            } else {
                System.out.println(currentStudent.getChosenCourses().contains(c));
                boolean isConfirmed = false;
                while (true){
                    System.out.println("你选择的课程为：");
                    System.out.println(c.toStringLine());
                    System.out.println("你确定吗？（y/n）");
                    String confirm = sc.next();
                    switch (confirm){
                        case "y":
                            //
                            System.out.println("你已选择课程");
                            currentStudent.addCourse(c);
                            isConfirmed = true;
                            break;
                        case "n":
                            System.out.println("取消选择课程");
                            isConfirmed = true;
                            break;
                        default:
                            System.out.println("请重新输入！");
                    }
                    if (isConfirmed){
                        break;
                    }
                }

            }
        }
    }

    private void StudentQuitCourse() {
        int page = 1;
        String command;
        while (true){
            String[] r = Utils.pagedQuery(currentStudent.getChosenCourses(),5,page);
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            System.out.println("输入课程ID（c0xxxx）来选择你想退选的课程");
            command = sc.next();
            if (Objects.equals(command, "0")){
                break;}
            if (Objects.equals(command, "2") && page < (AdminServ.getAllCourses().size() / 5) + 1){
                page++;continue;}
            if (Objects.equals(command, "1") && page > 1){
                page--;continue;}
            Course c = getCourseByID(command);
            if (c == null) {
                System.out.println("此课程ID不存在！请重新输入。");
            } else {
                boolean isConfirmed = false;
                while (true){
                    System.out.println("你选择的课程为：");
                    System.out.println(c.toStringLine());
                    System.out.println("你确定吗？（y/n）");
                    String confirm = sc.next();
                    switch (confirm){
                        case "y":
                            //
                            System.out.println("你已退选此课程");
                            currentStudent.removeCourse(c);
                            isConfirmed = true;
                            break;
                        case "n":
                            System.out.println("取消退选此课程");
                            isConfirmed = true;
                            break;
                        default:
                            System.out.println("请重新输入！");
                    }
                    if (isConfirmed){
                        break;
                    }
                }

            }
        }
    }

    private void StudentInquireCourses() {
        System.out.println(currentStudent.getChosenCoursesString());
        System.out.println("输入任意字符退出");
        sc.next();
    }

    private void StudentInquirePersonalInformation() {
        System.out.println(currentStudent.toString());
        System.out.println("输入任意字符退出");
        sc.next();
    }

    /**
     * 通过学号获取学生对象，未找到则返回null
     * @param id
     */
    private Student getStudentbyID(String id){
        for(Student s :StudentServ.getStudentAccounts()){
            if (s.getStudentID().equals(id)){
                return s;
            }
        }
        return null;
    }

    /**
     * 通过账号获取管理员对象，未找到则返回null
     * @param name
     * @return
     */
    private Admin getAdminAccountbyName(String name){
        for (Admin a : AdminServ.getAdminAccounts()){
            if (a.getAdminID().equals(name)){
                return a;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 通过账号获取课程对象，未找到则返回null
     * @param id 课程id
     * @return 课程对象
     */
    private Course getCourseByID(String id){
        for (Course c:AdminServ.getAllCourses()){
            if(c.getCourseId().equals(id)){
                return c;
            }
        }
        return null;
    }
}
