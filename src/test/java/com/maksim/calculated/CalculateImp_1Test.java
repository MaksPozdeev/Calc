package com.maksim.calculated;

import com.maksim.validating.Validating;
import com.maksim.validating.Validator_1;
import org.junit.Assert;
import org.junit.Test;

public class CalculateImp_1Test {

    private Validator_1 validator_1 = new Validator_1();

    @Test
    public void isSymbolExpressionValidTest1() {
        String expression = "13+(3.6*4.7)/3";
        boolean actual = validator_1.isSymbolExpressionValid(expression);
        Assert.assertTrue(actual);
    }

    @Test
    public void isSymbolExpressionValidTest2() {
        String expression = "a13+(3.6*4.7)/3";
        boolean actual = validator_1.isSymbolExpressionValid(expression);
        Assert.assertFalse(actual);
    }



}