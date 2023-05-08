package com.britetonia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class UserServiceApplication {

//    @Value("${jwt.secret}")
    private static String SECRET_KEY;

    public static void main(String[] args) {
        log.info(SECRET_KEY);
        SpringApplication.run(UserServiceApplication.class, args);
    }
}