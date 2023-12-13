package com.itheima;

public class Test {
    public static void main(String[] args) {
        new B().m();
    }


}
class A {
    int a = 10;
    A() { m(); }
    void m() { System.out.println(this.a); }
}

class B extends A {
    int a = 20;
    void m() { System.out.println(this.a); }
}