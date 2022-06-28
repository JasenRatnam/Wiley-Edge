/*
 * Copyright Jasen Ratnam
 */

package com.mycompany.guessnumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application starting program
 * REST server to facilitate playing a number guessing game known as "Bulls and Cows".
 * 4-digit number is generated where every digit is different. 
 * For each round, the user guesses a number 
 * User is told the exact and partial digit matches.
 * 
 * An exact match occurs when the user guesses the correct digit in the correct position.
 * A partial match occurs when the user guesses the correct digit but in the wrong position.
 * 
 * Once the number is guessed (exact matches for all digits) the user wins the game.
 * @author Jasen Ratnam
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
