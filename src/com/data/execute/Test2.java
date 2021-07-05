package com.data.execute;
//栈帧-操作数栈
public class Test2 {
    public int add(int a, int b) {
        int c = a + b;
        return a + b + c;
        //slot
/** 操作数栈：用来存放方法运行间的指令操作数据
 0: iload_1     导入int 参数1 对应局部变量表的a
 1: iload_2
 2: iadd
 3: istore_3    存入int 参数3 对应c
 4: iload_1
 5: iload_2
 6: iadd        int相加 a+b
 7: iload_3
 8: iadd        a+b+c
 9: ireturn     返回int
 LineNumberTable:
 line 5: 0
 line 6: 4
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      10     0  this   Lcom/data/execute/Test2;
 0      10     1     a   I
 0      10     2     b   I
 4       6     3     c   I
 */
    }
    public static void main(String[] args) {
        Test2 t=new Test2();
        int ret = t.add(1, 2);
        System.out.println("ret="+ret);
    }
}
