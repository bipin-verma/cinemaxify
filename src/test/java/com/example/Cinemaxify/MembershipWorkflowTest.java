package com.example.Cinemaxify;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class MembershipWorkflowTest {

    @Test
    void collectsSelfMemberDetailsWithNormalPlanAndPrintsThem() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            MembershipWorkflow workflow = new MembershipWorkflow(
                    context,
                    scannerFor("1", "1", "Bipin", "25", "9876543210", "Pune", "2")
            );

            String output = ConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Welcome to the Cinemaxify Application"));
            assertTrue(output.contains("Please select the member you want the plan for:"));
            assertTrue(output.contains("Please select your plan:"));
            assertTrue(output.contains("Hello Bipin, you have entered the following details for self:"));
            assertTrue(output.contains("contact: 9876543210"));
            assertTrue(output.contains("plan: normal"));
        }
    }

    @Test
    void exitsCleanlyWhenUserChoosesExit() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            MembershipWorkflow workflow = new MembershipWorkflow(context, scannerFor("3"));

            String output = ConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Exiting..."));
        }
    }

    @Test
    void retriesInvalidAgeBeforeContinuing() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            MembershipWorkflow workflow = new MembershipWorkflow(
                    context,
                    scannerFor("2", "2", "Anaya", "-1", "30", "9998887776", "Delhi", "2")
            );

            String output = ConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Please enter a valid positive number."));
            assertTrue(output.contains("Hello Anaya, you have entered the following details for spouse:"));
            assertTrue(output.contains("address: Delhi"));
            assertTrue(output.contains("plan: premium"));
        }
    }

    @Test
    void loopsToCreateAnotherPlanWhenUserChoosesYes() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            MembershipWorkflow workflow = new MembershipWorkflow(
                    context,
                    scannerFor(
                            "1", "2", "Bipin", "25", "9876543210", "Pune", "1",
                            "2", "1", "Anaya", "28", "9998887776", "Delhi", "2"
                    )
            );

            String output = ConsoleTestSupport.captureOutput(workflow::run);

            assertTrue(output.contains("Hello Bipin, you have entered the following details for self:"));
            assertTrue(output.contains("plan: premium"));
            assertTrue(output.contains("Hello Anaya, you have entered the following details for spouse:"));
            assertTrue(output.contains("plan: normal"));
        }
    }

    private Scanner scannerFor(String... lines) {
        String input = String.join(System.lineSeparator(), lines) + System.lineSeparator();
        return new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }
}
