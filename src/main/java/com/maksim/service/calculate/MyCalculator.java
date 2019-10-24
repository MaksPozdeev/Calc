package com.maksim.service.calculate;

import com.maksim.validating.MyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyCalculator implements Calculator {

    private static final Logger logger = LogManager.getLogger(MyCalculator.class);

    @Override
    public double calculate(String mathExpression) {
        logger.info("Method: calculate() - init");
        double result = 0;

//        Проверка валидности строки mathExpression
//        in (String):	mathExpression
//        out (Boolean): true/false
        MyValidator validator_My_ = new MyValidator();
        if (!validator_My_.isExpressionValid(mathExpression)) {
        logger.error("Введено некорректное выражение");
//            throw new ExpressionIsNotValidException("Введено некорректное выражение");
        } else {
            logger.info("всё ок - продолжаем");
        }

//        переводим/получаем mathExpression в постфксный вид (RPN-обратная польская нотация)
//        in (Stirng): mathExpression
//        out (String): mathExpressionRPN

//        производим вычисление значения выражения из обратной польской нотации:
//        in (Stirng): mathExpressionRPN
//        out (double): result

        return result;
    }

}
