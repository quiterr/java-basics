package com.quiterr;

/**
 * @author huangchen
 * @date 2020/1/30
 */
public abstract class AbstractFather {
    int a;
    int b;

    AbstractFather setA(int a){
        this.a = a;
        return this;
    }

    AbstractFather setB(int b){
        this.b = b;
        return this;
    }
}
