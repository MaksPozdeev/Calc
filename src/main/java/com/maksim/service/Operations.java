package com.maksim.service;

import com.maksim.exceptions.ExpressionIsNotValidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Operations {

    private static final Logger logger = LogManager.getLogger(Operations.class);
    private static final Map<String, Integer> MATH_OPERATIONS = new HashMap<>();

    static {
//        MATH_OPERATIONS.put("u-", 0);
        MATH_OPERATIONS.put("(", 5);
        MATH_OPERATIONS.put("+", 10);
        MATH_OPERATIONS.put("-", 10);
        MATH_OPERATIONS.put("*", 20);
        MATH_OPERATIONS.put("/", 20);
        MATH_OPERATIONS.put("^", 30);
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
