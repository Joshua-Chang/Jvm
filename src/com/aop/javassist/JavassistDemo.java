package com.aop.javassist;

import com.aop.AopDemoServiceImpl;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistDemo {
    public static void main(String[] args) throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.aop.AopDemoServiceImpl"); //NotFoundException的话用下边的方法
//        classPool.insertClassPath(new ClassClassPath(AopDemoServiceImpl.class));
//        CtClass ctClass = classPool.get(AopDemoServiceImpl.class.getName());

        CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");
        ctMethod.insertBefore("{ System.out.println(\"start\");}");
        ctMethod.insertAfter("{ System.out.println(\"end javassist\");}");

        AopDemoServiceImpl aopDemoService= (AopDemoServiceImpl) ctClass.toClass().newInstance();
        aopDemoService.sayHello();
    }
}
