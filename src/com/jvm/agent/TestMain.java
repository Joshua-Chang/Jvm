//package com.jvm.agent;
//
//import com.sun.tools.attach.*;
//
//import java.io.IOException;
//import java.util.List;
//
/*java11中tool.jar 不直接在bin中提供，javac编译可以*/
//public class TestMain {
//    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
//        VirtualMachine virtualMachine = null;
//        System.out.println("main start");
//        List<VirtualMachineDescriptor> list = VirtualMachine.list();
//        for (VirtualMachineDescriptor vmd : list) {
//            System.out.println(vmd.displayName());
//            if (vmd.displayName().endsWith("BeAttachedVM")) {
//                virtualMachine = VirtualMachine.attach(vmd.id());
//                virtualMachine.loadAgent("/Users/joshuachang/Documents/jvm/src/agentmain.jar");
//            }
//        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (virtualMachine != null) {
//            virtualMachine.detach();
//        }
//        System.out.println("main end");
//    }
//}
