package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    static final Map<Character, Integer> inPrecedence = PrecedenceMap.INSIDE_STACK_PRECEDENCE;
    static final Map<Character, Integer> outPrecedence = PrecedenceMap.OUTSIDE_STACK_PRECEDENCE;

    static List<String> steps = new ArrayList<>();

    private enum StackLocation{IN, OUT;}

    public static void main(String[] args) {
        String equation = "(3+2)^2^2";
        List<String> postfixForm = convertToPostfix(equation);
        double result = evaluatePostfix(postfixForm);
        System.out.println(equation + " = " + result);

        steps.forEach(System.out::println);
    }

    public static boolean isOperator(char entity){
        return outPrecedence.getOrDefault(entity, null) != null;
    }

    public static int getPrecedence(String operator, StackLocation stackLocation){
        char checkOperator = operator.charAt(0);
        return stackLocation == StackLocation.IN ?
                inPrecedence.getOrDefault(checkOperator, null) : outPrecedence.getOrDefault(checkOperator, null);
    }

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

                String step = String.format("%.2f", Double.parseDouble(leftOperand)) + " "
                        + entity + " " + String.format("%.2f", Double.parseDouble(rightOperand))
                        + "\t(" + String.format("%.2f", operationResult) + ")";
                steps.add(step);
            }
        }

        return Double.parseDouble(stack.pop());
    }
}
