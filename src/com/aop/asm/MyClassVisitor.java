package com.aop.asm;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 首先通过MyClassVisitor类中的visitMethod方法，判断当前字节码读到哪一个方法了。跳过构造方法 <init> 后，将需要被增强的方法交给内部类MyMethodVisitor来进行处理。
 * 接下来，进入内部类MyMethodVisitor中的visitCode方法，会在某个方法被访问时调用，故前置增强逻辑在此编写，
 * MyMethodVisitor继续读取字节码指令，每当ASM访问到无参数指令时，都会调用MyMethodVisitor中的visitInsn方法。我们判断了当前指令是否为无参数的“return”指令，如果是就在它的前面添加一些指令，也就是将AOP的后置逻辑放在该方法中。
 * 综上，重写MyMethodVisitor中的两个方法，就可以实现AOP了，
 * 而重写方法时就需要用ASM的写法，手动写入或者修改字节码。通过调用methodVisitor的visitXXXXInsn()方法就可以实现字节码的插入，XXXX对应相应的操作码助记符类型，比如mv.visitLdcInsn(“end”)对应的操作码就是ldc “end”，即将字符串“end”压入栈。
 */
public class MyClassVisitor extends ClassVisitor implements Opcodes {
    public MyClassVisitor(ClassVisitor classVisitor) {
        super(ASM5, classVisitor);
    }
    //MyClassVisitor继承自ClassVisitor，用以观察某个类的字节码文件，

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        //visitMethod方法用于判断当前读取到字节码文件的哪个方法了，当读取到我们想进行增强的方法时，交给MyMethodVisitor对原方法进行增强
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("<init>")){//不增强构造方法,过滤掉
            return methodVisitor;
        }
        return new MyMethodVisitor(methodVisitor);
    }
    //MyMethodVisitor负责对具体方法进行增强
    //visitCode会在某个方法被访问时调用，故前置增强逻辑在此编写，
    //visitInsn会在无参数的指令的执行时调用，退出语句return被调用时就会调用visitInsn方法，因此，后置增强逻辑可以写在这里
    class MyMethodVisitor extends MethodVisitor implements Opcodes {

        public MyMethodVisitor(MethodVisitor methodVisitor) {
            super(ASM5, methodVisitor);
        }
        //使用idea插件ASM ByteCode Outline 查看ASMified并和原来的原文件生成的asm比较
        @Override
        public void visitCode() {
            super.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("start");//ldc:加载常量池中的常量值
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }

        @Override
        public void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                    || opcode == Opcodes.ATHROW) {
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("end : asm");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            }
            mv.visitInsn(opcode);
        }
    }
}
