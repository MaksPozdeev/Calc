package com.maksim;

import com.maksim.validating.Validator_1;

public class Experimental {
    public static void main(String[] args) {
        String mathExpression1 = "-7.3+4*((43.7-2)/3)+6";
        String mathExpression2 = "-7.3++4***((43..7--2)()/3)+6";
        String mathExpression3 = "-7.3+4*((43.7-2)/3)+6";
        String mathExpression4 = ".7.3+4*((43.7-2)/3)+6";
        String mathExpression5 = "+7.3+4*((43.7-2)/3)+6";
        String mathExpression6 = "*7.3+4*((43.7-2)/3)+6";

        Validator_1 v = new Validator_1();

//        v.isMathExpressionValid(mathExpression);
//        System.out.println(v.isDuplicateOperationsFound(mathExpression3));
//        System.out.println(v.isFirstSymbolCorrect(mathExpression3));

    }
}
