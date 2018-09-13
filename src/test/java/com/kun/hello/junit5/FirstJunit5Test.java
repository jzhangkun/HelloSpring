package com.kun.hello.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstJunit5Test {
    @Test
    @DisplayName("my first test")
    public void myFirstTest() {
        assertEquals(2, 1 + 1);
    }
}
