package com.aop.proxy;

import com.aop.AopDemoService;
import com.aop.AopDemoServiceImpl;
import com.aop.proxy.dynamicproxy.DynamicHandler;
import com.aop.proxy.staticproxy.AopDemoProxy;

import java.lang.reflect.Proxy;

public class JavaProxyDemo {
    public static void main(String[] args) {

        AopDemoService staticProxy=new AopDemoProxy();/*静态代理就是代理这种设计模式*/
        staticProxy.sayHello();

        AopDemoService dynamicProxy = (AopDemoService) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{AopDemoService.class},
                new DynamicHandler(new AopDemoServiceImpl()));/*动态在接口的实现类的方法上修改*/
        dynamicProxy.sayHello();
        /*静态代理和动态代理都需要接口实现类*/
    }
}
