package com.maksim.service.calculate;

import com.maksim.service.Operations;
import com.maksim.service.converter.PostfixRecord;
import com.maksim.validating.MyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class MyCalculator implements Calculator {

    private static final Logger logger = LoggerFactory.getLogger(MyCalculator.class);
    private MyValidator myValidator = new MyValidator();
    private LinkedList<String> stack = new LinkedList<>();

    @Override
    public double calculate(String mathExpression) {
        logger.info("Method: calculate() - init");
        double result;

        if (!myValidator.isExpressionValid(mathExpression)) {
            logger.error("Введено некорректное выражение");
        } else {
            logger.info("всё ок - продолжаем");
        }

        LinkedList<String> strPostfix = PostfixRecord.strToPostfix(mathExpression);

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
                double tmp2 = Double.parseDouble(stack.removeLast());
                double tmp1 = Double.parseDouble(stack.removeLast());
                double tmpResult;
                tmpResult = operationsRun(token, tmp1, tmp2);
                stack.add(Double.toString(tmpResult));
            }
        }

        result = Double.parseDouble(stack.removeLast());
        return result;
    }

    private double operationsRun(String token, double tmp1, double tmp2) {
        double result;
        switch (token) {
            case "+": {
                result = tmp1 + tmp2;
                break;
            }
            case "-": {
                result = tmp1 - tmp2;
                break;
            }
            case "*": {
                result = tmp1 * tmp2;
                break;
            }
            case "/": {
                result = tmp1 / tmp2;
                break;
            }
            case "^": {
                result = Math.pow(tmp1, tmp2);
                break;
            }
            default: {
                throw new IllegalArgumentException("Не найдена операция");
            }
        }
        return result;
    }

}
