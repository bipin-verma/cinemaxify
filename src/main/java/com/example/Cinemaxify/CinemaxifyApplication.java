package com.example.Cinemaxify;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CinemaxifyApplication {

    public static void main(String[] args) {
        try (
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                java.util.Scanner scanner = new java.util.Scanner(System.in)
        ) {
            new MembershipWorkflow(context, scanner).run();
        }
    }
}
