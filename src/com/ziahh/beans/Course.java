package com.ziahh.beans;

import com.ziahh.Utils;
import com.ziahh.enums.WeekDay;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Course implements Serializable ,Comparable<Course>{

    private String courseId;
    private String courseName;
    private String courseTeacher;
    private String courseClassroom;
    private double courseScore;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<WeekDay> weekdays;
    private static final long serialVersionUID = 8203570095223467865L;

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

    public ArrayList<WeekDay> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(ArrayList<WeekDay> weekdays) {
        this.weekdays = weekdays;
    }

    public Course(String courseName, String courseTeacher, String courseClassroom, double courseScore, LocalTime startTime, LocalTime endTime, ArrayList<WeekDay> weekdays) {
        this.courseId = Utils.generateCourseID();
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseClassroom = courseClassroom;
        this.courseScore = courseScore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekdays = weekdays;
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
                + "教师：" + '\'' + courseTeacher + '\''+ "\n"
                + "学分：" + '\'' + courseScore + '\''+ "\n"
                + "上课时间：" + '\'' + startTime + "——" + endTime + '\''+ "\n"
                + "         '" + getWeekdaysString() + '\''+ "\n"
                + "=========================";
    }

    private String getWeekdaysString(){
        StringJoiner sb = new StringJoiner(",");
        for (WeekDay w : weekdays){
            sb.add(w.toString());
        }
        return sb.toString();
    }

    public String toStringLine(){
        return "(" + courseId + ") [" + courseScore + "]" + courseName +  " , " + courseClassroom + " ,教师：" + courseTeacher;
    }

    public String toSchduleLine(){
        return courseName + "|" + courseTeacher + "|" + courseClassroom + "|" + startTime + "——" + endTime;
    }

    @Override
    public int compareTo(@NotNull Course o) {
        return startTime.compareTo(o.startTime);
    }
}
