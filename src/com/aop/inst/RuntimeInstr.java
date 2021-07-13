package com.aop.inst;

import java.lang.management.ManagementFactory;

public class RuntimeInstr {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];

        //打印当前Pid
        System.out.println("pid:"+s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            sayHello();
        }
    }

    public static void sayHello() {
        System.out.println("hello");
    }
}
