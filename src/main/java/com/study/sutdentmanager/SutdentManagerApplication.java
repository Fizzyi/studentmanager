package com.study.sutdentmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@MapperScan(
        //指定扫描包
        basePackages = "com.study.sutdentmanager.*",
        annotationClass = Repository.class
)
@SpringBootApplication
public class SutdentManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SutdentManagerApplication.class, args);
    }

}
