package com.data.memory;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    private byte [] bs=new byte[2*1024*1024];

    public static void main(String[] args) {
        List<Test1>list=new ArrayList<Test1>();
        int num=0;
        try {
            while (true){
                list.add(new Test1());
                num++;
            }
        } catch (Throwable e) {//此处catch的不是Exception而是Error因此用Throwable
            e.printStackTrace();
        }
    }
}
