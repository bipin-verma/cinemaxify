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
        while (true) {
            Integer userChoice = promptForMenuChoice(
                    "Please select the member you want the plan for:",
                    "1. Self",
                    "2. Spouse",
                    "3. Exit"
            );

            if (userChoice == 3) {
                System.out.println("Exiting...");
                return;
            }

            Integer planChoice = promptForMenuChoice(
                    "Please select your plan:",
                    "1. Normal",
                    "2. Premium"
            );

            User user = getUserFromChoice(userChoice, planChoice);
            String name = promptForNonBlank("Please enter your name:");
            Integer age = promptForPositiveInteger("Please enter your age:");
            Long contact = promptForPositiveLong("Please enter your contact:");
            String address = promptForNonBlank("Please enter your address:");

            user.setUserDetails(name, age, contact, address);
            user.getUserDetails();

            Integer continueChoice = promptForMenuChoice(
                    "Do you want to purchase a plan for someone else?",
                    "1. Yes",
                    "2. No"
            );

            if (continueChoice == 2) {
                System.out.println("Exiting...");
                return;
            }
        }
    }

    private User getUserFromChoice(int userChoice, int planChoice) {
        String memberType = switch (userChoice) {
            case 1 -> "self";
            case 2 -> "spouse";
            default -> throw new IllegalArgumentException("Unsupported member choice: " + userChoice);
        };

        String planType = switch (planChoice) {
            case 1 -> "Normal";
            case 2 -> "Premium";
            default -> throw new IllegalArgumentException("Unsupported plan choice: " + planChoice);
        };

        return context.getBean(memberType + planType, User.class);
    }

    private Integer promptForMenuChoice(String prompt, String... options) {
        while (true) {
            System.out.println(prompt);
            for (String option : options) {
                System.out.println(option);
            }

            Integer value = parseInteger(readLine());
            if (value != null && value >= 1 && value <= options.length) {
                return value;
            }

            System.out.println("Invalid choice.");
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
