/*
 * Wiley-edge code-along
 */
package com.mycompany.windowmaster;

import java.util.Scanner;

/**
 * The purpose of the program is to calculate the total cost for home replacement windows
 * @author Jasen Ratnam
 */
public class WindowMaster {
    /**
     * Requirements:
     * It must prompt the user for the height of the window (in feet).
     * It must prompt the user for the width of the window (in feet).
     * It must calculate and display the area of the window.
     * It must calculate and display the perimeter of the window.
     * Based on the area and perimeter, it must calculate the total cost of the window.
     * The glass for the windows cost $3.50 per square foot.
     * The trim for the windows cost $2.25 per linear foot.
     */
    
    
    private static int getIntInput(){
        Scanner myScanner = myScanner = new Scanner(System.in);
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
    
    private static float getFloatInput(){
        Scanner myScanner = myScanner = new Scanner(System.in);
        boolean isValid = false;
        float inputFloat = -1;
        
        while(!isValid){            
            String input = myScanner.nextLine();
            
            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputFloat = Float.parseFloat(input);
                    if (inputFloat > 0) 
                        isValid = true;
                    else
                        System.out.println("Please enter a number greater than 0");
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
        }
        return inputFloat;
    }
    
    private static float getArea(float height, float width){
        return height * width;
    }
    
    private static float getPerimeter(float height, float width){
        return 2*height + 2*width;
    }
    
    private static float getCost(float area, float perimeter, double glassCost, double trimCost, int numWindows){
        //glass is $3.50 per square foot
        //trim is $2.25 per linear foot.
        return (float) (glassCost*area + trimCost*perimeter) * numWindows;
    }
    
    public static void main(String[] args){
       
        System.out.println("Please enter the number of windows wanted");
        int numWindows = getIntInput();
        
        System.out.println("Please enter the cost of glass");
        float glassCost = getFloatInput();
        
        System.out.println("Please enter the cost of trim");
        float trimCost = getFloatInput();
        
        System.out.println("Please enter the height of the window: (in feet)");
        float height = getFloatInput();
        
        System.out.println("Please enter the width of the window: (in feet)");
        float width  = getFloatInput();
        
        float area = getArea(height, width);
        float perimeter = getPerimeter(height, width);
        double cost = getCost(area, perimeter, glassCost, trimCost, numWindows);
        
        System.out.println("\nHeight is: " + height);
        System.out.println("Width is: " + width);
        System.out.println("Area is: " + area);
        System.out.println("Perimeter is: " + perimeter);
        System.out.println("Cost is: " + cost);
    }
}
