package com.example.Cinemaxify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ApplicationContextTest {

    @Test
    void loadsSelfAndSpouseBeansFromXmlConfiguration() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            User self = context.getBean("self", User.class);
            User spouse = context.getBean("spouse", User.class);

            assertNotNull(self);
            assertNotNull(spouse);
        }
    }
}
