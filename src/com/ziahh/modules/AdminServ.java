package com.ziahh.modules;

import com.ziahh.Utils;
import com.ziahh.beans.Admin;
import com.ziahh.beans.Course;
import com.ziahh.beans.Student;
import com.ziahh.enums.WeekDay;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminServ{

    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    private static ArrayList<Admin> adminAccounts = new ArrayList<>();
    private static ArrayList<Course> allCourses = new ArrayList<>();
    private static Admin currentLoginedAdmin = null;
    private boolean quitFlag = true;

    public static ArrayList<Admin> getAdminAccounts() {
        return adminAccounts;
    }

    public static void setAdminAccounts(ArrayList<Admin> adminAccounts) {
        AdminServ.adminAccounts = adminAccounts;
    }

    public static void setAllCourses(ArrayList<Course> allCourses) {
        AdminServ.allCourses = allCourses;
    }

    public static ArrayList<Course> getAllCourses() {
        return allCourses;
    }

    public AdminServ(){
        //把init()函数放到这里就会StackOverFlowError,原因未知
        //System.out.println("construtctor As");
    }

    public static void init(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse("08:10",dtf);
        LocalTime lt2 = LocalTime.parse("10:00",dtf);
        ArrayList<WeekDay> weekDays = new ArrayList<>();
        weekDays.add(WeekDay.MONDAY);
        weekDays.add(WeekDay.FRIDAY);
        //默认管理员
        adminAccounts.add(new Admin("超级管理员","admin","admin"));
        //Test
        allCourses.add(new Course("高等数学","苏绿","明德楼B5202",8.5,lt,lt2, weekDays));
        allCourses.add(new Course("大学物理实验","高超","图海楼333",5.5,lt,lt2, weekDays));
        allCourses.add(new Course("程序设计基础","跟上赵叔的节奏","明德楼B2301",4,lt,lt2, weekDays));
        allCourses.add(new Course("大学物理","莫莫","钟海楼05031",4,lt,lt2, weekDays));
        allCourses.add(new Course("大学英语读写I","卓超","钟海楼04033",2,lt,lt2, weekDays));
        allCourses.add(new Course("大学英语听说I","吴Jafeng","兴教楼303",2,lt,lt2, weekDays));
        allCourses.add(new Course("定向越野","新华","体育馆",2,lt,lt2, weekDays));
        allCourses.add(new Course("大学心理健康","唐立平","钟海楼03020",2,lt,lt2, weekDays));
        allCourses.add(new Course("思想道德与法治","刘伟","钟海楼04024",2,lt,lt2, weekDays));
        allCourses.add(new Course("劳动教育","宋蕾","钟海楼05030",0,lt,lt2, weekDays));
    }

    public void run(){
        login();
    }

    public void login(){
        if (adminAccounts.isEmpty()){
            System.out.println("还没有任何管理员账号，请注册！");
            return;
        }
        boolean logined = false;
        System.out.println("========> 广东原神大学教务处 管理员登录 <========");
        System.out.println("(键入0退出系统)");
        while (true){
            System.out.println(" ");
            System.out.println("请输入账号：");
            String account = sc.next();
            if (account.equals("0")){ break;}
            System.out.println("请输入密码：");
            String password = sc.next();
            //待修改 链接数据库 and MD5校验

            Admin ad = getAdminAccountbyName(account);
            if (ad != null){
                if (ad.getLoginTimes() >= 3){
                    System.out.println("该账号多次登录失败，已经被冻结，请联系管理员！");
                    continue;
                }
                if (ad.getPassword().equals(password)){
                    currentLoginedAdmin = ad;
                    logined = true;
                    break;
                } else {
                    ad.setLoginTimes(ad.getLoginTimes()+1);
                    System.out.println("账号或密码错误！(Code 1)");
                }
            } else {
                System.out.println("账号或密码错误！(Code 2)");
            }
            if (logined){
                break;
            }
        }
        if (logined){
            Utils.printMessage("登陆成功，欢迎您！" + currentLoginedAdmin.getAdminName());
            this.start();
        }

    }

    //主菜单
    public void start(){
        while(quitFlag){
            System.out.println("========> 广东原神大学教务处 管理员界面 <========");
            System.out.println(" 1.添加学生  2.删除学生  3.修改学生  4.查询学生");
            System.out.println(" 5.添加课程  6.修改课程  7.查询课程  8.未知领域");
            System.out.println(" 0.退出系统");
            System.out.println("============================================");
            System.out.println(" 输入指令：");
            String in = sc.next();
            switch (in){
                case "1":
                    registerNewStudent();
                    break;
                case "2":
                    deleteStudent();
                    break;
                case "3":
                    modifyStudent();
                    break;
                case "4":
                    queryStudent();
                    break;
                case "5":
                    addNewCourse();
                    break;
                case "6":
                    modifyCourse();
                    break;
                case "7":
                    queryCourse();
                    break;
                case "8":
                    System.out.println("前面的区域，以后再来探索吧");
                    break;
                case "0":
                    System.out.println("===退出管理系统===");
                    quitFlag = false;
                    break;
                default:
                    System.out.println("Wrong Arguments!");
            }
        }
        quitFlag = true;
    }

    private void modifyCourse() {
        int page = 1;
        String courseID;
        Course c = null;
        while (true){
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            String[] r = Utils.pagedQuery(allCourses,5,page);
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            System.out.println("请输入你要的修改的课程的ID：");
            courseID = sc.next();
            if (courseID.equals("2") && page < (allCourses.size() / 5) + 1){
                page++;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }
            if (courseID.equals("1") && page > 1){
                page--;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }

            c = getCourseByID(courseID);
            if (c == null){
                System.out.println("你查找的课程不存在！");
            } else {
                break;
            }
        }
        String command;
        String value;
        System.out.println("输入 n 修改课程名称");
        System.out.println("输入 t 修改课程教师");
        System.out.println("输入 c 修改课程教室");
        System.out.println("输入 s 修改课程学分");
        System.out.println("输入 time 修改课程上课时间");
        System.out.println("输入 wd 修改上课星期");
        command = sc.next();
        switch (command){
            case "n":
                System.out.println("将课程名称修改为：");
                value = sc.next();
                c.setCourseName(value);
                break;
            case "t":
                System.out.println("将课程教师修改为：");
                value = sc.next();
                c.setCourseTeacher(value);
                break;
            case "c":
                System.out.println("将课程教室修改为：");
                value = sc.next();
                c.setCourseClassroom(value);
                break;
            case "s":
                while(true){
                    System.out.println("将课程学分修改为：");
                    value = sc.next();
                    if(Utils.requireLegalScore(value)){
                        c.setCourseScore(Double.parseDouble(value));
                        break;
                    } else {
                        System.out.println("出错了！请输入合法的数字。");
                    }
                }
                break;
            case "time":
                LocalTime start = null;
                LocalTime end = null;

                while (true){
                    try{
                        System.out.println("请输入课程的上课时间（mm:ss）：");
                        start = LocalTime.parse(sc.next(),dtf);
                        c.setStartTime(start);
                        break;
                    } catch (Exception e){
                        System.out.println("你输入的时间有误！请重试。");
                        continue;
                    }
                }

                while (true){
                    try{
                        System.out.println("请输入课程的下课时间（mm:ss）：");
                        end = LocalTime.parse(sc.next(),dtf);
                    } catch (Exception e){
                        System.out.println("你输入的时间有误！请重试。");
                        continue;
                    }

                    if (!end.isAfter(start)){
                        System.out.println("结束时间不能在开始时间之前！重新输入！");
                    } else {
                        c.setEndTime(end);
                        break;
                    }
                }
                break;
            case "wd":
                ArrayList<WeekDay> weekdays = new ArrayList<>();
                System.out.println("请输入上课的星期：");
                System.out.println("整数[0-6] : 星期日-星期六");
                System.out.println("提示：输入 -1 退出输入星期");
                while (true){
                    boolean quitFlag = false;
                    String weekdayInput = sc.next();
                    switch (weekdayInput){
                        case "1":
                            weekdays.add(WeekDay.MONDAY);
                            break;
                        case "2":
                            weekdays.add(WeekDay.TUESDAY);
                            break;
                        case "3":
                            weekdays.add(WeekDay.WEDNESDAY);
                            break;
                        case "4":
                            weekdays.add(WeekDay.THURSDAY);
                            break;
                        case "5":
                            weekdays.add(WeekDay.FRIDAY);
                            break;
                        case "6":
                            weekdays.add(WeekDay.SATURDAY);
                            break;
                        case "0":
                            weekdays.add(WeekDay.SUNDAY);
                            break;
                        case "-1":
                            if (weekdays.isEmpty()){
                                System.out.println("至少需要输入一天！");
                                continue;
                            } else {
                                quitFlag = true;
                                break;
                            }
                        default:
                            System.out.println("你输入的内容有误！");
                            break;
                    }
                    if (quitFlag){
                        c.setWeekdays(weekdays);
                        break;
                    }
                }
                break;
            default:
                System.out.println("未知指令，请重试！");
                break;
        }
        System.out.println("修改完成！课程信息如下：");
        System.out.println(c);

    }

    private void queryCourse() {
        int page = 1;
        int command = -1;
        while (true){
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            String[] r = Utils.pagedQuery(allCourses,5,page);
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            String courseID = sc.next();
            if (courseID.equals("2") && page < (allCourses.size() / 5) + 1){
                page++;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }
            if (courseID.equals("1") && page > 1){
                page--;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }
            if (courseID.equals("0")){
                System.out.println("退出查询......");
                break;
            }
            Course c = getCourseByID(courseID);
            if (c == null){
                System.out.println("你查找的课程不存在！");
            } else {
                System.out.println(c);
            }
        }
    }

    private void addNewCourse() {
        Course newCourse = new Course();
        System.out.println("请输入课程的名称：");
        newCourse.setCourseName(sc.next());
        System.out.println("请输入课程的教师：");
        newCourse.setCourseTeacher(sc.next());
        System.out.println("请输入课程的教室：");
        newCourse.setCourseClassroom(sc.next());

        LocalTime start = null;
        LocalTime end = null;

        while (true){
            try{
                System.out.println("请输入课程的上课时间（mm:ss）：");
                start = LocalTime.parse(sc.next(),dtf);
                newCourse.setStartTime(start);
                break;
            } catch (Exception e){
                System.out.println("你输入的时间有误！请重试。");
                continue;
            }
        }

        while (true){
            try{
                System.out.println("请输入课程的下课时间（mm:ss）：");
                end = LocalTime.parse(sc.next(),dtf);
            } catch (Exception e){
                System.out.println("你输入的时间有误！请重试。");
                continue;
            }

            if (!end.isAfter(start)){
                System.out.println("结束时间不能在开始时间之前！重新输入！");
            } else {
                newCourse.setEndTime(end);
                break;
            }
        }

        ArrayList<WeekDay> weekdays = new ArrayList<>();
        System.out.println("请输入上课的星期：");
        System.out.println("整数[0-6] : 星期日-星期六");
        System.out.println("提示：输入 -1 退出输入星期");
        while (true){
            boolean quitFlag = false;
            String weekdayInput = sc.next();
            switch (weekdayInput){
                case "1":
                    weekdays.add(WeekDay.MONDAY);
                    break;
                case "2":
                    weekdays.add(WeekDay.TUESDAY);
                    break;
                case "3":
                    weekdays.add(WeekDay.WEDNESDAY);
                    break;
                case "4":
                    weekdays.add(WeekDay.THURSDAY);
                    break;
                case "5":
                    weekdays.add(WeekDay.FRIDAY);
                    break;
                case "6":
                    weekdays.add(WeekDay.SATURDAY);
                    break;
                case "0":
                    weekdays.add(WeekDay.SUNDAY);
                    break;
                case "-1":
                    if (weekdays.isEmpty()){
                        System.out.println("至少需要输入一天！");
                        continue;
                    } else {
                        quitFlag = true;
                        break;
                    }
                default:
                    System.out.println("你输入的内容有误！");
                    break;
            }
            if (quitFlag){
                break;
            }
        }

        newCourse.setWeekdays(weekdays);
        allCourses.add(newCourse);
        System.out.println("添加课程成功：");
        System.out.println(newCourse);
    }

    private void queryStudent() {
        int page = 1;
        String studentID;
        Student stu = null;
        while (true){
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            String[] r = Utils.pagedQuery(StudentServ.getStudentAccounts(),5,page);
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            System.out.println("请输入你要查询的学生的学号：");
            studentID = sc.next();
            if (studentID.equals("0")){
                break;
            }
            if (studentID.equals("2") && page < (allCourses.size() / 5) + 1){
                page++;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }
            if (studentID.equals("1") && page > 1){
                page--;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }

            stu = getStudentbyID(studentID);
            if (stu == null){
                System.out.println("你输入的学生不存在！");
            } else {
                System.out.println("你要查询的学生信息如下：");
                System.out.println(stu);
                System.out.println(stu.getChosenCoursesString());
                break;
            }
        }
    }

    private void modifyStudent() {
        int page = 1;
        boolean isQuit = false;
        String studentID;
        Student c = null;
        while (true){
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            String[] r = Utils.pagedQuery(StudentServ.getStudentAccounts(),5,page);
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            System.out.println("请输入你要的修改的学生的学号：");
            studentID = sc.next();
            if (studentID.equals("0")){
                isQuit = true;
                break;
            }
            if (studentID.equals("2") && page < (allCourses.size() / 5) + 1){
                page++;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }
            if (studentID.equals("1") && page > 1){
                page--;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }

            c = getStudentbyID(studentID);
            if (c == null){
                System.out.println("你查找的学生不存在！");
            } else {
                break;
            }
        }
        if(isQuit){
            return;
        }
        String command;
        String value;
        System.out.println("输入 n 修改学生姓名");
        System.out.println("输入 a 修改学生年龄");
        System.out.println("输入 p 修改学生密码");
        System.out.println("输入 s 修改学生性别");
        command = sc.next();
        switch (command){
            case "n":
                System.out.println("将学生姓名修改为：");
                value = sc.next();
                c.setStudentName(value);
                break;
            case "a":
                while (true){
                    System.out.println("将学生年龄修改为：");
                    value = sc.next();
                    if (Utils.requireLegalAge(value)){
                        c.setStudentAge(value);
                        break;
                    } else {
                        System.out.println("年龄不合法，请重试！");
                    }
                }
                break;
            case "p":
                System.out.println("将学生密码修改为：");
                value = sc.next();
                //也许可以设置密码安全要求
                c.setPassword(value);
                break;
            case "s":
                while (true){
                    System.out.println("将学生性别修改为：");
                    value = sc.next();
                    if (Utils.requireLegalGender(value)){
                        c.setStudentSex(value.toCharArray()[0]);
                        break;
                    } else {
                        System.out.println("性别不合法，请重试！");
                    }
                }
                break;
            default:
                System.out.println("未知指令，请重试！");
        }
        System.out.println("修改完成！学生信息如下：");
        System.out.println(c);
    }

    private void registerNewStudent() {
        Student stu = new Student();
        Utils.printMessage("请输入学生姓名：");
        stu.setStudentName(sc.next());

        while (true){
            Utils.printMessage("请输入学生年龄：");
            String age = sc.next();
            if(Utils.requireLegalAge(age)){
                stu.setStudentAge(age);
                break;
            } else {
                Utils.printMessage("输入的数据有误！重试一次。");
            }
        }

        while(true){
            Utils.printMessage("请输入学生性别：");
            String in = sc.next();
            if (Utils.requireLegalGender(in)){
                stu.setStudentSex(in.charAt(0));
                break;
            } else {
                Utils.printMessage("性别不合法，请重新输入！");
            }

        }

        Utils.printMessage("请输入学生密码：");
        stu.setPassword(sc.next());

        stu.setStudentID(Utils.generateStudentID());

        StudentServ.getStudentAccounts().add(stu);
        Utils.printMessage("添加学生成功！\n" + stu.toString());
    }

    private void deleteStudent(){
        int page = 1;
        String studentID;
        Student stu = null;
        while (true){
            System.out.println();
            System.out.println("------- 第" + page + "页 -------");
            String[] r = Utils.pagedQuery(StudentServ.getStudentAccounts(),5,page);
            if (r != null) {
                for (String s : r) {
                    System.out.println(s);
                }
            }
            System.out.println("------- 第" + page + "页 -------");
            System.out.println("输入 1 返回上一页 | 输入 2 进入下一页 | 输入 0 退出查询");
            System.out.println("请输入你要删除的学生的学号：");
            studentID = sc.next();
            if (studentID.equals("0")){
                break;
            }
            if (studentID.equals("2") && page < (allCourses.size() / 5) + 1){
                page++;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }
            if (studentID.equals("1") && page > 1){
                page--;
                continue;//如果输入的是翻页指令，直接跳过下面的代码
            }

            stu = getStudentbyID(studentID);
            if (stu == null){
                System.out.println("你输入的学生不存在！");
            } else {
                StudentServ.getStudentAccounts().remove(stu);
                System.out.println("此学生删除成功！");
                break;
            }
        }
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
        for (Admin a : adminAccounts){
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
        for (Course c:allCourses){
            if(c.getCourseId().equals(id)){
                return c;
            }
        }
        return null;
    }

}
