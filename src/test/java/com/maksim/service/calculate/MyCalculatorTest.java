package com.maksim.service.calculate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MyCalculatorTest {

    private String mathExpression;
    private double expected;

    public MyCalculatorTest(double expected, String mathExpression) {
        this.mathExpression = mathExpression;
        this.expected = expected;
    }

    private MyCalculator calc = new MyCalculator();

    @Parameterized.Parameters(name = "{index}:isValid({0}, {1})")
    public static Iterable<Object[]> dataForTest() throws Exception {
        return Arrays.asList(new Object[][]{
                {5, "2+3"},
                {6, "2*3"},
                {3, "6/2"},
                {2, "5-3"},
                {8, "2^3"}
        });
    }

    @Test
    public void calculate_test() {
        assertEquals(expected, calc.calculate(mathExpression), 0);
    }

}