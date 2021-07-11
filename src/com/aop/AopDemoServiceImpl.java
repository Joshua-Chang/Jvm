package com.aop;

public class AopDemoServiceImpl implements AopDemoService {
    @Override
    public void sayHello() {
        System.out.println("hello : AopDemoServiceImpl");
    }
}
