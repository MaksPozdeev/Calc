package com.maksim.calculated;

import com.maksim.exceptions.ExpressionIsNotValidException;
import com.maksim.regular_ex.RegularExpressions;
import com.maksim.validating.Validator_1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.regex.Pattern.matches;

public class CalculateImp_1 implements Calculated {

    private static final Logger logger = LogManager.getLogger(CalculateImp_1.class);

    @Override
    public double calculate(String mathExpression) {
        logger.info("Method: calculate() - init");
        double result = 0;

//        Проверка валидности строки mathExpression
//        in (String):	mathExpression
//        out (Boolean): true/false
        Validator_1 validator_1 = new Validator_1();
        if (!validator_1.isExpressionValid(mathExpression)) {
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
