package com.aop.proxy.staticproxy;

import com.aop.AopDemoService;
import com.aop.AopDemoServiceImpl;

/*静态代理*/
public class AopDemoProxy implements AopDemoService {
    private AopDemoService aopDemoService=new AopDemoServiceImpl();
    @Override
    public void sayHello() {
        System.out.println("start");
        aopDemoService.sayHello();
        System.out.println("end");
    }
}
