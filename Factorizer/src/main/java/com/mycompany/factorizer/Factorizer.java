/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * receives an integer value from a user 
 * calculates and prints out a list that includes all of the factors of that number
 * whether or not the number is perfect, and whether or not the number is prime.
 *
 * A perfect number is a number where all the factors of the number, excluding
 * the number itself, add up to that number. 
 * For example, the first perfect number is 6 because its factors 1, 2, and 3 add up to 6.
 
 * A prime number is defined as a number greater than 1 that has only itself and
 * 1 as factors. 
 * For example, 3 is a prime number, but 4 is not.
 *
 * @author Jasen
 */
public class Factorizer {
    
    private static int getInput(){
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
    
    private static ArrayList<Integer> getFactors(int number){
        
        
        ArrayList<Integer> factors = new ArrayList<>();
       
        
        // loop runs from 1 to given number
        for (int i = 1; i <= number; ++i) {

          // if number is divided by i
          // i is the factor
          if (number % i == 0) {
            System.out.print(i + " ");
            factors.add(i);
          }
        }
        return factors;
    }
    
    private static boolean isPerfect(ArrayList<Integer> factors, int number){
        
        int sum = 0;
        for(int i = 0; i< factors.size()-1; i++){
            sum += factors.get(i);
        }
        
        if(sum == number){
            return true;
        }
        
        return false;
    }
    
    private static boolean isPrime(int number){
        for (int i = 2; i <= number / 2; ++i) {
            // condition for nonprime number
            if (number % i == 0) {
              return false;
            }
        }
        return true;
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("What number whould you like to factor?");
        
        int number = getInput();
        
        System.out.println("The factors of " + number + " are: ");
        ArrayList<Integer> factors = getFactors(number);
        
        System.out.println("\n" + number + " has " + factors.size() + " factors.");
        
        boolean perfect = isPerfect(factors, number);
        boolean prime = isPrime(number);
        
        System.out.print(number + " is ");
        if(!perfect)
            System.out.print(" not ");
        System.out.print(" a perfect number.\n");
       
        System.out.print(number + " is ");
        if(!prime)
            System.out.print(" not ");
        System.out.print(" a prime number.");        
    }
    
}
