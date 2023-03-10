package com.zerobase.zerobaseprojectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ZeroBaseProjectBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeroBaseProjectBoardApplication.class, args);
    }

}
