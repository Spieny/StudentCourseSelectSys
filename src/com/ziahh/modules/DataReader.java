package com.ziahh.modules;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class DataReader {

    private InputStream in = new FileInputStream("src/students.data");

    public DataReader() throws Exception {
        byte[] b = in.readAllBytes();
        String r = new String(b);
        System.out.println(r);
    }
}
