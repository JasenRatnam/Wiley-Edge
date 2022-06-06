/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.statecapitals3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author TheBoss
 */
public class StateCapitalsApp {
    
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
    
    public static void main(String[] args) {
        //map for capitals of states
        //states are the key
        Map<String, Capital> stateCapitals = new HashMap<>();
        String state = "";
        String capitalName = "";
        int population = 0;
        double squareMileage = 0;
        
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(new FileReader("MoreStateCapitals.txt")));
            
            // go through the file line by line
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] result = currentLine.split("::");
                state = result[0];
                
                try{
                    
                    capitalName = result[1];
                    population = Integer.parseInt(result[2]);
                    squareMileage = Double.parseDouble(result[3]);
                    
                    Capital capital = new Capital(capitalName, population,squareMileage);
                    
                    stateCapitals.put(state, capital);
                }catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed.");
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: reading file failed!");
        }
        
         System.out.println(stateCapitals.size() + "STATES & CAPITALS ARE LOADED.\n");
        
        System.out.println("===============");
                
         // get the Set of keys from the map
        Set<String> keys = stateCapitals.keySet();

        // print the keys and associated values to the screen
        for (String k : keys) {
            System.out.println(k + " - " + stateCapitals.get(k).getName() + 
                    " | Pop: " + stateCapitals.get(k).getPopulation() +
                    " | Area: " + stateCapitals.get(k).getSquareMilage() + "sq mi");
        }
        
        
        System.out.println("Please enter the lower limit for capital city population: ");
        int input = getIntInput();
        
        System.out.println("LISTING CAPITALS WITH POPULATIONS GREATER THAN " +  input + ": ");
        
        // print the keys and associated values to the screen
        for (String k : keys) {
            
            if(stateCapitals.get(k).getPopulation() > input){
                System.out.println(k + " - " + stateCapitals.get(k).getName() + 
                    " | Pop: " + stateCapitals.get(k).getPopulation() +
                    " | Area: " + stateCapitals.get(k).getSquareMilage() + "sq mi");
            }
        }
        
        System.out.println("Please enter the upper limit for capital city sq mileage: ");
        input = getIntInput();
        
        System.out.println("LISTING CAPITALS WITH AREA LESS THAN " +  input + ": ");

        // print the keys and associated values to the screen
        for (String k : keys) {
            
            if(stateCapitals.get(k).getSquareMilage() < input){
                System.out.println(k + " - " + stateCapitals.get(k).getName() + 
                    " | Pop: " + stateCapitals.get(k).getPopulation() +
                    " | Area: " + stateCapitals.get(k).getSquareMilage() + "sq mi");
            }
        }
        
        
    }
}
