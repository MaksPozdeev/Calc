package com.maksim.validating;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(MyValidator.class);
    private static final String DELIMITER_REGEX = "([\\+\\-\\*\\/\\^\\(\\)]|$)";
    private static final String NUMBERS_REG_EX = "(\\d+([\\.]\\d+)?)";
//    2 или 3 операции подряд / подумать
    private static final String RECURRING_OPERATIONS_REG_EX = "(\\.|-+|\\++|\\*+|:+|\\^+){3,}";
    private static final String NOT_FIRST_REG_EX = "^[\\+\\.\\*]";
    private static final String NOT_END_REG_EX = "[\\+\\-\\*\\/\\^\\.\\(]$";

    @Override
    public boolean isExpressionValid(String mathExpression) {
        logger.info("Method: isExpressionValid() - init");
        mathExpression = mathExpression.replaceAll("\\s", "");

        if (!isSymbolExpressionValid(mathExpression)) {
            logger.error("В выражении присутствуют некорректные символы");
            return false;
        }

        if (!isFirstSymbolCorrect(mathExpression)) {
            logger.error("В выражении некорректный первый символ");
            return false;
        }

        if (!isLastSymbolCorrect(mathExpression)) {
            logger.error("В выражении некорректный последний символ");
            return false;
        }

        if (isDuplicateOperationsFound(mathExpression)) {
            logger.error("В выражении присутствуют подряд идущие операции. Пример: ++, ***, //, ...");
            return false;
        }

        if (!isBracketsExpressionValid(mathExpression)) {
            logger.error("В выражении некорректно расставлены скобки '(' и ')'");
            return false;
        }
        return true;
    }

    public boolean isBracketsExpressionValid(String mathExpression) {
        logger.info("Method: isBracketsExpressionValid() - init");
        int leftBrackets = 0;
        for (int i = 0; i < mathExpression.length(); i++) {
            if (mathExpression.charAt(i) == '(') {
                leftBrackets++;
            }
            if (mathExpression.charAt(i) == ')') {
                leftBrackets--;
                if (leftBrackets < 0) {
                    logger.error("Найдено некоррекно поставленная закрывающая скобка: ')'");
                    return false;
//                    throw new ExpressionIsNotValidException("Найдено некоррекно поставленная закрывающая скобка: ')'");
                }
            }
        }
        if (leftBrackets != 0) {
            logger.error("Количество '(' и ')' не одинаково");
            return false;
//            throw new ExpressionIsNotValidException("Количество '(' и ')' не одинаково");
        }
        return true;
    }

    public boolean isSymbolExpressionValid(String mathExpression) {
        logger.info("Method: isSymbolExpressionValid() - init");
//        Удаляем цифры и символы делители: +-*/^() из выражения.
//        Если длина выражения не нуль значит остались некорретные символы
        mathExpression = mathExpression.
                replaceAll(NUMBERS_REG_EX, "").
                replaceAll(DELIMITER_REGEX, "");
        return mathExpression.isEmpty();
    }

    public boolean isDuplicateOperationsFound(String mathExpression) {
        logger.info("Method: isDuplicateOperationsFound() - init");
        Pattern p = Pattern.compile(RECURRING_OPERATIONS_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return m.find();
    }

    public boolean isFirstSymbolCorrect(String mathExpression) {
        logger.info("Method: isFirstSymbolCorrect() - init");
        Pattern p = Pattern.compile(NOT_FIRST_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return !m.find();
    }

    public boolean isLastSymbolCorrect(String mathExpression) {
        logger.info("Method: isLastSymbolCorrect() - init");
        Pattern p = Pattern.compile(NOT_END_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return !m.find();
    }

}
