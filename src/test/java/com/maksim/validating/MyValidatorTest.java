package com.maksim.validating;

import com.maksim.exceptions.ExpressionIsNotValidException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MyValidatorTest {

    private MyValidator validator = new MyValidator();

    private String mathExpression;
    private boolean expected;

    public MyValidatorTest(boolean expected, String mathExpression) {
        this.expected = expected;
        this.mathExpression = mathExpression;
    }

    @Parameterized.Parameters(name = "{index}:isValid({0}, {1})")
    public static Iterable<Object[]> dataForTest() throws Exception {
        return Arrays.asList(new Object[][]{
                {true, ")*7.3+4*((43.7-2)/3)+6"},
                {true, "-7.3+4*)(43.7-2)/3)+6"},
                {true, "-7.3+4*((43.7-2)/3)+6+"},
                {true, "-7.3+4*((43.7-2)/3)+6."},
                {true, "-7.3+4*((43.7-2)/3)+6+("},
                {true, "-7.3+4*((43.7-2)/3)+6("},
                {true, "-7.3++4***((43..7--2)()/3)+6"},
                {true, ".7.3+4*((43.7-2)/3)+6"},
                {true, "+7.3+4*((43.7-2)/3)+6"},
                {true, "*7.3+4*((43.7-2)/3)+6"}
        });
    }

    @Test(expected = ExpressionIsNotValidException.class)
    public void isExpressionValid_exception_test() {
        assertEquals(expected, validator.isExpressionValid(mathExpression));
    }

    @Test
    public void isExpressionValid_test1() {
        assertTrue(validator.isExpressionValid("-7.3+4*((-43.7-2)/3)+6"));
        assertTrue(validator.isExpressionValid("-7.3+4*((43.7-2)/3)+6"));
    }

    @Test(expected = ExpressionIsNotValidException.class)
    public void isBracketsExpressionValid_test() {
        assertTrue(validator.isExpressionValid("-7.3+4*((((-43.7-2)/3)+6"));
    }

}