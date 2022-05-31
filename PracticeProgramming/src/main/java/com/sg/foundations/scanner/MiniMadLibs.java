/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.scanner;

import java.util.Scanner;

/**
 *
 * @author TheBoss
 */
public class MiniMadLibs {
    
    private static String getInput(){
        Scanner myScanner  = new Scanner(System.in);
        boolean isValid = false;
        String input = "";
        
        while(!isValid){            
            input = myScanner.nextLine();
            
            if(input == null || input.isEmpty())
                System.out.println("Please enter a valid entry");
            else
               isValid = true;  
        }
        
        return input;
    }
    
    public static void main(String[] args) {
        
        String noun1;
        String adjective1;
        String noun2;
        String number;
        String adjective2;
        String pluralNoun1;
        String pluralNoun2;
        String pluralNoun3;
        String verbPresent;
        String verbPast;
        
        System.out.println("Let's play MAD LIBS!\n");
        
        System.out.println("I need a noun: ");
        noun1 = getInput();
        System.out.println("Now an adjective: ");
        adjective1 = getInput();
        System.out.println("Another noun: ");
        noun2 = getInput();
        System.out.println("And a number: ");
        number = getInput();
        System.out.println("Another adjective: ");
        adjective2 = getInput();
        System.out.println("A plural noun: ");
        pluralNoun1 = getInput();
        System.out.println("Another one: ");
        pluralNoun2 = getInput();
        System.out.println("One more: ");
        pluralNoun3 = getInput();
        System.out.println("A verb (infinitive form): ");
        verbPresent = getInput();
        System.out.println("Same verb (past participle): ");
        verbPast = getInput();
        
        System.out.println("\n*** NOW LETS GET MAD (libs) ***");
        
        
        System.out.println(noun1 + ": the " + adjective1 + " frontier. "
                + "These are the voyages of the starship " + noun2 + 
                ". Its " + number +"-year mission: to explore strange " +
                adjective2  + " " + pluralNoun1 + ", to seek out " + adjective2 
                + " " + pluralNoun2 + " and " + adjective2 
                + " " + pluralNoun3 + ", to boldly " + verbPresent + 
                " where no one has " + verbPast + " before.");
        
    }
    
}
