package com.ziahh.beans;

import com.ziahh.Utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private String studentName = "default";
    private String studentID = "00000000";
    private String studentAge = "100";
    private char studentSex = '男';//性别
    private double score = 0.0; //学分
    private String password = "666666"; //默认密码身份证后六位
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
        return "(" + studentID + ") " + studentName  + " " + studentSex;
    }

    public String getChosenCoursesString(){
        StringBuilder sb = new StringBuilder();
        sb.append("=========== 学生").append(getStudentName()).append("已选课程 ===========\n");
        if (this.chosenCourses.isEmpty()){
            sb.append("你还没有选择任何课程\n");
        } else {
            for (Course e:chosenCourses){
                sb.append(e.toStringLine()).append("\n");
            }
        }
        sb.append("=========== 学生").append(getStudentName()).append("已选课程 ===========");
        return sb.toString();
    }

    private void updateScore(){
        for (Course c:chosenCourses){
            this.score += c.getCourseScore();
        }
    }

    public void removeCourse(Course c) {
        this.chosenCourses.remove(c);
        updateScore();
    }
}
