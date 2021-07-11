package com.jvm.agent.premain;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PremainAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new DefineTransformer(), true);
    }

    public static void premain(String agentArgs) {//没有两个参数的方法，才会执行这个
        System.out.println("agentArgs : " + agentArgs);
    }

    static class DefineTransformer implements ClassFileTransformer {
        //transform方法将返回一个 byte 数组，代表更新过后的类的字节码。
        //Java 虚拟机会使用所返回的 byte 数组，来完成接下来的类加载工作。不过，如果transform方法返回 null 或者抛出异常，那么 Java 虚拟机将使用原来的 byte 数组完成类加载工作。
        //基于这一类加载事件的拦截功能，我们可以实现字节码注入（bytecode instrumentation），往正在被加载的类中插入额外的字节码。
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            /*可在此处用javassist等修改替换*/
//            System.out.println("premain load Class:" + className);
            System.out.printf("Loaded %s: 0x%X%X%X%X\n", className, classfileBuffer[0], classfileBuffer[1],
                    classfileBuffer[2], classfileBuffer[3]);//打印该数组的前四个字节，也就是 Java class 文件的魔数（magic number）0xCAFEBABE。
            return classfileBuffer;
        }
    }
    //Premain-Class ：包含 premain 方法的类（类的全路径名）
    //Agent-Class ：包含 agentmain 方法的类（类的全路径名）
}
