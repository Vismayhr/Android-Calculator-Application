/*==========================================
Title:  Handles the operations of the calculator
Author: Vismay Revankar (vismayhr@dal.ca)
Date:   1 Oct 2018
============================================*/

package com.mobilecomputing.macs.vismay.calculatorapp;

public class Calculator extends Object {
    private float firstOperand;
    private float secondOperand;
    private boolean operandOneNegated;
    private boolean operandTwoNegated;
    private String operator;

    public Calculator() {
        this.firstOperand = Float.NaN;
        this.secondOperand = Float.NaN;
        this.operandOneNegated = false;
        this.operandTwoNegated = false;
        this.operator = null;
    }

    public float calculate() {
        float result = 0.0f;
        if (operandOneNegated) {
            firstOperand *= -1.0f;
        }
        if (operandTwoNegated) {
            secondOperand *= -1.0f;
        }
        switch (operator) {
            case "/":
                result = firstOperand / secondOperand;
                break;
            case "x":
                result = firstOperand * secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "+":
                result = firstOperand + secondOperand;
                break;
        }
        return result;
    }

    public float getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(float firstOperand) {
        this.firstOperand = firstOperand;
    }

    public float getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(float secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean isOperandOneNegated() {
        return operandOneNegated;
    }

    public void setOperandOneNegated(boolean operandOneNegated) {
        this.operandOneNegated = operandOneNegated;
    }

    public boolean isOperandTwoNegated() {
        return operandTwoNegated;
    }

    public void setOperandTwoNegated(boolean operandTwoNegated) {
        this.operandTwoNegated = operandTwoNegated;
    }
}
