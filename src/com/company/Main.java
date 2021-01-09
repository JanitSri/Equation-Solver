package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    static final Map<Character, Integer> inPrecedence = PrecedenceMap.INSIDE_STACK_PRECEDENCE;
    static final Map<Character, Integer> outPrecedence = PrecedenceMap.OUTSIDE_STACK_PRECEDENCE;

    private enum StackLocation{IN, OUT}

    static List<String> steps = new ArrayList<>();

    public static void main(String[] args) {
        Console.getHeader();
        Console.getSubHeader();

        String equation = Console.getInput();

        while(!equation.equals("q")){
            List<String> postfixForm = convertToPostfix(equation);
            double solution = evaluatePostfix(postfixForm);
            Console.stepsOutput(steps);
            Console.finalEquationOutput(equation, solution);

            steps.clear();
            equation = Console.getInput();
        }
        Console.goodBye();
    }

    /**
     * Check if passed in entity is an operator or not
     * @param entity operator or operand
     * @return       true if operator, false if operand
     */
    public static boolean isOperator(char entity){
        return outPrecedence.getOrDefault(entity, null) != null;
    }

    /**
     * Gets the precedence of the operator, used for associativity
     * @param operator      the operator to check for precedence
     * @param stackLocation enum representing if the operator is in the stack (StackLocation.IN) or not (StackLocation.IN)
     * @return the precedence of the operator
     */
    public static int getPrecedence(String operator, StackLocation stackLocation){
        char checkOperator = operator.charAt(0);
        return stackLocation == StackLocation.IN ?
                inPrecedence.getOrDefault(checkOperator, null) : outPrecedence.getOrDefault(checkOperator, null);
    }

    /**
     * Converts the equation (infix form) to the postfix form of the equation
     * @param equation the equation that was entered by the user
     * @return         list of the operands and operators in postfix form
     */
    public static List<String> convertToPostfix(String equation){

        LinkedListStack<String> stack = new LinkedListStack<>();
        char[] charArr = equation.trim().toCharArray();
        List<String> postfixList = new ArrayList<>();

        int charArrPos = 0;
        boolean isPrevNum = false;

        while(charArrPos < charArr.length){
            if(charArr[charArrPos] == ' '){
                charArrPos++;
            }

            if(!isOperator(charArr[charArrPos])){
                if(isPrevNum){
                    String updateVal = postfixList.get(postfixList.size()-1) + charArr[charArrPos];
                    postfixList.set(postfixList.size()-1, updateVal);
                }else{
                    postfixList.add(String.valueOf(charArr[charArrPos]));
                }
                isPrevNum = true;
                charArrPos++;
            }else{
                isPrevNum = false;
                int outStack = getPrecedence(String.valueOf(charArr[charArrPos]), StackLocation.OUT);
                int inStack = stack.isEmpty() ? -1 : getPrecedence(stack.stackTop(), StackLocation.IN);

                if(stack.isEmpty() || outStack > inStack){
                    stack.push(String.valueOf(charArr[charArrPos++]));
                }else if (outStack == inStack){
                    stack.pop();
                    charArrPos++;
                }else{
                    postfixList.add(stack.pop());
                }
            }
        }

        while(!stack.isEmpty()){
            postfixList.add(stack.pop());
        }

        return postfixList;
    }

    /**
     * Evaluates the equation in postfix form
     * @param postfixEquation the postfix form of the equation
     * @return                the solution to the equation
     */
    private static double evaluatePostfix(List<String> postfixEquation) {
        LinkedListStack<String> stack = new LinkedListStack<>();

        for(String entity: postfixEquation){
            if(!isOperator(entity.charAt(0))){
                stack.push(entity);
            }else{
                String rightOperand = stack.pop();
                String leftOperand = stack.pop();

                double operationResult = OperationMap.getOperationResult(leftOperand, rightOperand, entity);
                stack.push(String.valueOf(operationResult));

                String step = String.format("%8s", String.format("%.2f", Double.parseDouble(leftOperand))) + " "
                        + entity + " " + String.format("%.2f", Double.parseDouble(rightOperand))
                        + String.format("%5s", "(") + String.format("%.2f", operationResult) + ")";
                steps.add(step);
            }
        }

        return Double.parseDouble(stack.pop());
    }
}