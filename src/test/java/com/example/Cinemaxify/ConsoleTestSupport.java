package com.example.Cinemaxify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

final class ConsoleTestSupport {

    private ConsoleTestSupport() {
    }

    static String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PrintStream captureStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(captureStream);
            action.run();
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString(StandardCharsets.UTF_8);
    }
}
