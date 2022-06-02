/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interestcalculator;

import java.util.Scanner;

/**
 * interest calculator program that works as described in this example:
 * 
 * John has $500 to invest. Sue knows of a mutual fund plan that pays 10% 
 * interest annually, compounded quarterly. That is, every three months, 
 * the principal is multiplied by 2.5% (the 10% annual rate divided by 4 
 * because it is compounded 4 times per year) and the result is added to t
 * he principal.
 * 
 * The new amount each quarter is equal to:
 * CurrentBalance * (1 + (QuarterlyInterestRate / 100))
 * 
 * @author Jasen
 */
public class InterestCalculator {
    
    
    private static int getInputInt(){
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
                    if (inputInt > 0) 
                        isValid = true;
                    else
                        System.out.println("Please enter a number greater than 0");
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
        }
        return inputInt;
    }
    
    private static double getInputDouble(){
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        double inputDouble = -1;
        
        while(!isValid){            
            String input = myScanner.nextLine();
            
            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputDouble = Double.parseDouble(input);
                    if (inputDouble > 0) 
                        isValid = true;
                    else
                        System.out.println("Please enter a number greater than 0");
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
        }
        return inputDouble;
    }
    
    private static double newAmount(double quarterlyInterestRate, double currentBalance){
        return currentBalance * (1 + (quarterlyInterestRate / 100));
    }
    
    
    public static void main(String[] args) {
        System.out.println("How much do you want to invest?");
        double currentBalance = getInputDouble();
        
        System.out.println("How many years are you investing?");
        int years = getInputInt();
        
        System.out.println("What is the annual interest rate % growth?");
        double interest = getInputDouble();
        
        System.out.println("\nCalculating...");
        
        double quarterlyInterestRate = interest/4;
        double newBalance = currentBalance;
        double gained = 0.0;
        
        for(int i=0;i<years;i++){
            for(int j =0;j<4;j++){
                newBalance = newAmount(quarterlyInterestRate, newBalance);
            }
            
            gained = newBalance - currentBalance;
            
            System.out.println("Year " + (i+1) + ": ");
            System.out.println("Begin with $" + currentBalance);
            System.out.println("Earned $" + gained);
            System.out.println("Ended with $" + newBalance + "\n");
            
            currentBalance = newBalance;
        }

    }
    
}
