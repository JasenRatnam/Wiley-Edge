/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

/**
 *
 * @author Jasen
 */
public class ForAndTwentyBlackbirds {
    
    /*
    Change it so that the loop counts to the more traditional twenty-four birds.
    24 in for loop instead of 20
    
    Update the bird number printouts so the count is from 1 - 24. 
    What did you change? (Answer in a comment.)
    Print (i+1)
    */
    
    public static void main(String[] args) {
        int birdsInPie = 0;
        for (int i = 0; i < 24; i++) {
            System.out.println("Blackbird #" + (i+1) + " goes into the pie!");
            birdsInPie++;
        }

        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie full!");
    }
}
