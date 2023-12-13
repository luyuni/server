package com.itheima;

import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

public class Test2 {
    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        new Thread(()->{
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }).start();
    }
}
