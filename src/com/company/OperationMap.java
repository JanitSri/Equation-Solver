package com.company;


@SuppressWarnings("ConstantConditions")
public class OperationMap {
    public enum Operation{
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        POWER("^");

        public final String label;

        Operation(String label) {
            this.label = label;
        }

        public static Operation getOperatorFromString(String stringOperator) {
            for (Operation operator : Operation.values()) {
                if (operator.label.equalsIgnoreCase(stringOperator)) {
                    return operator;
                }
            }
            return null;
        }
    }

    static double getOperationResult(String operand1, String operand2, String operator){
        double a = Double.parseDouble(operand1);
        double b = Double.parseDouble(operand2);

        switch(Operation.getOperatorFromString(operator)){
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
            case POWER:
                return Math.pow(a,b);
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }

}
