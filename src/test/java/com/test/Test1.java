package com.test;

import org.junit.Test;

public class Test1 {
    @Test
    public void showMyTest(){
        myTest test = new myTest();
        System.out.println("start");
        test.showTest();
        System.out.println("end");
    }
}
