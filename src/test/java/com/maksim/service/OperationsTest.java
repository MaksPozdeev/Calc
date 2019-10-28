package com.maksim.service;

import com.maksim.exceptions.ExpressionIsNotValidException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OperationsTest {

    private String operation;
    private Integer expected;

    public OperationsTest(Integer expected, String operation) {
        this.operation = operation;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}:getPrioritet({0}, {1})")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {5, "("},
                {10, "+"},
                {10, "-"},
                {20, "*"},
                {20, "/"},
                {30, "^"}
        });
    }

    @Test
    public void getPrior_exception_test() {
        assertEquals(expected, Operations.getPrior(operation));
    }

    @Test
    public void getPrior_true_test() {
        Integer exp = 20;
        assertEquals(exp, Operations.getPrior("*"));
    }

    @Test(expected = ExpressionIsNotValidException.class)
    public void getPrior_exceptions_test() {
        Integer exp = 0;
        assertEquals(exp, Operations.getPrior("!"));
        assertEquals(exp, Operations.getPrior("%"));
    }

    @Test(expected = NullPointerException.class)
    public void getPrior_nullExceptions_test() {
        assertEquals(false, Operations.getPrior(null));
    }

    @Test
    public void isOperation_true_test() {
        assertTrue(Operations.isOperation("*"));
    }

    @Test
    public void isOperation_false_test() {
        assertFalse(Operations.isOperation("!"));
        assertFalse(Operations.isOperation("%"));
    }

    @Test
    public void isOperation_null_test() {
        assertFalse(Operations.isOperation(null));
    }

}