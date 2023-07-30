package com.laurasoto.springrest.lifeCycle;

public class ExplicitBean {
    public void init(){
        System.out.println("Init method");
    }
    public void destroy(){
        System.out.println("Destroy method");
    }
}
