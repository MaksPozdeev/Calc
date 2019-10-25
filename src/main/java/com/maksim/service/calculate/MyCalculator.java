package com.maksim.service.calculate;

import com.maksim.service.Operations;
import com.maksim.service.converter.PostfixRecord;
import com.maksim.validating.MyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class MyCalculator implements Calculator {

    private static final Logger logger = LogManager.getLogger(MyCalculator.class);

    @Override
    public double calculate(String mathExpression) throws Exception {
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
//        out (List<String>): mathExpressionRPN
        LinkedList<String> strPostfix = PostfixRecord.strToPostfix(mathExpression);

//        производим вычисление значения выражения из обратной польской нотации:
//        in (List<String>): mathExpressionRPN
//        out (double): result

        LinkedList<String> outPostfixString = new LinkedList<>();
        LinkedList<String> stack = new LinkedList<>();
        double tmp1 = 0;
        double tmp2 = 0;
        double tmpResult = 0;

        for (String token : strPostfix) {
            if (PostfixRecord.isNumber(token)) {
                stack.add(token);
                continue;
            }

//            Унарный минус (или функция)
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
