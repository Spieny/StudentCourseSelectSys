package com.ziahh.beans;

import com.ziahh.Utils;

import java.util.ArrayList;

public class Student {

    private String studentName = "default";
    private String studentID = "00000000";
    private String studentAge = "100";
    private char studentSex = '男';//性别
    private double score = 0.0; //学分
    private String password = "666666"; //默认密码7777777 身份证后六位
    private ArrayList<Course> chosenCourses = new ArrayList<>();
    //待添加......


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
        return "=======[学生基本消息]=======\n"
                + "姓名：'" + studentName + '\'' + "\n"
                + "学号：'" + studentID + '\''+ "\n"
                + "年龄：'" + studentAge + '\'' + "\n"
                + "性别：" + studentSex + '\''+ "\n"
                + "学分：" + score + "\n" +
                "=========================";
    }

    public String getDataString(){
        return studentName + "," + studentID + "," +studentAge + "," +studentSex + "," + score;
    }

    public String toStringLine(){
        return "(" + studentID + ") " + studentName  + " " + studentSex;
    }
}
