package com.jvm.t.bridge;

import com.jvm.t.bridge.Return.ReturnC;

public class Test {
    public static void main(String[] args) {
        C c = new C();
        c.test();
        ReturnC.sameStatic();//子类有同样地静态方法，父类的被隐藏
    }
}
