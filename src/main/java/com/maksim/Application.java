package com.maksim;

import com.maksim.calculated.Calculated;
import com.maksim.calculated.CalculateImp_1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger logger = LogManager.getLogger(CalculateImp_1.class);

    public static void main(String[] args) {
        logger.info("Method: main() - init");

        String mathExpression = "-7.3+4*((43.7-2)/3)+6";

        Calculated calc = new CalculateImp_1();

        double result = calc.calculate(mathExpression);

        System.out.println("Результат: " + result);

    }
}
