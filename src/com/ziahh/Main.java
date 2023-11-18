package com.ziahh;

import com.ziahh.modules.DataReader;
import com.ziahh.modules.DataWriter;
import com.ziahh.modules.MainServ;

public class Main {

    public static void main(String[] args) throws Exception {
        //DataWriter dw = new DataWriter();
        MainServ ms = new MainServ();
        //DataReader dr = new DataReader();
        ms.run();
    }
}
