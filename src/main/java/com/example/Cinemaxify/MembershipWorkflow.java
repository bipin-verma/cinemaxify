package com.example.Cinemaxify;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;

public class MembershipWorkflow {

    private final ApplicationContext context;
    private final Scanner scanner;

    public MembershipWorkflow(ApplicationContext context, Scanner scanner) {
        this.context = context;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("Welcome to the Cinemaxify Application");
        System.out.println("Please select the member you want the plan for:");
        System.out.println("1. Self");
        System.out.println("2. Spouse");
        System.out.println("3. Exit");

        Integer userChoice = parseInteger(readLine());
        if (userChoice == null) {
            System.out.println("Invalid choice.");
            return;
        }

        if (userChoice == 3) {
            System.out.println("Exiting...");
            return;
        }

        User user = getUserFromChoice(userChoice);
        if (user == null) {
            System.out.println("Invalid choice.");
            return;
        }

        String name = promptForNonBlank("Please enter your name:");
        Integer age = promptForPositiveInteger("Please enter your age:");
        Long contact = promptForPositiveLong("Please enter your contact:");
        String address = promptForNonBlank("Please enter your address:");

        user.setUserDetails(name, age, contact, address);
        user.getUserDetails();
    }

    private User getUserFromChoice(int userChoice) {
        return switch (userChoice) {
            case 1 -> context.getBean("self", User.class);
            case 2 -> context.getBean("spouse", User.class);
            default -> null;
        };
    }

    private String promptForNonBlank(String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = readLine();
            if (value != null && !value.isBlank()) {
                return value;
            }

            System.out.println("Value cannot be blank. Please try again.");
        }
    }

    private Integer promptForPositiveInteger(String prompt) {
        while (true) {
            System.out.println(prompt);
            Integer value = parseInteger(readLine());
            if (value != null && value > 0) {
                return value;
            }

            System.out.println("Please enter a valid positive number.");
        }
    }

    private Long promptForPositiveLong(String prompt) {
        while (true) {
            System.out.println(prompt);
            Long value = parseLong(readLine());
            if (value != null && value > 0) {
                return value;
            }

            System.out.println("Please enter a valid positive contact number.");
        }
    }

    private String readLine() {
        if (!scanner.hasNextLine()) {
            return null;
        }

        return scanner.nextLine().trim();
    }

    private Integer parseInteger(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private Long parseLong(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        try {
            return Long.parseLong(input);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
