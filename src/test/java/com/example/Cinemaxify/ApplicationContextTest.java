package com.example.Cinemaxify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ApplicationContextTest {

    @Test
    void loadsAllMemberPlanBeansFromXmlConfiguration() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            Map<String, User> users = Map.of(
                    "selfNormal", context.getBean("selfNormal", User.class),
                    "spouseNormal", context.getBean("spouseNormal", User.class),
                    "selfPremium", context.getBean("selfPremium", User.class),
                    "spousePremium", context.getBean("spousePremium", User.class)
            );

            users.values().forEach(user -> assertNotNull(user.getUserPlan()));
            assertEquals("normal", users.get("selfNormal").getUserPlan().getPlanName());
            assertEquals("normal", users.get("spouseNormal").getUserPlan().getPlanName());
            assertEquals("premium", users.get("selfPremium").getUserPlan().getPlanName());
            assertEquals("premium", users.get("spousePremium").getUserPlan().getPlanName());
            assertTrue(users.get("selfNormal") instanceof Self);
            assertTrue(users.get("spousePremium") instanceof Spouse);
        }
    }
}
