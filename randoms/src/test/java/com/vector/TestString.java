package com.vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestString {
    StringTest stringTest;
    @BeforeEach
    public void start() {
        stringTest = new StringTest();
    }

    @ParameterizedTest
    @CsvSource(value={
        "megumin,aqua,Yes",
        "sumit,ava,Yes",
        "ava,sumit,No"
    })
    public void testString(String a,String b,String expected) {
        assertEquals(expected, stringTest.greater(a, b));
    }
}
