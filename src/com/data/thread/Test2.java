package com.data.thread;
/*多线程交叉赋值*/
public class Test2 {
    private static int a=0;
    private static int b=0;
    private static int x=0;
    private static int y=0;

    //t1先运行完，然后再运行t2：               a=1,x=0;b=2,y=1     abxy=1201
    //t2先运行完，然后再运行t1：               b=2,y=0;a=1,x=2     abxy=1220
    //t1,t2交叉，t1先运行一行，t2完整运行两行：  a=1;b=2,y=1;x=2     abxy=1221
    //t1,t2交叉，t1先运行一行，t2运行一行：     a=1;b=2;x=2;y=1     abxy=1221
    //t1,t2交叉，t2先运行一行，t1完整运行两行：  b=2;a=1,x=2;y=1     abxy=1221
    //t1,t2交叉，t2先运行一行，t1运行一行：     b=2;a=1;y=1;x=2     abxy=1221
    //以上是为发生指令重排时，若发生指令重排则在上面的基础上t1,t2各自的第一/第二行有可能调换
    //当重排时有可能发生x==0&&y==0

    public static void testResort()throws Exception{
        a=0;
        b=0;
        x=0;
        y=0;
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (System.currentTimeMillis()%2==0){
                        Thread.sleep(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                a=1;
                x=b;
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (System.currentTimeMillis()%2==1){
                        Thread.sleep(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                b=2;
                y=a;
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("x="+x+"   y="+y);
    }
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 1000; i++) {
            testResort();
        }
    }
}
