package com.itheima;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test4 {
    static class C {
        public C(String str) {
            System.out.print(str);
        }
    }

    static class A {
        static {
            new C("1");
        }

        static C c1 = new C("2");

        public A() {
            new C("3");
        }

        C c2 = new C("4");
    }

    static class B extends A {
        static C c5 = new C("5");

        static {
            new C("6");
        }

        C c6 = new C("7");

        public B() {
            new C("8");
        }
    }

    interface I {
        void foo();
    }

    public static void main(String[] args) {
        Enhancer e = new Enhancer();
        e.setSuperclass(I.class);

        e.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("enter");
            return null;
        });
        I i = (I) e.create();
        i.foo();
    }
}

class Sync {
    public synchronized void a() {
        System.out.print(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

    }

    public void b() {
        System.out.print(2);
    }
}


