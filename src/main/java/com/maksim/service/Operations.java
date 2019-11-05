package com.maksim.service;

import com.maksim.exceptions.ExpressionIsNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Operations {

    private static final Logger logger = LoggerFactory.getLogger(Operations.class);

    public static final String BRACKET = "(";
//    public static final String ADDITION = "+";
    public static final String ADDITION = "+";

    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
    public static final String POW= "^";

    private static final Map<String, Integer> MATH_OPERATIONS = new HashMap<>();

    static {
        MATH_OPERATIONS.put(BRACKET, 5);
        MATH_OPERATIONS.put(ADDITION, 10);
        MATH_OPERATIONS.put(SUBTRACTION, 10);
        MATH_OPERATIONS.put(MULTIPLICATION, 20);
        MATH_OPERATIONS.put(DIVISION, 20);
        MATH_OPERATIONS.put(POW, 30);
    }

    public static Integer getPrior(String operation) {
        if (operation != null) {
            if (isOperation(operation)) {
                return MATH_OPERATIONS.get(operation);
            } else {
                logger.error("Вызываемая операция: " + operation + " - не найдена");
                throw new ExpressionIsNotValidException("Вызываемая операция: " + operation + " - не найдена");
            }
        } else {
            logger.error("Обращение к null: getPrior(null)");
            throw new NullPointerException("Обращение к null: getPrior(null)");
        }
    }

    public static boolean isOperation(String operation) {
        if (operation != null) {
            return MATH_OPERATIONS.containsKey(operation);
        }
        return false;
    }
}
