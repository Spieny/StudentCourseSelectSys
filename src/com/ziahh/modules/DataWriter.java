package com.ziahh.modules;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DataWriter {
    private OutputStream out = new FileOutputStream("src/students.data",true);

    public DataWriter() throws Exception {
        byte[] bytes = "这里放一行students的toString()\n测试测试测试".getBytes();
        out.write(bytes);

        out.close();
    }
}
