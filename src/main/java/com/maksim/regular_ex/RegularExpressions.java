package com.maksim.regular_ex;

public class RegularExpressions {

    public static final String DELIMITER_REGEX = "([\\+\\-\\*\\/\\^\\(\\)]|$)";
    public static final String NUMBERS_REG_EX = "(\\d+([\\.]\\d+)?)";
    public static final String OPERATORS_REG_EX = "[\\+\\-\\*\\/^]";
    public static final String RECURRING_OPERATIONS_REG_EX = "(\\.|-+|\\++|\\*+|:+|\\^+){2,}";
    public static final String NOT_FIRST_REG_EX = "^[\\+\\.\\*]";


}
