/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author TheBoss
 */
public class HiddenNuts {
    
    public static void main(String[] args) {

        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        int index = squirrel.nextInt(hidingSpots.length);
        System.out.println(index);
        hidingSpots[index] = "Nut";
        System.out.println("The nut has been hidden ...");

        // Nut finding code should go here! 
        
        for (int i = 0; i < hidingSpots.length; i++) {
            if(hidingSpots[i] != null && hidingSpots[i].equals("Nut")){
                System.out.println("Found it! It's in spot# " + i);
            }
        }

    }
    
}
