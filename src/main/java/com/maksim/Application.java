package com.maksim;

import com.maksim.service.calculate.Calculator;
import com.maksim.service.calculate.MyCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Method: main() - init");

        String mathExpression = "-7.3+4*((-43.7-2)/3)+6";

        Calculator calc = new MyCalculator();

        try {
            double result = calc.calculate(mathExpression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            logger.error("Возникла какая-то ошибка: " + e);
        }

    }
}
