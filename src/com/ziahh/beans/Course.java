package com.ziahh.beans;

import com.ziahh.Utils;
import com.ziahh.enums.WeekDay;

import java.io.Serializable;
import java.time.LocalTime;

public class Course implements Serializable {

    private String courseId;
    private String courseName;
    private String courseTeacher;
    private String courseClassroom;
    private double courseScore;
    private LocalTime startTime;
    private LocalTime endTime;
    private WeekDay weekday;

    public WeekDay getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekDay weekday) {
        this.weekday = weekday;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Course(String courseName, String courseTeacher, String courseClassroom, double courseScore, LocalTime startTime, LocalTime endTime,WeekDay day) {
        this.courseId = Utils.generateCourseID();
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseClassroom = courseClassroom;
        this.courseScore = courseScore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekday = day;
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
