/**
 * Day 1 of Wiley training
 */
package com.mycompany.wileyday1;

import java.util.Scanner;

/**
 *
 * @author Jasen Ratnam
 */
public class Main {
    
    private static void printMultiples(){
        /**
         * write an app to print "foo" for multiples of 3
         * "buz" for multiples of 5
         * "biz" for multiples of 7
         */
        
        for(int i = 1; i<= 50;i++){
            System.out.println(i);
            if(i%3 == 0)
                System.out.print(" foo ");
            if(i%5 == 0)
                System.out.print(" buz ");
            if(i%7 == 0)
                System.out.print(" biz ");
        }
    }
    
    private static void adder(){
        // declare and initialize variables
        int sum = 0;
        int operand1 = 0;
        int operand2 = 0;
        String op1 = "";
        String op2 = "";
        String input = "";
        boolean isValid = false;


        
        //scanner object to read input of user 
        Scanner myScanner = new Scanner(System.in);
        
        while(!isValid){
            System.out.println("Please enter the first number to be added:");
            
            op1 = myScanner.nextLine();
            
            if(op1 == null || op1.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    operand1 = Integer.parseInt(op1);
                    if (operand1 >= 1 && operand1 <= 10) {
                        isValid = true;
                    }
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
            

        }
        
        isValid = false;
        while(!isValid){
            System.out.println("Please enter the second number to be added:");
            
            op2 = myScanner.nextLine();
            
            if(op2 == null || op2.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    operand2 = Integer.parseInt(op2);
                    if (operand2 >= 1 && operand2 <= 10) {
                        isValid = true;
                    }
                    
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
        }
        
        try{
            //convert string to int
            operand1 = Integer.parseInt(op1);
            operand2 = Integer.parseInt(op2);
        } catch(NumberFormatException ex){
            System.out.println("Input could not be parsed into an integer");
        }
        
        //assign the sum of operand1 and operand2 to sum
        sum = operand1 + operand2;

        System.out.println("Sum is: " + sum);
    }
    
    //main method
    public static void main(String[] args) {
        
        //printMultiples();
        
        adder();
        
    }
    
}
