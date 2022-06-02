/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.methods;

import java.util.Random;

/**
 *
 * @author Jasen
 */
public class BarelyControlledChaos {
    
    /**
    Next, define and implements the methods needed to complete it:
    
    * Write a method that returns a randomly chosen color 
        (have it choose from at LEAST five different colors!)
    * Write a method that returns a randomly chosen animal 
        (have it choose from at LEAST five different animals!)
    * Write another method that returns a random integer chosen from a range 
        (min/max) that can be either of the two numbers or anything between.
     
     */
    
    
    /**
     * Write a method that returns a randomly chosen color 
        (have it choose from at LEAST five different colors!)
     * Red
     * Blue
     * Black
     * White
     * Brown
     * @return chosen colour
     */
    private static String chooseColour(Random randomizer){
     String[] colours = new String[]{"red","blue","black","white","brown"};
     int randomNum = randomizer.nextInt(colours.length);
     
     
     return colours[randomNum];
    }
    
    /**
     * Write a method that returns a randomly chosen animal 
        (have it choose from at LEAST five different animals!)
     * Lion
     * Tiger
     * Shark
     * Snake
     * Bear
     * @return 
     */
    private static String chooseAnimal(Random randomizer){
        String[] animals = new String[]{"lion","tiger","shark","snake","bear"};
        int randomNum = randomizer.nextInt(animals.length);
        
        return animals[randomNum];
    }
    
    /**
     * Write another method that returns a random integer chosen from a range 
        (min/max) that can be either of the two numbers or anything between.
     * @return 
     */
    private static int getRandomInt(int min, int max, Random randomizer){
              
        int randomNum = randomizer.nextInt(max + 1 - min) + min;
        
        return randomNum;
    }
    
    public static void main(String[] args) {
        
        Random randomizer = new Random();        

        String color = chooseColour(randomizer); // call color method here
        String animal = chooseAnimal(randomizer); // call animal method again here
        String colorAgain = chooseColour(randomizer); // call color method again here
        int weight = getRandomInt(5, 200, randomizer); // call number method,
            // with a range between 5 - 200
        int distance = getRandomInt(10,20, randomizer); // call number method,
            // with a range between 10 - 20
        int number = getRandomInt(10000, 20000, randomizer); // call number method,
            // with a range between 10000 - 20000
        int time = getRandomInt(2, 6, randomizer); // call number method,
            // with a range between 2 - 6            

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
            + number + " " + colorAgain + " poppies for nearly "
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
            + "let me tell you!");
    }

}
