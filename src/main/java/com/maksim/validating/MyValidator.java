package com.maksim.validating;

import com.maksim.exceptions.ExpressionIsNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(MyValidator.class);
    private static final String DELIMITER_REGEX = "([\\+\\-\\*\\/\\^\\(\\)]|$)";
    private static final String NUMBERS_REG_EX = "(\\d+([\\.]\\d+)?)";
    private static final String RECURRING_OPERATIONS_REG_EX = "(\\.|\\++|\\*+|:+|\\^+){2,}|(-+){3,}";
    private static final String NOT_FIRST_REG_EX = "^[\\+\\.\\*]";
    private static final String NOT_END_REG_EX = "[\\+\\-\\*\\/\\^\\.\\(]$";

    @Override
    public boolean isExpressionValid(String mathExpression) {
        logger.info("Method: isExpressionValid() - init");
        mathExpression = mathExpression.replaceAll("\\s", "");

        if (!isSymbolExpressionValid(mathExpression)) {
            logger.error("В выражении присутствуют некорректные символы");
            throw new ExpressionIsNotValidException("В выражении присутствуют некорректные символы");
        }

        if (!isFirstSymbolCorrect(mathExpression)) {
            logger.error("В выражении некорректный первый символ");
            throw new ExpressionIsNotValidException("В выражении некорректный первый символ");
        }

        if (!isLastSymbolCorrect(mathExpression)) {
            logger.error("В выражении некорректный последний символ");
            throw new ExpressionIsNotValidException("В выражении некорректный последний символ");
        }

        if (isDuplicateOperationsFound(mathExpression)) {
            logger.error("В выражении присутствуют подряд идущие операции. Пример: ++, ***, //, ...");
            throw new ExpressionIsNotValidException("В выражении присутствуют подряд идущие операции. Пример: ++, ***, //, ...");
        }

        if (!isBracketsExpressionValid(mathExpression)) {
            logger.error("В выражении некорректно расставлены скобки '(' и ')'");
            throw new ExpressionIsNotValidException("В выражении некорректно расставлены скобки '(' и ')'\"");
        }
        return true;
    }

    private boolean isBracketsExpressionValid(String mathExpression) {
        logger.info("Method: isBracketsExpressionValid() - init");
        int leftBrackets = 0;
        for (int i = 0; i < mathExpression.length(); i++) {
            if (mathExpression.charAt(i) == '(') {
                leftBrackets++;
            }
            if (mathExpression.charAt(i) == ')') {
                leftBrackets--;
                if (leftBrackets < 0) {
                    logger.error("Найдено некоррекно поставленная закрывающая скобка: ')'" + i);
                    throw new ExpressionIsNotValidException("Найдено некоррекно поставленная закрывающая скобка: ')'");
                }
            }
        }
        if (leftBrackets != 0) {
            logger.error("Количество '(' и ')' не одинаково");
            throw new ExpressionIsNotValidException("Количество '(' и ')' не одинаково");
        }
        return true;
    }

    private boolean isSymbolExpressionValid(String mathExpression) {
        logger.info("Method: isSymbolExpressionValid() - init");
        mathExpression = mathExpression
                .replaceAll(NUMBERS_REG_EX, "")
                .replaceAll(DELIMITER_REGEX, "");
        return mathExpression.isEmpty();
    }

    private boolean isDuplicateOperationsFound(String mathExpression) {
        logger.info("Method: isDuplicateOperationsFound() - init");
        Pattern p = Pattern.compile(RECURRING_OPERATIONS_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return m.find();
    }

    private boolean isFirstSymbolCorrect(String mathExpression) {
        logger.info("Method: isFirstSymbolCorrect() - init");
        Pattern p = Pattern.compile(NOT_FIRST_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return !m.find();
    }

    private boolean isLastSymbolCorrect(String mathExpression) {
        logger.info("Method: isLastSymbolCorrect() - init");
        Pattern p = Pattern.compile(NOT_END_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return !m.find();
    }

}
