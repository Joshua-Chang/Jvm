package com.data.gc;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

public class ReferenceType {
    private static ReferenceQueue<User> rq = new ReferenceQueue<User>();

    private static void printQueue(String str){
        Reference<? extends User> obj = rq.poll();
        if (obj != null) {
            System.out.println("gc obj reference="+str+"="+obj.get());
        }
    }

    /**
     * 内存不够了就回收softReference
     */
    private static void testSoftReference() throws Exception {
        List<SoftReference<User>> list = new ArrayList<SoftReference<User>>();
        for (int i = 0; i < 10; i++) {
            SoftReference<User> sr = new SoftReference<User>(new User("soft " + i), /*引用被回收后加入队列*/rq);
            System.out.println("soft user==" + sr.get());
            list.add(sr);
        }
        System.gc();
        Thread.sleep(1000L);
        printQueue("soft");
    }

    /**
     * 内存不够了就回收WeakReference,ThreadLocal的ThreadLocalMap key就是弱引用
     */
    private static void testWeakReference() throws Exception {
        List<WeakReference<User>> list = new ArrayList<WeakReference<User>>();
        for (int i = 0; i < 10; i++) {
            WeakReference<User> sr = new WeakReference<>(new User("wake " + i), /*引用被回收后加入队列*/rq);
            System.out.println("weak user==" + sr.get());
            list.add(sr);
        }
        System.gc();
        Thread.sleep(1000L);
        printQueue("weak");
    }

    private static void testPhantomReference() throws Exception {
        List<PhantomReference<User>> list = new ArrayList<PhantomReference<User>>();
        for (int i = 0; i < 10; i++) {
            PhantomReference<User> sr = new PhantomReference<User>(new User("phantom " + i), /*引用被回收后加入队列*/rq);
            System.out.println("phantom user==" + sr.get());
            list.add(sr);
        }
        System.gc();
        Thread.sleep(1000L);
        printQueue("phantom");
    }

    public static void main(String[] args) throws Exception {
        testSoftReference();
//        testWeakReference();
//        testPhantomReference();
    }
}
