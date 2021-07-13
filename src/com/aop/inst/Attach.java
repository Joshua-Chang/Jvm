package com.aop.inst;


import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Attach {
    public static void main(String[] args) {
        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = VirtualMachine.attach("1204");
            virtualMachine.loadAgent("/Users/joshuachang/Documents/jvm/src/inst.jar");
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        }
    }
}
