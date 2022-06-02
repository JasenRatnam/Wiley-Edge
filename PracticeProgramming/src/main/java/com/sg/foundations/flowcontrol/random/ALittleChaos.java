/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;

import java.util.Random;

/**
 *
 * @author Jasen
 */
public class ALittleChaos {
    public static void main(String[] args) {

        Random randomizer = new Random();

        System.out.println("Random can make integers: " + randomizer.nextInt());
        System.out.println("Or a double: " + randomizer.nextDouble());
        System.out.println("Or even a boolean: " + randomizer.nextBoolean());

        // random number up to 50 generated and 50 is added to it
        // number will be at minimum 50
        int num = randomizer.nextInt(51) + 50;
        
        //random numbers can be used in a math statement by saving it to a variable

        System.out.println("You can store a randomized result: " + num);
        System.out.println("And use it over and over again: " + num + ", " + num);

        System.out.println("Or just keep generating new values");
        System.out.println("Here's a bunch of numbers from 0 - 100: ");

        System.out.print(randomizer.nextInt(51)+50 + ", ");
        System.out.print(randomizer.nextInt(51)+50 + ", ");
        System.out.print(randomizer.nextInt(51)+50 + ", ");
        System.out.print(randomizer.nextInt(51)+50 + ", ");
        System.out.print(randomizer.nextInt(51)+50 + ", ");
        System.out.println(randomizer.nextInt(51)+50);
    }
}
