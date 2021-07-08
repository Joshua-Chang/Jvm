package com.jvm.t.bridge;

import com.jvm.t.bridge.Return.ReturnC;

public class C extends P{
    public ReturnC test() {
        return new ReturnC();
    }
//子类定义了一个与父类参数类型相同的方法，其返回类型为父类方法返回类型的子类，
//那么 Java 编译器也会为其生成桥接方法。(jvm判定不是重写，java语法判定为重写，因此要桥接)
//子类和父类的非私有非静态方法同名 同参 同返回值类型时，JVM才判定位重写。但java语法判定重写还支持返回值类型的派生类型。
//    public ReturnC test(Test a) { 重载也可以作用于继承方法：在子类直接重载父类方法，或在子类重载已被继承过来的方法都可以。
//        return new ReturnC();
//    }

}
//public class com.jvm.t.bridge.C extends com.jvm.t.bridge.P {
//public com.jvm.t.bridge.C();父构造
//        Code:
//        0: aload_0
//        1: invokespecial #1                  // Method com/jvm/t/bridge/P."<init>":()V
//        4: return
//
//public com.jvm.t.bridge.Return.ReturnC test();
//    flags: (0x0001) ACC_PUBLIC 复写原方法
//        Code:
//        0: new           #2                  // class com/jvm/t/bridge/Return/ReturnC
//        3: dup
//        4: invokespecial #3                  // Method com/jvm/t/bridge/Return/ReturnC."<init>":()V
//        7: areturn
//
//public com.jvm.t.bridge.Return.ReturnP test();
//  flags: (0x1041) ACC_PUBLIC, ACC_BRIDGE, ACC_SYNTHETIC 桥接方法
//        Code:
//        0: aload_0
//        1: invokevirtual #4                  // Method test:()Lcom/jvm/t/bridge/Return/ReturnC;
//        4: areturn
//        }
