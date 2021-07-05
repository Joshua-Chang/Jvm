package com.data.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A {
    private final Lock lock=new ReentrantLock(/*是否开启公平锁*/);//默认不公平锁
    private /*volatile*/ int a;/*volatile对所有线程可见：但多线程下并不能完全保证线程绝对安全。另一个作用是禁止指令重排优化*/
    //适合volatile的情景：1、运算结果不依赖变量的当前值 2、确保只有一个线程修改变量的值
    public /*synchronized*/ void aPlus(){/*synchronized加锁才线程安全*/
        lock.lock();
        a++;
        lock.unlock();
    }

    public int getA() {
        return this.a;
    }
}
