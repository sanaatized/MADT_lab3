package com.example.madt_lab3;

public class CalculatorLogic {
    private double currentNumber;
    private double storedNumber;
    private String currentOperation;
    private boolean isNewNumber;
    private String currentInput;

    public CalculatorLogic() {
        clear();
    }

    public void clear() {
        currentNumber = 0;
        storedNumber = 0;
        currentOperation = "";
        isNewNumber = true;
        currentInput = "0";
    }

    public void appendNumber(String digit) {
        if (isNewNumber) {
            currentInput = digit;
            currentNumber = Double.parseDouble(digit);
            isNewNumber = false;
        } else {
            currentInput += digit;
            currentNumber = Double.parseDouble(currentInput);
        }
    }

    public void setOperation(String operation) {
        calculateResult();
        storedNumber = currentNumber;
        currentOperation = operation;
        isNewNumber = true;
    }

    public void calculateResult() {
        if (currentOperation.isEmpty()) return;

        switch (currentOperation) {
            case "+":
                currentNumber = storedNumber + currentNumber;
                break;
            case "-":
                currentNumber = storedNumber - currentNumber;
                break;
            case "×":
                currentNumber = storedNumber * currentNumber;
                break;
            case "÷":
                if (currentNumber != 0) {
                    currentNumber = storedNumber / currentNumber;
                }
                break;
        }
        currentOperation = "";
        isNewNumber = true;
        currentInput = String.valueOf(currentNumber);
    }

    public void squareRoot() {
        if (currentNumber >= 0) {
            currentNumber = Math.sqrt(currentNumber);
            currentInput = String.valueOf(currentNumber);
            isNewNumber = true;
        }
    }

    public void changeSign() {
        currentNumber = -currentNumber;
        currentInput = String.valueOf(currentNumber);
    }

    public void backspace() {
        if (!isNewNumber && currentInput.length() > 0) {
            currentInput = currentInput.length() > 1 ?
                    currentInput.substring(0, currentInput.length() - 1) : "0";
            currentNumber = Double.parseDouble(currentInput);
        }
    }

    public String getCurrentValue() {
        if (currentNumber == (long) currentNumber) {
            return String.format("%d", (long) currentNumber);
        }
        return String.format("%.8f", currentNumber).replaceAll("0*$", "").replaceAll("\\.$", "");
    }
}