package com.example.Cinemaxify;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MemberProfileTest {

    @ParameterizedTest
    @MethodSource("profileCases")
    void printsEnteredMemberDetails(User user, String memberType) {
        user.setUserDetails("Bipin", 25, 9876543210L, "Pune");

        String output = ConsoleTestSupport.captureOutput(user::getUserDetails);

        assertTrue(output.contains("Hello Bipin, you have entered the following details for " + memberType + ":"));
        assertTrue(output.contains("age: 25"));
        assertTrue(output.contains("contact: 9876543210"));
        assertTrue(output.contains("address: Pune"));
    }

    private static Stream<Arguments> profileCases() {
        return Stream.of(
                Arguments.of(new Self(), "self"),
                Arguments.of(new Spouse(), "spouse")
        );
    }
}
