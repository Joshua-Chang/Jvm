package com.jvm.t.bridge.Return;

public class ReturnP {
    public void myReturn(){
        System.out.println("返回类型的父类");
    }
    public static void sameStatic(){//子类也有，父类的会被隐藏
        System.out.println("返回类型的父类 的静态方法");
    }
}
