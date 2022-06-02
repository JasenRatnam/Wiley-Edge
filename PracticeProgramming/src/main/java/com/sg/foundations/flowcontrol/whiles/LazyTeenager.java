/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Random;

/**
 *
 * @author Jasen
 */
public class LazyTeenager {
    
    /*
    Write a program named LazyTeenager that simulates a lazy teenager who 
    won't clean their room until their parent tells them repeatedly to do it.

    Write this using a do-while loop that continues to execute until the room is clean.
   
    Every time the loop executes, the parent should tell the teenager to clean their room.
   
    Each time the teenager is told to clean their room, it increases the chance 
    the teenager will clean the room by 10%. So the first time they're told, 
    there is only a 10% chance they'll clean it. The second time, there's a 
    20% chance. Use Random to calculate this "chance" check.
   
    However, by the 7th time, they're going to get grounded and have their 
    Xbox confiscated. (Note: Use a break, not a compound condition, 
    to stop the loop in that case.)
    */
    
    public static void main(String[] args) {
        
        boolean clean = false;
        int counter = 1;
        int chance = 0;
        
        Random randomizer = new Random();        
        int chanceCheck = randomizer.nextInt(101);
        
        do{
            System.out.println(clean + " - " + counter + " - " + chance + " - " + chanceCheck);
            System.out.println("Clean your room!! (x" + counter + ")");
            
            if(counter == 7){
                System.out.println("Clean your room!! That's IT, I'm doing it!!! "
                        + "YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
                clean = true;
            }else if(chance >= chanceCheck){
                System.out.println("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
                clean = true;
            }else{
                counter++;
                chance += 10;
            }
            
        }while(!clean);
        
    }
}
