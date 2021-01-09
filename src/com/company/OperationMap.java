package com.company;

public class OperationMap {

    /**
     * Enum representing the available operations of the program
     */
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

    /**
     *
     * Performs an operation based on the operands and operator passed in.
     *
     * @param leftOperand  the left operand
     * @param rightOperand the right operand
     * @param operator     the operator used by the Operation enum
     * @return             the result of the operation
     */
    static double getOperationResult(String leftOperand, String rightOperand, String operator){
        double a = Double.parseDouble(leftOperand);
        double b = Double.parseDouble(rightOperand);

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