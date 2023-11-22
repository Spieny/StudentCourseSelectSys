package com.ziahh.modules;

import com.ziahh.beans.Admin;

import java.io.*;

/*
  使用序列化流读取储存学生和管理员的文件
 */

public class DataWriter {

    public static void writeAll(){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/admins.data"));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("src/courses.data"));
        ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("src/students.data"))
        ) {
            oos.writeObject(AdminServ.getAdminAccounts());
            oos2.writeObject(AdminServ.getAllCourses());
            oos3.writeObject(StudentServ.getStudentAccounts());
            System.out.println("本地数据已保存");
        } catch (IOException e) {
            System.out.println("写入失败:" + e.getMessage());
        }
    }
}
