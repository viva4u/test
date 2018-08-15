package com.test;


import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class TestApplication {
    public static void main(String[] args){
        System.out.println("helloworld");
        SpringApplication.run(SpringBootTestyanxj.class,args);
    }
}
