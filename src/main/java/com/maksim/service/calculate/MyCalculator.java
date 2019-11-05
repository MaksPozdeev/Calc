package com.maksim.service.calculate;

import com.maksim.service.Operations;
import com.maksim.service.converter.PostfixRecord;
import com.maksim.validating.MyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class MyCalculator implements Calculator {

    private static final Logger logger = LoggerFactory.getLogger(MyCalculator.class);

    @Override
    public double calculate(String mathExpression) {
        logger.info("Method: calculate() - init");
        double result;

        MyValidator myValidator = new MyValidator();
        if (!myValidator.isExpressionValid(mathExpression)) {
            logger.error("Введено некорректное выражение");
        } else {
            logger.info("всё ок - продолжаем");
        }

        LinkedList<String> strPostfix = PostfixRecord.strToPostfix(mathExpression);

        LinkedList<String> stack = new LinkedList<>();
        double tmp1;
        double tmp2;
        double tmpResult;

        for (String token : strPostfix) {
            if (PostfixRecord.isNumber(token)) {
                stack.add(token);
                continue;
            }

            if (token.equals("-")) {
                if (stack.size() == 1 || stack.size() == 3) {
                    stack.add("-" + stack.removeLast());
                    continue;
                }
            }

            if (Operations.isOperation(token)) {
                tmp2 = Double.parseDouble(stack.removeLast());
                tmp1 = Double.parseDouble(stack.removeLast());
                switch (token) {
                    case "+": {
                        tmpResult = tmp1 + tmp2;
                        break;
                    }
                    case "-": {
                        tmpResult = tmp1 - tmp2;
                        break;
                    }
                    case "*": {
                        tmpResult = tmp1 * tmp2;
                        break;
                    }
                    case "/": {
                        tmpResult = tmp1 / tmp2;
                        break;
                    }
                    case "^": {
                        tmpResult = Math.pow(tmp1, tmp2);
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Не найдена операция");
                    }
                }
                stack.add(Double.toString(tmpResult));
            }
        }

        result = Double.parseDouble(stack.removeLast());
        return result;
    }

}
