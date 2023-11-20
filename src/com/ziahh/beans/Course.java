package com.ziahh.beans;

import com.ziahh.Utils;

import java.io.Serializable;

public class Course implements Serializable {

    private String courseId;
    private String courseName;
    private String courseTeacher;
    private String courseClassroom;
    private double courseScore;

    /**
     * id 由系统自动生成且唯一
     * @param course_name 课程名
     * @param course_teacher 课程老师
     * @param course_classroom 课程教室地点
     */
    public Course(String course_name, String course_teacher, String course_classroom,double score) {
        this.courseName = course_name;
        this.courseTeacher = course_teacher;
        this.courseClassroom = course_classroom;
        this.courseId = Utils.generateCourseID();
        this.courseScore = score;
    }

    public double getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(double courseScore) {
        this.courseScore = courseScore;
    }

    public Course() {
        this.courseId = Utils.generateCourseID();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getCourseClassroom() {
        return courseClassroom;
    }

    public void setCourseClassroom(String courseClassroom) {
        this.courseClassroom = courseClassroom;
    }

    @Override
    public String toString() {
        return "=======[课程基本信息]=======\n"
                + "ID：'" + courseId + '\'' + "\n"
                + "名称：'" + courseName+ '\''+ "\n"
                + "教室：'" + courseClassroom + '\'' + "\n"
                + "教师：" + courseTeacher + '\''+ "\n"
                + "学分：" + courseScore + '\''+ "\n"
                + "=========================";
    }

    public String toStringLine(){
        return "(" + courseId + ") [" + courseScore + "]" + courseName +  " , " + courseClassroom + " ,教师：" + courseTeacher;
    }
}
