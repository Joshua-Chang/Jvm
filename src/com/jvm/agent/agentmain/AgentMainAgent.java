package com.jvm.agent.agentmain;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentMainAgent {
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        instrumentation.addTransformer(new DefineTransformer(), true);
    }

    public static void agentmain(String agentArgs) {//没有两个参数的方法，才会执行这个
        System.out.println("agentArgs : " + agentArgs);
    }

    static class DefineTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            /*可在此处用javassist等修改替换*/
            System.out.println("agentmain load Class:" + className);
            return classfileBuffer;
        }
    }
}
