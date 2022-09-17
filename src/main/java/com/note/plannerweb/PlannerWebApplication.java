package com.note.plannerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class PlannerWebApplication {

    public static void main(String[] args) {
        System.out.println("test go");
        System.out.println("te23st");
        System.out.println("1233123");
        System.out.println("123312122133");
        SpringApplication.run(PlannerWebApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // authenticationManager를 Bean 등록합니다.
}
