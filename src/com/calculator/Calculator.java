package com.calculator;

import com.calculator.exceptions.NotExistVariableException;
import com.calculator.exceptions.NotValidExpressionException;
import com.calculator.exceptions.NotValidVariableInitException;
import com.calculator.exceptions.UnknownOperatorException;
import java.util.Scanner;
import java.util.TreeMap;

public class Calculator {
    private static TreeMap<String, Integer> variables = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression;

        while (true) {
            System.out.println("Enter an expression (or '/exit' to exit):");
            expression = sc.nextLine();
            if (expression.equals("/exit"))
                return;
            try {
                parseExpression(expression);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(5);            // не успевает вывести сообщение об ошибке полностью?
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static Operators setOperator(String op) throws Exception {       // Check if operator is valid: {+, -, *, /}
        switch (op) {
            case "+":
                return Operators.PLUS;
            case "-":
                return Operators.MINUS;
            case "*":
                return Operators.MULTIPLY;
            case "/":
                return Operators.DIVIDE;
            case "=":
                return Operators.EQUAL;
            default:
                throw new UnknownOperatorException();
        }
    }

    public static void parseExpression(String expression) throws Exception {
        String[] splitExpression = expression.trim().split("\\p{Blank}");

        if (splitExpression.length != 3)
            throw new NotValidExpressionException();

        Operators operator = setOperator(splitExpression[1]);
        String firstOperand = splitExpression[0];
        String secondOperand = splitExpression[2];

        if (operator.equals(Operators.EQUAL)) {

            if (isDigit(firstOperand) || firstOperand.equals("\\W"))
                throw new NotValidVariableInitException();

            if (!isDigit(secondOperand)) {
                Integer value = variables.get(secondOperand);

                if (value == null)
                    throw new NotValidVariableInitException();

                variables.put(firstOperand, value);
                System.out.println("> Variable successfully set.");
                return;
            }

            variables.put(firstOperand, Integer.parseInt(secondOperand));
            System.out.println("> Variable successfully set.");
            return;
        }

        if (isDigit(firstOperand) && isDigit(secondOperand)) {
            doOperation(Integer.parseInt(firstOperand), Integer.parseInt(secondOperand), operator);
            return;
        }

        if (isDigit(firstOperand)) {
            Integer value = variables.get(secondOperand);
            if (value == null) throw new NotExistVariableException();
            doOperation(Integer.parseInt(firstOperand), value, operator);
            return;
        }

        if (isDigit(secondOperand)) {
            Integer value = variables.get(firstOperand);
            if (value == null) throw new NotExistVariableException();
            doOperation(value, Integer.parseInt(secondOperand), operator);
            return;
        }

        Integer firstValue = variables.get(firstOperand);
        Integer secondValue = variables.get(secondOperand);
        if (firstValue == null || secondValue == null) throw new NotExistVariableException();

        doOperation(firstValue, secondValue, operator);
    }

    public static void doOperation(int firstOperand, int secondOperand, Operators operator) throws RuntimeException {
        if (secondOperand == 0) throw new ArithmeticException();
        switch (operator) {
            case PLUS:
                System.out.println(firstOperand + secondOperand);
                break;
            case MINUS:
                System.out.println(firstOperand - secondOperand);
                break;
            case MULTIPLY:
                System.out.println(firstOperand * secondOperand);
                break;
            case DIVIDE:
                System.out.println(firstOperand / secondOperand);
                break;
        }
    }

    public static boolean isDigit(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (Character.digit(string.charAt(i), 10) < 0)
                return false;
        }
        return true;
    }
}
