/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author TheBoss
 */
public class GuessMeMore {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random randomizer = new Random();
        boolean found = false;
        
        int num = randomizer.nextInt(201)-100;
        
        System.out.println("I've chosen a number between -100 and 100. Bet you can't guess it!");
        System.out.println(num);
        while(!found){
            String input = scanner.nextLine();

            System.out.println("Your guess: " + input + "\n");

            int inputInt = Integer.parseInt(input);

            if(inputInt>num){
                System.out.println("Ha, nice try - too High! Try again!");
            }else if(inputInt<num){
                System.out.println("Ha, nice try - too low! Try again!");
            }else if(inputInt == num){
                System.out.println("Wow, nice guess! That was it!");
                found = true;
            }
        }
        
    }
}
