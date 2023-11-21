package com.ziahh.modules;

import com.ziahh.beans.Admin;
import com.ziahh.beans.Course;
import com.ziahh.beans.Student;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/*
  使用序列化流读取储存学生和管理员的文件
 */
public class DataReader {

    public static void readAll(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/admins.data"));
             ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream("src/students.data"));
             ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("src/courses.data"))
        ) {
            ArrayList<Admin> inputAdminAccounts = (ArrayList<Admin>) ois.readObject();
            ArrayList<Course> inputCourses = (ArrayList<Course>) ois2.readObject();
            ArrayList<Student> inputStudents= (ArrayList<Student>) ois3.readObject();

            AdminServ.setAdminAccounts(inputAdminAccounts);
            AdminServ.setAllCourses(inputCourses);
            StudentServ.setStudentAccounts(inputStudents);
            System.out.println("读取本地数据成功！");

        } catch (Exception e) {
            System.out.println("写入失败:" + e.getMessage());
        }
    }
}
