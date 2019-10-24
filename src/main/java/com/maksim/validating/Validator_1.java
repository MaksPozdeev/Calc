package com.maksim.validating;

import com.maksim.exceptions.ExpressionIsNotValidException;
import com.maksim.regular_ex.RegularExpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator_1 implements Validating {

    @Override
    public boolean isExpressionValid(String mathExpression) {
//        logger.info("isExpressionValid - init");
        mathExpression = mathExpression.replaceAll("\\s", "");

        if(!isSymbolExpressionValid(mathExpression)){
            return false;
//            logger.error("В выражении присутствуют некорректные символы");
        }

        if(!isFirstSymbolCorrect(mathExpression)){
          return false;
//            logger.error("В выражении некорректный первый символ");
        }

        if(isDuplicateOperationsFound(mathExpression)){
            return false;
//            logger.error("В выражении присутствуют подряд идущие операции. Пример: ++, ***, //, ...");
        }

        if(!isBracketsExpressionValid(mathExpression)){
            return false;
//            logger.error("В выражении некорректно расставлены скобки '(' и ')'");
        }
        return true;
    }

    public boolean isBracketsExpressionValid(String mathExpression) {
        boolean result = false;
//        не должно подряд идти несколько математичесикх операции   +
//        не должно подряд идти несколько точек
//        первая входящая скобка не должна быть )
//        количество ( и ) скобок должно быть равно

        int leftBrackets = 0;
        for (int i = 0; i < mathExpression.length(); i++) {
            if (mathExpression.charAt(i) == '(') {
                leftBrackets++;
            }
            if (mathExpression.charAt(i) == ')') {
                leftBrackets--;
                if (leftBrackets < 0) {
                    throw new ExpressionIsNotValidException("Найдено некоррекно поставленная закрывающая скобка: ')'");
                }
            }
        }
        if (leftBrackets != 0) {
            throw new ExpressionIsNotValidException("Количество '(' и ')' не одинаково");
        }
        return result;
    }

    public boolean isSymbolExpressionValid(String mathExpression) {
//        Удаляем цифры и символы делители: +-*/^() из выражения.
//        Если длина выражения не нуль значит остались некорретные символы
        mathExpression = mathExpression.
                replaceAll(RegularExpressions.NUMBERS_REG_EX, "").
                replaceAll(RegularExpressions.DELIMITER_REGEX, "");
        return mathExpression.isEmpty();
    }

    public boolean isDuplicateOperationsFound(String mathExpression){
        Pattern p = Pattern.compile(RegularExpressions.RECURRING_OPERATIONS_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return m.find();
    }

    public boolean isFirstSymbolCorrect(String mathExpression){
        Pattern p = Pattern.compile(RegularExpressions.NOT_FIRST_REG_EX);
        Matcher m = p.matcher(mathExpression);
        return !m.find();
    }

}
