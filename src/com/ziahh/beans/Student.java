package com.ziahh.beans;

import com.ziahh.Utils;

import java.io.Serializable;
import java.util.ArrayList;

import static com.ziahh.Utils.WEEKDAYS;

public class Student implements Serializable {

    private String studentName = "default";
    private String studentID = "00000000";
    private String studentAge = "100";
    private char studentSex = '男';//性别
    private double score = 0.0; //学分
    private String password = "666666"; //默认密码
    private ArrayList<Course> chosenCourses = new ArrayList<>();
    private static final long serialVersionUID = -5225586180334516497L;
    //待添加......
    private int loginTimes = 0;

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Student(String studentName, String studentAge, char studentSex, String password, ArrayList<Course> chosenCourses) {
        this.studentName = studentName;
        this.studentID = Utils.generateStudentID();
        this.studentAge = studentAge;
        this.studentSex = studentSex;
        this.password = password;
        this.chosenCourses = chosenCourses;
    }

    public Student() {
    }

    //添加课程
    public void addCourse(Course c){
        this.chosenCourses.add(c);
    }

    public void setChosenCourses(ArrayList<Course> chosenCourses) {
        this.chosenCourses = chosenCourses;
    }

    //获取学生个人课表
    public ArrayList<Course> getChosenCourses() {
        return chosenCourses;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public char getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(char studentSex) {
        this.studentSex = studentSex;
    }

    @Override
    public String toString() {
        updateScore();
        return "===========[学生基本消息]===========\n"
                + "姓名：'" + studentName + '\'' + "\n"
                + "学号：'" + studentID + '\''+ "\n"
                + "年龄：'" + studentAge + '\'' + "\n"
                + "性别：" + studentSex + '\''+ "\n"
                + "学分：" + score + "\n" +
                "====================================";
    }

    public String getDataString(){
        updateScore();
        return studentName + "," + studentID + "," +studentAge + "," +studentSex + "," + score;
    }

    public String toStringLine(){
        updateScore();
        if (loginTimes >= 3){
            return "(" + studentID + ") " + studentName  + " " + studentSex + " [账号异常]";
        } else {
            return "(" + studentID + ") " + studentName  + " " + studentSex;
        }
    }

    public String getChosenCoursesString(){
        updateScore();
        StringBuilder sb = new StringBuilder();
        sb.append("================ 学生").append(getStudentName()).append("已选课程 ================\n");
        if (this.chosenCourses.isEmpty()){
            sb.append("你还没有选择任何课程\n");
        } else {
            for (Course e:chosenCourses){
                sb.append(e.toStringLine()).append("\n");
            }
        }
        sb.append("================ 学生").append(getStudentName()).append("已选课程 ================");
        return sb.toString();
    }

    private void updateScore(){
        score = 0;
        for (Course c:chosenCourses){
            this.score += c.getCourseScore();
        }
    }

    public void removeCourse(Course c) {
        this.chosenCourses.remove(c);
        updateScore();
    }

    public void showCourseSchedule(){
        StringBuilder schedule = new StringBuilder();
        schedule.append("===========================================\n");
        for (int i = 0; i < WEEKDAYS.length; i++) {
            ArrayList<Course> todayCourses = new ArrayList<>();
            schedule.append(WEEKDAYS[i]).append(":\n");
            for (Course c:chosenCourses){
                if (c.getWeekdays().contains(WEEKDAYS[i])){
                    todayCourses.add(c);
                }
            }
            todayCourses.sort(Course::compareTo);
            if (!todayCourses.isEmpty()){
                for (Course c : todayCourses){
                    schedule.append(c.toSchduleLine()).append("\n");
                }
            } else {
                schedule.append("-今日无课-\n");
            }
        }
        schedule.append("===========================================\n");
        System.out.println(schedule);
    }

}
