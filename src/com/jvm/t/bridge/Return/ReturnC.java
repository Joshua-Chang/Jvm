package com.jvm.t.bridge.Return;

public class ReturnC extends ReturnP{
    @Override
    public void myReturn() {
        System.out.println("返回类型的子类");
    }
    public static void sameStatic(){
        System.out.println("返回类型的子类 相同的静态方法");
    }
}
