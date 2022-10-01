package com.note.plannerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class PlannerWebApplication {

//    @PostConstruct
//    public void started(){
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//    }

    public static void main(String[] args) {
        SpringApplication.run(PlannerWebApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // authenticationManager를 Bean 등록합니다.
}
