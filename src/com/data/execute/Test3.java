package com.data.execute;

//方法分派 重载args问题
public class Test3 {
    public void m1(Object a) {
        System.out.println("Object " + a);
    }
    public void m1(Integer a) {
        System.out.println("Integer " + a);
    }
    public void m1(int a) {
        System.out.println("int " + a);
    }

    public void m1(String a) {//静态分派：重载
        System.out.println("string " + a);
    }

    public static class Child extends Test3 {
//        public void m1(int a) {
//            System.out.println("child int "+a);
//        }
        public void m1(int a,int b) {
            System.out.println("child a+b int "+a+b);
        }
    }

    public static void main(String[] args) {
//        Test3 t = new Test3();
        Test3 t = new Child();//动态分派：重写。看实例是child还是parent
//        t.m1("str");
        t.m1(5);//当前实例有这个方法就调，没有就调父类的。父类方法可以重载，重载方法存在范围覆盖时：int方法没有则调用Integer再没有则Obj。从小范围到大范围。
//        t.m1(Integer.valueOf(5));
//        t.m1(new Object());重载方法存在参数范围覆盖时，优先调用精准的小范围。object是最大的范围，因此只能调用object这一个方法。
        //第一个维度先看动态分派是子类还是父类实例的方法
        //第二个维度再看静态分派匹配同名不同参数的方法
    }
}
