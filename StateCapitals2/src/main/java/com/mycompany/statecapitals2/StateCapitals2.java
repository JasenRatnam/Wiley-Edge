/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.statecapitals2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Jasen
 */
public class StateCapitals2 {
    
    private static String getInput(){
        Scanner myScanner  = new Scanner(System.in);
        boolean isValid = false;
        String input = null;

        while(!isValid){
            input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                isValid = true;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        //map for capitals of states
        //states are the key
        Map<String, String> stateCapitals = new HashMap<>();
        
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(new FileReader("StateCapitals.txt")));
            
            // go through the file line by line
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] result = currentLine.split("::");
                stateCapitals.put(result[0], result[1]);
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: reading file failed!");
        }
        
        System.out.println(stateCapitals.size() + "STATES & CAPITALS ARE LOADED.\n");
        
        System.out.println("=========");
        
        System.out.println("HERE ARE THE STATES : ");
        
         // get the Set of keys from the map
        Set<String> keys = stateCapitals.keySet();

        // print the states to the screen
        for (String k : keys) {
            System.out.print(k + ",");
        }
        
        System.out.println("READY TO TEST YOUR KNOWLEDGE?");
        System.out.println("WHAT IS THE CAPITAL OF 'Alaska'?");
        
        String input = getInput();
        
        if(input.equals(stateCapitals.get("Alaska")))
            System.out.println("NICE WORK! " + input + " IS CORRECT!");
        else
            System.out.println("WRONG! " + input + " IS NOT CORRECT!");    
    }
}
