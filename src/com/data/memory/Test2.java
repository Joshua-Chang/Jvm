package com.data.memory;

public class Test2 {
    private int num=0;
    private int callMe(int a,int b){
        num++;
        return callMe(a, b);
    }

    public static void main(String[] args) {
        Test2 test2=new Test2();
        try {
            test2.callMe(1,2);
        } catch (Throwable e) {
            System.out.println(""+test2.num);
            e.printStackTrace();
        }
    }
}
