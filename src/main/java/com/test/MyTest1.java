package com.test;

import org.springframework.beans.factory.annotation.Autowired;

public class MyTest1 {
    @Autowired
    public void testAutowired(myTest test){
        test.showTest();
    }
}
