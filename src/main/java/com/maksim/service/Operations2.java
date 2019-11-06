package com.maksim.service;

import java.util.function.BinaryOperator;

import static java.lang.Math.pow;

public enum Operations2 {

    BRACKET("(", 5),
    ADDITION("+", 10, (x, y) -> x + y),
    SUBTRACTION("-", 10, (x, y) -> x - y),
    MULTIPLICATION("*", 20, (x, y) -> x * y),
    DIVISION("/", 20, (x, y) -> x / y),
    POW("^", 30, (x, y) -> pow(x, y));

    private String operation;
    private int priority;
    private BinaryOperator<Double> action;

    Operations2() {
    }

    Operations2(String operation, int priority) {
        this.operation = operation;
        this.priority = priority;
    }

    Operations2(String operation, int priority, BinaryOperator<Double> action) {
        this.operation = operation;
        this.priority = priority;
        this.action = action;
    }

    public String getOperation() {
        return operation;
    }

    public int getPriority() {
        return priority;
    }

    public BinaryOperator<Double> getAction() {
        if (this == BRACKET) {
            throw new IllegalArgumentException("У операции BRACKET нет action");
        }
        return action;
    }
}
