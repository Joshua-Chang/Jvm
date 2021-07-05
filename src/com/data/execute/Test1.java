package com.data.execute;

public class Test1 {
    public int add(int a, int b) {
//    public static int add(int a,int b){
        int c = a + b;
        return a + b + c;
        //slot 槽位
        //0-this 1-a 2-b 3-c
/**
 *
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      10     0  this   Lcom/data/execute/Test1;
 0      10     1     a   I
 0      10     2     b   I
 4       6     3     c   I
 静态方法不传this
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0      10     0     a   I
 0      10     1     b   I
 4       6     2     c   I
 */
    }

    public static void main(String[] args) {
        {//用方法块包裹住局部变量
            byte[] bs = new byte[2 * 1024 * 1024];//局部变量bs的作用域为其所在方法块
        //  bs=null;//方案二：bs指向堆的空间置空
        }//bs出了作用域就可以被回收
        //  int a = 5;//方案一：覆盖bs槽位
        System.gc();//并不一定保证GC

        //slot槽位情况
        //0--args
        //1--bs-----指向堆空间放着2M数据   gc时本地变量表还在用bs
        //1--a将槽位1给局部变量a复用

//        System.out.println("total:" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0);
//        System.out.println("free:" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0);
//        System.out.println("max:" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0);
    }
}
