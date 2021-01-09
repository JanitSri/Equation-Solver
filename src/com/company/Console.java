package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Console {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String RED_BOLD = "\033[1;31m";
    private static final String CYAN_BOLD = "\033[1;36m";
    private static final String PURPLE_BOLD = "\033[1;35m";
    private static final String BLUE_BOLD = "\033[1;34m";
    private static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";


    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the header for display from the file and outputs it to the console.
     */
    public static void getHeader(){
        Path fileName = Paths.get("header.txt");
        String header = null;
        try {
            header = Files.readString(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(RED_BOLD + header + ANSI_RESET + "\n\n");
    }

    /**
     * Displays the subheader. Subheader notifies the user of the available operations.
     */
    public static void getSubHeader(){
        System.out.println(PURPLE_BOLD + "\t***Current Operations Supported***");
        for (OperationMap.Operation operator : OperationMap.Operation.values()) {
            System.out.printf("%20s: %2s%n", operator.name(), operator.label);
        }

        System.out.printf("%20s: %2s%n", "PARENTHESES", "( )");
        System.out.println(ANSI_RESET);
    }

    /**
     * Get the equation input from the user. Continue to query for input if empty line is entered.
     * @return the equation entered by the user or 'q' to quit
     */
    public static String getInput(){
        String line;
        System.out.print("Enter equation ('q' to quit): ");
        while((line = scanner.nextLine().trim()).isEmpty()) {
            System.out.print("Enter equation - equation cannot be empty ('q' to quit): ");
        }
        return line;
    }

    /**
     * Output the steps taken in solving the equation.
     * @param steps the steps taken to evaluate the equation
     */
    public static void stepsOutput(List<String> steps){
        System.out.println(CYAN_BOLD + "\t<<< Operation Steps >>>");
        for(String step: steps){
            System.out.printf("%s%n", step);
        }

        System.out.println(ANSI_RESET);
    }

    /**
     * Output the equation and the solution of equation
     * @param equation the equation entered by the user
     * @param solution the solution for the equation
     */
    public static void finalEquationOutput(String equation, double solution){
        System.out.println("\t" + BLUE_BOLD + equation + " = " + String.format("%.2f", solution));
        System.out.println(ANSI_RESET);
    }

    public static void goodBye(){
        System.out.println("\n" + YELLOW_BACKGROUND_BRIGHT + RED_BOLD + "GOOD BYE!!!" + ANSI_RESET);
    }
}