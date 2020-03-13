package com.quiterr;

/**
 * 简单测试下继承情况下的链式编程
 *
 * @author huangchen
 * @date 2020/1/30
 */
public class FatherTest {
    public static void main(String[] args) {
        Child c = new Child();
        c.setA(1).setB(2);
        System.out.println(c.reduce());
    }
}
