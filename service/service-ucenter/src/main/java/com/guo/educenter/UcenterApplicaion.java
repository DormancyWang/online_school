package com.guo.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.guo"})
@SpringBootApplication
@MapperScan("com.guo.educenter.mapper")
public class UcenterApplicaion {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplicaion.class,args);
    }
}
