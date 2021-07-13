package com.aop.inst;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class RuntimeAgent {
//        public static void agentmain(String args, Instrumentation inst) throws Exception {
//        inst.addTransformer(new ClassFileTransformer() {
//            @Override
//            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
//                try {
//                    ClassReader classReader = new ClassReader("com.aop.inst.RuntimeInstr");
//                    System.out.println("no error");
//                    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//                    ClassVisitor myClassVisitor = new ClassVisitor(Opcodes.ASM5) {
//                        @Override
//                        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//                            MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
//                            if (name.equals("sayHello")) {
//                                return new MethodVisitor(Opcodes.ASM5) {
//                                    @Override
//                                    public void visitCode() {
//                                        super.visitCode();
//                                        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//                                        mv.visitLdcInsn("start---");
//                                        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//                                    }
//
//                                    @Override
//                                    public void visitInsn(int opcode) {
//                                        if ((opcode >= org.objectweb.asm.Opcodes.IRETURN && opcode <= org.objectweb.asm.Opcodes.RETURN)
//                                                || opcode == org.objectweb.asm.Opcodes.ATHROW) {
//                                            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//                                            mv.visitLdcInsn("end---");
//                                            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//                                        }
//                                        mv.visitInsn(opcode);
//                                    }
//                                };
//                            } else {
//                                return methodVisitor;
//                            }
//                        }
//                    };
//                    classReader.accept(myClassVisitor, ClassReader.SKIP_DEBUG);
//                    return classWriter.toByteArray();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        });
//        inst.retransformClasses(com.aop.inst.RuntimeInstr.class);
//    }
//}

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        final CtClass[] ctClass = new CtClass[1];

        inst.addTransformer(new ClassFileTransformer() {


            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                try {
                    ClassPool classPool = ClassPool.getDefault();
                    classPool.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
//                    CtClass ctClass = classPool.get("com.aop.inst.RuntimeInstr");
                    classPool.insertClassPath(new ClassClassPath(RuntimeInstr.class));
                    ctClass[0] = classPool.get(RuntimeInstr.class.getName());
                    CtMethod ctMethod = ctClass[0].getDeclaredMethod("sayHello");
                    ctMethod.insertBefore("{ System.out.println(\"start\");}");
                    ctMethod.insertAfter("{ System.out.println(\"end javassist\");}");
                    return ctClass[0].toBytecode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(com.aop.inst.RuntimeInstr.class);
            //CtClass有如下操作时writeFile(), toClass(), or toBytecode(), Javassist freezes that CtClass会被冻结，操作完成后要解冻ctClass.defrost()
            ctClass[0].defrost();
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}