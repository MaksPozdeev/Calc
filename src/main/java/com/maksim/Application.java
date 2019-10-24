package com.maksim;

import com.maksim.calculated.Calculated;
import com.maksim.calculated.CalculateImp_1;

public class Application {

    public static void main(String[] args) {

        String mathExpression = "-7.3+4*((43.7-2)/3)+6";

        Calculated calc = new CalculateImp_1();

        double result = calc.calculate(mathExpression);

        System.out.println("Результат: " + result);

    }
}
