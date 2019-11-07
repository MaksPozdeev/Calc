package com.maksim.service.converter;

import com.maksim.service.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostfixRecord {

    private static final Logger logger = LoggerFactory.getLogger(PostfixRecord.class);
    private static final String DELIMITER_REGEX = "([\\+\\-\\*\\/\\^\\(\\)]|$)";

    public static LinkedList<String> strToPostfix(String mathExpression) {
        logger.info("Method: strToPostfix() - init");
        mathExpression = mathExpression.replaceAll("\\s", "");
        LinkedList<String> outPostfixString = new LinkedList<>();
        LinkedList<String> stack = new LinkedList<>();

        List<String> listTokens = getTokens(mathExpression);
        boolean tmp;
        for (String token : listTokens) {
            if (isNumber(token)) {
                outPostfixString.add(token);
                continue;
            }

            if (token.equals("(")) {
                stack.add("(");
                continue;
            }

            if (token.equals(")")) {
                tmp = false;
                while (!stack.isEmpty()) {
                    if (stack.getLast().equals("(")) {
                        stack.removeLast();
                        tmp = true;
                        break;
                    } else {
                        outPostfixString.add(stack.removeLast());
                    }
                }
                if (!tmp) {
                    logger.error("Скобки не согласованны. Проверьте выражение!");
                }
                continue;
            }

            if (Operations.isOperation(token)) {
                while (!stack.isEmpty()) {
                    if (Operations.getPrior(token) <= Operations.getPrior(stack.getLast())) {
                        outPostfixString.add(stack.removeLast());
                    } else {
                        break;
                    }
                }
                stack.add(token);
            }
        }

        while (!stack.isEmpty()) {
            outPostfixString.add(stack.removeLast());
        }
        return outPostfixString;
    }

    public static boolean isNumber(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    private static List<String> getTokens(String mathExpression) {
        logger.info("Method: getTokens() - init");
        List<String> stringTokensList = new ArrayList<>();
        Pattern pattern = Pattern.compile(DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(mathExpression);
        int startPos = 0;
        int endPos;
        String strTmp;
        while (matcher.find()) {
            endPos = matcher.start();
            strTmp = mathExpression.substring(startPos, endPos);
            if (!strTmp.isEmpty()) {
                stringTokensList.add(strTmp);
            }
            if (!matcher.group().isEmpty()) {
                stringTokensList.add(matcher.group());
            }
            startPos = matcher.end();
        }
        return stringTokensList;
    }
}