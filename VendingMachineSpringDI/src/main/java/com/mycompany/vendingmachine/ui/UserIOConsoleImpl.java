
package com.mycompany.vendingmachine.ui;

import java.util.Scanner;

/**
 * This is the console-specific implementation of the UserIO interface.
 * @author Jasen
 */
public class UserIOConsoleImpl implements UserIO{
    
    /**
     * print a string message to the user
     * @param message 
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * ask the user for a string input
     * @param prompt to user
     * @return user input
     */
    @Override
    public String readString(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        String input;
        
        
        print(prompt);   
        input = myScanner.nextLine();

        return input;
    }

    /**
     * ask the user for an int input
     * @param prompt to user
     * @return user input
     */
    @Override
    public int readInt(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        int inputInt = -1;
        
        
        print(prompt);   
        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputInt = Integer.parseInt(input);
                    isValid = true;
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into an integer, try again.");
                    isValid = false;
                }
            }
        }
        return inputInt;    
    }

    /**
     * ask user for an int input with a min and max
     * @param prompt to user
     * @param min of input
     * @param max of input
     * @return user input
     */
    @Override
    public int readInt(String prompt, int min, int max) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        int inputInt = -1;
        
        
        print(prompt);   
        print("Range of " + min + " - " +  max);

        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputInt = Integer.parseInt(input);
                    if (inputInt <= max && inputInt>=min)
                        isValid = true;
                    else {
                        print("ERROR: number exceeds the allowed range");
                        print("Range of " + min + " - " +  max);
                        isValid = false;
                    }
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into an integer, try again.");
                    isValid = false;
                }
            }
        }
        return inputInt;    
    }

    /**
     * ask user for an double input 
     * @param prompt to user
     * @return user input
     */
    @Override
    public double readDouble(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        double inputDouble = -1;
        
        
        print(prompt);   
        
        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputDouble = Double.parseDouble(input);
                    isValid = true;
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into a double, try again.");
                    isValid = false;
                }
            }
        }
        return inputDouble;    
    }

    /**
     * ask user for an double input with a min and max
     * @param prompt to user
     * @param min of input
     * @param max of input
     * @return user input
     */
    @Override
    public double readDouble(String prompt, double min, double max) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        double inputDouble = -1;
        
        
        print(prompt);   
        print("Range of " + min + " - " +  max);

        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputDouble = Double.parseDouble(input);
                    if (inputDouble <= max && inputDouble>=min)
                        isValid = true;
                    else {
                        print("ERROR: number exceeds the allowed range");
                        print("Range of " + min + " - " +  max);
                        isValid = false;
                    }
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into an double, try again.");
                    isValid = false;
                }
            }
        }
        return inputDouble;    
    }

    /**
     * ask user for an float input 
     * @param prompt to user
     * @return user input
     */
    @Override
    public float readFloat(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        float inputFloat = -1;
        
        
        print(prompt);   
        
        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputFloat = Float.parseFloat(input);
                    isValid = true;
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into a float, try again.");
                    isValid = false;
                }
            }
        }
        return inputFloat;    
    }

    /**
     * ask user for an float input with a min and max
     * @param prompt to user
     * @param min of input
     * @param max of input
     * @return user input
     */
    @Override
    public float readFloat(String prompt, float min, float max) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        float inputFloat = -1;
        
        
        print(prompt);   
        print("Range of " + min + " - " +  max);

        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputFloat = Float.parseFloat(input);
                    if (inputFloat <= max && inputFloat>=min)
                        isValid = true;
                    else {
                        print("ERROR: number exceeds the allowed range");
                        print("Range of " + min + " - " +  max);
                        isValid = false;
                    }
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into an float, try again.");
                    isValid = false;
                }
            }
        }
        return inputFloat;    
    }

    /**
     * ask user for an long input 
     * @param prompt to user
     * @return user input
     */
    @Override
    public long readLong(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        long inputLong = -1;
        
        
        print(prompt);   
        
        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputLong = Long.parseLong(input);
                    isValid = true;
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into a long, try again.");
                    isValid = false;
                }
            }
        }
        return inputLong;  
    }

    /**
     * ask user for an long input with a min and max
     * @param prompt to user
     * @param min of input
     * @param max of input
     * @return user input
     */
    @Override
    public long readLong(String prompt, long min, long max) {
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        long inputLong = -1;
        
        
        print(prompt);   
        print("Range of " + min + " - " +  max);

        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                print("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputLong = Long.parseLong(input);
                    if (inputLong <= max && inputLong>=min)
                        isValid = true;
                    else {
                        print("ERROR: number exceeds the allowed range");
                        print("Range of " + min + " - " +  max);
                        isValid = false;
                    }
                } catch(NumberFormatException ex){
                    print("Input could not be parsed into an long, try again.");
                    isValid = false;
                }
            }
        }
        return inputLong;    
    }
}
