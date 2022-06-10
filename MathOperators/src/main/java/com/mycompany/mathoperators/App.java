
package com.mycompany.mathoperators;

import java.util.Scanner;

/**
 *
 * @author Jasen Ratnam
 */
public class App {
    
     private static int getIntInput(){
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        int inputInt = -1;

        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputInt = Integer.parseInt(input);
                    isValid = true;
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer, try again.");
                    isValid = false;
                }
            }
        }
        return inputInt;
    }
     
    private static int calculate(MathOperator operator, int operand1, int operand2) {
        switch(operator) {
               case PLUS:
                     return operand1 + operand2;
               case MINUS:
                     return operand1 - operand2;
               case MULTIPLY:
                     return operand1 * operand2;
               case DIVIDE:
                     return operand1 / operand2;
               default:
                     throw new UnsupportedOperationException();
        }
    }
     
    public static void main(String[] args) {
        System.out.println("Give first operand: ");
        int op1 = getIntInput();
        
        System.out.println("Give second operand: ");
        int op2 = getIntInput();
        
        double result = 0;
        
        result = calculate(MathOperator.PLUS, op1, op2);
        System.out.println(op1 + " + " + op2 + " = " + result);
        
        result = calculate(MathOperator.MINUS, op1, op2);
        System.out.println(op1 + " - " + op2 + " = " + result);
        
        result = calculate(MathOperator.MULTIPLY, op1, op2);
        System.out.println(op1 + " * " + op2 + " = " + result);
        
        result = calculate(MathOperator.DIVIDE, op1, op2);
        System.out.println(op1 + " / " + op2 + " = " + result);
    }
}
