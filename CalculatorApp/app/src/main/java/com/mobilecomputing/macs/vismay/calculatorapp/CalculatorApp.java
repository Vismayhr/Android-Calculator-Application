/*==========================================
Title:  Handles the operations of the calculator
Author: Vismay Revankar (vismayhr@dal.ca)
Date:   1 Oct 2018
============================================*/

package com.mobilecomputing.macs.vismay.calculatorapp;

import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class CalculatorApp extends Object {
    private Calculator calculator;
    private TextView calculatorDisplay;
    private boolean OperandOne;
    private boolean OperandTwo;
    private boolean operandDecimal;
    private int decimalPosition;
    private boolean decimalIncomplete;
    private boolean operatorExpected;
    private StringBuffer expression;
    private boolean expressionCompleted;
    private Set<Integer> operands;
    private Set<Integer> memoryFunctions;
    private float memoryValue;

    public CalculatorApp(TextView textView) {
        this.calculator = new Calculator();
        this.calculatorDisplay = textView;
        this.calculatorDisplay.setText("0");
        this.expression = new StringBuffer();
        this.expressionCompleted = false;
        this.OperandOne = false;
        this.OperandTwo = false;
        this.operandDecimal = false;
        this.decimalPosition = 0;
        this.decimalIncomplete = false;
        this.operatorExpected = false;
        this.memoryValue = 0f;
        this.operands = new HashSet<Integer>();
        this.memoryFunctions = new HashSet<Integer>();
        this.initialiseOperandsSet();
        this.initialiseMemoryFunctions();
    }

    // Create an Integer set containing IDs of all numeric buttons
    private void initialiseOperandsSet() {
        operands.add(R.id.btnZero);
        operands.add(R.id.btnOne);
        operands.add(R.id.btnTwo);
        operands.add(R.id.btnThree);
        operands.add(R.id.btnFour);
        operands.add(R.id.btnFive);
        operands.add(R.id.btnSix);
        operands.add(R.id.btnSeven);
        operands.add(R.id.btnEight);
        operands.add(R.id.btnNine);
    }

    //Create an Integer set containing the IDs of memory function buttons
    private void initialiseMemoryFunctions() {
        memoryFunctions.add(R.id.btnMPlus);
        memoryFunctions.add(R.id.btnMMinus);
        memoryFunctions.add(R.id.btnMClear);
        memoryFunctions.add(R.id.btnMRecall);
    }

    // Reset all the values and clear the calculator display
    private void resetValues() {
        this.calculator = new Calculator();
        this.expression = new StringBuffer();
        this.expressionCompleted = false;
        this.OperandOne = false;
        this.OperandTwo = false;
        this.operandDecimal = false;
        this.decimalPosition = 0;
        this.decimalIncomplete = false;
        this.operatorExpected = false;
        this.updateCalculatorDisplay("0");
    }

    public boolean parseCalculatorInput(int buttonId, String buttonValue) {
        //Return to the MainActivity if more than 10 digits are displayed on the screen
        if (!calculatorCanDisplay()) {
            this.resetValues();
            return (false);
        }
        //Handle clicks for the C(clear) button
        if (buttonId == R.id.btnClearScreen) {
            this.resetValues();
        } else if (memoryFunctions.contains(buttonId)) {    //Handle clicks of the memory function
            switch (buttonId) {                             // buttons M+, M-, MC and MR
                case R.id.btnMPlus:
                    if (isOperandOne() && !isOperandTwo()) {
                        float op1 = calculator.getFirstOperand();
                        if (calculator.isOperandOneNegated()) {
                            op1 = op1 * (-1);
                        }
                        memoryValue = memoryValue + op1;
                    } else if (isOperandOne() && isOperandTwo()) {
                        float op2 = calculator.getSecondOperand();
                        if (calculator.isOperandTwoNegated()) {
                            op2 = op2 * (-1);
                        }
                        memoryValue = memoryValue + op2;
                    }
                    break;
                case R.id.btnMMinus:
                    if (isOperandOne() && !isOperandTwo()) {
                        float op1 = calculator.getFirstOperand();
                        if (calculator.isOperandOneNegated()) {
                            op1 = op1 * (-1);
                        }
                        memoryValue = memoryValue - op1;
                    } else if (isOperandOne() && isOperandTwo()) {
                        float op2 = calculator.getSecondOperand();
                        if (calculator.isOperandTwoNegated()) {
                            op2 = op2 * (-1);
                        }
                        memoryValue = memoryValue - op2;
                    }
                    break;

                case R.id.btnMClear:
                    memoryValue = 0f;
                    break;

                case R.id.btnMRecall:
                    if (!isOperandOne() && !isOperandTwo()) {
                        calculator.setFirstOperand((memoryValue));
                        OperandOne = true;
                        operatorExpected = true;
                        operandDecimal = true;
                        decimalIncomplete = false;
                        expression.append(String.valueOf(memoryValue));
                        updateCalculatorDisplay(this.expression.toString());
                    } else if (isOperandOne() && !isOperandTwo()) {
                        if (isOperatorExpected()) {
                            calculator.setFirstOperand((memoryValue));
                            expression = new StringBuffer(String.valueOf(memoryValue));
                            updateCalculatorDisplay(this.expression.toString());
                            operandDecimal = true;
                            decimalIncomplete = false;
                        } else {
                            calculator.setSecondOperand(memoryValue);
                            expression.append(String.valueOf(memoryValue));
                            this.updateCalculatorDisplay(this.expression.toString());
                            operandDecimal = true;
                            decimalIncomplete = false;
                            expressionCompleted = true;
                        }
                    } else if (isOperandOne() && isOperandTwo()) {
                        if (!isDecimalIncomplete()) {
                            float op2 = calculator.getSecondOperand();
                            String currentScreen = calculatorDisplay.getText().toString();
                            int indexOfSecondOperand = currentScreen.lastIndexOf(String.valueOf(op2));  // Locate the second operand in the calculator screen
                            StringBuilder builder = new StringBuilder();                                // and replace it with the value in Memory
                            builder.append(currentScreen.substring(0, indexOfSecondOperand));
                            builder.append(String.valueOf(memoryValue));
                            String newScreen = builder.toString();
                            expression = new StringBuffer(newScreen);
                            this.updateCalculatorDisplay(this.expression.toString());
                            calculator.setSecondOperand(memoryValue);
                            operandDecimal = true;
                            decimalIncomplete = false;
                            expressionCompleted = true;
                        }
                    }
                    break;
            }
        } else if (operands.contains(buttonId)) {           // Handle operations for numeric input
            if (!isOperandOne() && !isOperandTwo()) {
                OperandOne = true;
                if (isDecimalIncomplete()) {
                    float op1 = (Float.parseFloat(buttonValue)) / 10;
                    calculator.setFirstOperand(op1);
                    decimalIncomplete = false;
                    decimalPosition = decimalPosition + 1;
                } else {
                    calculator.setFirstOperand(Float.parseFloat(buttonValue));
                }
                expression.append(buttonValue);
                this.updateCalculatorDisplay(this.expression.toString());
                operatorExpected = true;
            } else if (isOperandOne() && !isOperandTwo()) {
                if (isOperatorExpected()) {
                    float op1 = calculator.getFirstOperand();
                    if (isOperandDecimal()) {
                        float decimalValue = Float.parseFloat(buttonValue);
                        for (int i = 0; i < decimalPosition; i++) {
                            decimalValue = decimalValue / 10.0f;
                        }
                        op1 += decimalValue;
                        if (isDecimalIncomplete()) {
                            decimalIncomplete = false;
                        }
                        decimalPosition = decimalPosition + 1;
                    } else {
                        op1 = (op1 * 10.0f) + Float.parseFloat(buttonValue);
                    }
                    calculator.setFirstOperand(op1);
                    expression.append(buttonValue);
                    this.updateCalculatorDisplay(this.expression.toString());
                } else {
                    OperandTwo = true;
                    if (isDecimalIncomplete()) {
                        float op2 = (Float.parseFloat(buttonValue)) / 10;
                        calculator.setSecondOperand(op2);
                        decimalIncomplete = false;
                        decimalPosition = decimalPosition + 1;
                    } else {
                        calculator.setSecondOperand(Float.parseFloat(buttonValue));
                    }
                    expression.append(buttonValue);
                    this.updateCalculatorDisplay(this.expression.toString());
                    expressionCompleted = true;
                }
            } else {
                float op2 = calculator.getSecondOperand();
                if (isOperandDecimal()) {
                    float decimalValue = Float.parseFloat(buttonValue);
                    for (int i = 0; i < decimalPosition; i++) {
                        decimalValue = decimalValue / 10.0f;
                    }
                    op2 += decimalValue;
                    if (isDecimalIncomplete()) {
                        decimalIncomplete = false;
                    }
                    decimalPosition = decimalPosition + 1;
                } else {
                    op2 = (op2 * 10.0f) + Float.parseFloat(buttonValue);
                }
                calculator.setSecondOperand(op2);
                expression.append(buttonValue);
                this.updateCalculatorDisplay(this.expression.toString());
            }
        } else {                                          // Handle clicks of operator buttons such as +,-,/,x
            if (!isDecimalIncomplete()) {
                if (expressionCompleted && ((buttonId != R.id.btnDecimal) && (buttonId != R.id.btnEquals) && (buttonId != R.id.btnInvertSign))) {
                    String btnValue = buttonValue;
                    StringBuilder temp = new StringBuilder();
                    temp.append(String.valueOf(calculator.getFirstOperand()));
                    temp.append(calculator.getOperator());
                    temp.append(String.valueOf(calculator.getSecondOperand()));
                    boolean firstExpressionEvaluated = evaluateExpression();
                    if (!firstExpressionEvaluated) {
                        return (false);
                    }
                    calculator.setOperator(btnValue);
                    operatorExpected = false;
                    expression.append(btnValue);
                    this.updateCalculatorDisplay(this.expression.toString());
                } else {
                    switch (buttonId) {
                        case R.id.btnDivide:
                            if (isOperatorExpected()) {
                                operandDecimal = false;
                                calculator.setOperator("/");
                                operatorExpected = false;
                                expression.append(buttonValue);
                                this.updateCalculatorDisplay(this.expression.toString());
                            }
                            break;
                        case R.id.btnMultiply:
                            if (isOperatorExpected()) {
                                operandDecimal = false;
                                calculator.setOperator("x");
                                operatorExpected = false;
                                expression.append(buttonValue);
                                this.updateCalculatorDisplay(this.expression.toString());
                            }
                            break;
                        case R.id.btnSubtract:
                            if (!isOperandOne() && !isOperandTwo()) {
                                if (!calculator.isOperandOneNegated()) {
                                    operandDecimal = false;
                                    calculator.setOperandOneNegated(true);
                                    expression.append(buttonValue);
                                    this.updateCalculatorDisplay(this.expression.toString());
                                }
                            } else if (isOperandOne() && !isOperandTwo()) {
                                operandDecimal = false;
                                if (isOperatorExpected()) {
                                    calculator.setOperator(buttonValue);
                                    operatorExpected = false;
                                    expression.append(buttonValue);
                                    this.updateCalculatorDisplay(this.expression.toString());
                                } else {
                                    if (!calculator.isOperandTwoNegated()) {
                                        calculator.setOperandTwoNegated(true);
                                        expression.append(buttonValue);
                                        this.updateCalculatorDisplay(this.expression.toString());
                                    }
                                }
                            }
                            break;

                        case R.id.btnAdd:
                            if (isOperatorExpected()) {
                                operandDecimal = false;
                                calculator.setOperator("+");
                                operatorExpected = false;
                                expression.append(buttonValue);
                                this.updateCalculatorDisplay(this.expression.toString());
                            } else if (calculatorDisplay.getText().toString().equals("0")) {
                                expression = new StringBuffer("+");
                                this.updateCalculatorDisplay(this.expression.toString());
                            }
                            break;

                        case R.id.btnEquals:
                            StringBuilder temp = new StringBuilder();
                            temp.append(String.valueOf(calculator.getFirstOperand()));
                            temp.append(calculator.getOperator());
                            temp.append(String.valueOf(calculator.getSecondOperand()));
                            boolean expressionResult = evaluateExpression();
                            if (!expressionResult) {
                                return (false);
                            }
                            break;

                        case R.id.btnDecimal:
                            if (!isOperandDecimal()) {
                                operandDecimal = true;
                                decimalPosition = 1;
                                decimalIncomplete = true;
                                expression.append(buttonValue);
                                this.updateCalculatorDisplay(this.expression.toString());
                            }
                            break;

                        case R.id.btnInvertSign:
                            String screenContents = calculatorDisplay.getText().toString();
                            if (isOperandOne() && !isOperandTwo()) {
                                boolean sign = calculator.isOperandOneNegated();
                                calculator.setOperandOneNegated(!sign);
                                if (screenContents.charAt(0) == '-') {
                                    expression = new StringBuffer("+" + screenContents.substring(1));
                                    this.updateCalculatorDisplay(this.expression.toString());
                                } else if (screenContents.charAt(0) == '+') {
                                    expression = new StringBuffer("-" + screenContents.substring(1));
                                    this.updateCalculatorDisplay(this.expression.toString());
                                } else {
                                    expression = new StringBuffer("-" + screenContents);
                                    this.updateCalculatorDisplay(this.expression.toString());
                                }
                            } else if (screenContents.equals("-")) {
                                boolean sign = calculator.isOperandOneNegated();
                                calculator.setOperandOneNegated(!sign);
                                expression = new StringBuffer("+");
                                this.updateCalculatorDisplay(this.expression.toString());
                            } else if (screenContents.equals("+")) {
                                boolean sign = calculator.isOperandOneNegated();
                                calculator.setOperandOneNegated(!sign);
                                expression = new StringBuffer("-");
                                this.updateCalculatorDisplay(this.expression.toString());
                            }
                            break;
                    }
                }

            }
        }
        return (true);
    }

    // Method to ensure that more than 10 digits are not displayed on the screen at a time
    private boolean calculatorCanDisplay() {
        if (expression.length() >= 10) {
            int digitCount = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (Character.isDigit(expression.charAt(i))) {
                    digitCount++;
                }
            }
            if (digitCount > 10) {
                return (false);
            }
        }
        return (true);
    }

    // Method to evaluate the parsed expression and display the result
    private boolean evaluateExpression() {
        if (isExpressionCompleted()) {
            float result = calculator.calculate();
            String resultAsString = String.valueOf(result);
            this.resetValues();
            if (result % 1.0f == 0.0f) {        //Truncate decimal part if the decimal value is 0
                if (resultAsString.length() > 10) {
                    return (false);
                }
                this.updateCalculatorDisplay(String.valueOf((int) result));
            } else {
                String concatenatedResult = String.format("%.3f", result);
                if (concatenatedResult.length() > 10) {
                    return (false);
                }
                this.updateCalculatorDisplay(String.valueOf(concatenatedResult));
            }
            calculator.setFirstOperand(result);
            if (result % 1.0f == 0.0f) {
                expression.append(String.valueOf((int) result));
            } else {
                expression.append(String.valueOf(result));
            }
            OperandOne = true;
            operatorExpected = true;
        }
        return (true);

    }

    // Refresh the calculator display with the String passed as a param
    private void updateCalculatorDisplay(String display) {
        calculatorDisplay.setText(display);
    }

    public boolean isOperandOne() {
        return OperandOne;
    }

    public boolean isOperandTwo() {
        return OperandTwo;
    }

    public boolean isOperatorExpected() {
        return operatorExpected;
    }

    public boolean isExpressionCompleted() {
        return expressionCompleted;
    }

    public boolean isOperandDecimal() {
        return operandDecimal;
    }

    public boolean isDecimalIncomplete() {
        return decimalIncomplete;
    }
}
