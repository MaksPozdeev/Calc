package com.maksim.service.calculate;

import com.maksim.validating.MyValidator;
import org.junit.Assert;
import org.junit.Test;

public class MyCalculator_Test {

    private MyValidator validator_My_ = new MyValidator();

    @Test
    public void isSymbolExpressionValidTest1() {
        String expression = "13+(3.6*4.7)/3";
        boolean actual = validator_My_.isSymbolExpressionValid(expression);
        Assert.assertTrue(actual);
    }

    @Test
    public void isSymbolExpressionValidTest2() {
        String expression = "a13+(3.6*4.7)/3";
        boolean actual = validator_My_.isSymbolExpressionValid(expression);
        Assert.assertFalse(actual);
    }

}