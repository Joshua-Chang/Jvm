package com.aop.cglib;

import com.aop.AopDemoService;
import com.aop.AopDemoServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*Code Generation Library*/
/*CGLIB创建一个继承实现类的子类，用Asm库动态修改子类的代码来实现AOP效果*/
public class CglibDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();//Enhancer既能够代理普通的class，也能够代理接口
        //Enhancer创建一个被代理对象的子类并且拦截所有的方法调用（包括从Object中继承的toString和hashCode方法）。不能够拦截final方法
        enhancer.setSuperclass(AopDemoServiceImpl.class);/*指定父类/接口*/
        enhancer.setCallback(new MethodInterceptor() {//指定拦截的方法
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("start");
                Object obj = methodProxy.invokeSuper(o, objects);
                System.out.println("end cglib");
                return obj;
            }
        });
        AopDemoService aopDemoService = (AopDemoService) enhancer.create();
        aopDemoService.sayHello();
    }
}
