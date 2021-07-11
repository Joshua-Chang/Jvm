package com.aop.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.FileOutputStream;

public class ASMDemo {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("com/aop/AopDemoServiceImpl");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
        classReader.accept(myClassVisitor,ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        File file = new File("/Users/joshuachang/Documents/jvm/src/com/aop/AopDemoServiceImpl2.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
