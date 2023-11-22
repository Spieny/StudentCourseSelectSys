package com.ziahh;

import com.ziahh.beans.Course;
import com.ziahh.enums.WeekDay;
import com.ziahh.modules.AdminServ;
import com.ziahh.modules.StudentServ;
import com.ziahh.beans.Student;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    public static final WeekDay[] WEEKDAYS = {WeekDay.MONDAY,WeekDay.TUESDAY,WeekDay.WEDNESDAY,WeekDay.THURSDAY,WeekDay.FRIDAY,WeekDay.SATURDAY,WeekDay.SUNDAY};

    private static ArrayList<String> arr = new ArrayList<>();
    private static final Random r = new Random();

    /**
     * 检测一个学生是否已经选择一门课程，基于课程id的唯一性编写。
     * @param stu 学生
     * @param c0 要判断的课程
     * @return 已选择返回真，反之返回假
     */
    public static boolean isStudenetChosenSpecificCourse(Student stu,Course c0){
        String courseID = c0.getCourseId();
        for (Course c1 : stu.getChosenCourses()){
            if (c1.getCourseId().equals(courseID)){
                return true;
            }
        }
        return false;
    }

    /**
     * 分页查询，特别支持课程表，学生表的分页查询
     * @param list
     * @param size 每一页展示的数量
     * @param page 选择第几页+
     * @return
     */
    public static String[] pagedQuery(ArrayList<?> list,int size,int page){
        //空白就抛异常，未来再添加
        if (list.isEmpty()){ return null;}

        int maxPage = 0;
        if (list.size() % size == 0){
            maxPage = list.size() / size;
        } else {
            maxPage = (list.size() / size) + 1;
        }

        //如果页数超过最大页数，返回最大页数的内容
        if (page > maxPage){return pagedQuery(list,size,maxPage);}

        //如果页数小于1，返回最大页数的内容
        if (page < 1){return pagedQuery(list,size,1);}

        //如果查询的是最后一页，对字符串数组的大小作特殊处理
        String[] results = null;
        if (page == maxPage){
            if (list.size() % size == 0){
                results = new String[size];
            } else {
                results = new String[list.size() % size];
            }

        } else {
            results = new String[size];
        }

        //如果是学生分页查询
        if (list.get(0) instanceof Student){
            int i = (page - 1) * size; // 10个 size 3
            for (int j = 0; j < size; j++) {
                if (i == list.size()){
                    break;
                } else {
                    Student c = (Student) list.get(i);
                    results[j] = c.toStringLine();
                    i++;
                }
            }
            return results;
        }

        //如果是课程分页查询
        if (list.get(0) instanceof Course){
            int i = (page - 1) * size; // 10个 size 3
            for (int j = 0; j < size; j++) {
                if (i == list.size()){
                    break;
                } else {
                    Course c = (Course) list.get(i);
                    results[j] = c.toStringLine();
                    i++;
                }
            }
            return results;
        }
        return null;
    }

    /**
     * 生成一个不重复的课程ID，eg.c0xxxx;
     * @return
     */
    public static String generateCourseID(){
        //课程爆满了！！！
        if (AdminServ.getAllCourses().size() >= 9999){
            return null;
        }
        boolean isDuplicated = false;
        String genid = "c0" + (r.nextInt(1000, 9999));

        //检查重复 如重复则递归调用函数，直至返回不重复的结果
        for (Course c : AdminServ.getAllCourses()) {
            if (genid.equals(c.getCourseId())) {
                isDuplicated = true;
                break;
            }
        }
        if (isDuplicated){
            return generateCourseID();
        } else {
            return genid;
        }
    }
    /**
     * 生成一个不重复的学生ID，eg.2023xxxx;
     * @return
     */
    public static String generateStudentID() {
        if (StudentServ.getStudentAccounts().size() >= 9999){
            return null;
        }
        boolean isDuplicated = false;
        String genid = "2023" + (r.nextInt(1000, 9999));

        //检查重复 同上
        for (Student stu : StudentServ.getStudentAccounts()) {
                if (genid.equals(stu.getStudentID())) {
                    isDuplicated = true;
                    break;
                }
        }
        if (isDuplicated) {
            return generateStudentID();
        } else {
            return genid;
        }
    }

    /**
     * 一个秘制的打印消息的方法，单纯为了在前后多打两行空行
     * @param s
     */
    public static void printMessage(String s){
        System.out.println(" ");
        System.out.println(s);
        System.out.println(" ");
    }

    //测试用的
    /*public static void printarray(){
        arr.sort((o1,o2)-> Integer.parseInt(o2) - Integer.parseInt(o1));
        System.out.println(arr);
    }*/

    /**
     * 检测输入的内容是否为合法年龄（1-150）
     * @param in
     * @return
     */
    public static boolean requireLegalAge(String in){
        int result;
        try{
            result = Integer.parseInt(in);
        } catch (Exception e){
            return false;
        }
        if(result < 0 || result > 150){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串能否被正确的转化为Double，返回布尔值
     * @param in 输入的文本
     * @return
     */
    public static boolean requireLegalScore(String in){
        double result;
        try{
            result = Double.parseDouble(in);
        } catch (Exception e){
            return false;
        }
        if(result < 0){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检测输入的内容是否为男或女
     * @param in
     * @return
     */
    public static boolean requireLegalGender(String in){
        char c = in.charAt(0);
        if(c == '男' || c =='女'){
            return true;
        }
        return false;
    }

}
