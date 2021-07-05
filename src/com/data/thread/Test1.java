package com.data.thread;

public class Test1 {
    public static void main(String[] args) throws Exception{
        A a=new A();
        Thread t1=new Thread(new MyThread(a,"t11"));
        Thread t2=new Thread(new MyThread(a,"t21"));
        t1.start();
        t2.start();
        /*三个线程都在跑 main t1 t2 无法控制谁先谁后*/
        t1.join();/*t1 join到main：当前main等t1执行后再恢复执行*/
        t2.join();
        System.out.println("A.a == "+a.getA());


//        面试题
        Integer x=1;
//      封装成对象  x=Integer.valueOf(1);
        if (x==2){ }//接封装成基本类型  Integer.valueOf(x)==2
    }
}
