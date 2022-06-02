/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luckysevens;

import java.util.Random;
import java.util.Scanner;

/**
 * Each round, the program rolls a virtual pair of dice for the user.
 *
 * If the sum of the two dice is equal to 7, the player wins $4; 
 * otherwise, the player loses $1.
 * 
 * This program will be a Java Console Application called LuckySevens .
 * The program first asks the user how many dollars they have to bet.
 * The program then rolls the dice repeatedly until all the money is gone.
 *         Hint: Use a loop construct to keep playing until the money is gone.
 * The program keeps track of how many rolls were taken before the money ran out.
 * The program keeps track of the maximum amount of money held by the player.
 * The program keeps track of how many rolls were taken at the point when the user held the most money.
 *      Hint: For steps 4, 5, and 6, declare some variables.
 * 
 * @author Jasen
 */
public class LuckySeven {
    
    private static double getInputDouble(){
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        double inputDouble = -1;
        
        while(!isValid){            
            String input = myScanner.nextLine();
            
            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputDouble = Double.parseDouble(input);
                    if (inputDouble > 0) 
                        isValid = true;
                    else
                        System.out.println("Please enter a number greater than 0");
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
        }
        return inputDouble;
    }
    
    private static int rollDice(){
        Random randomizer = new Random();
        return randomizer.nextInt(6)+1;
    }
    
    public static void main(String[] args) {
        int numberRolls = 0;
        int quitRollNum = 0;
        double quitMoney = 0.0;
        double money = 0.0;
        double startMoney = 0.0;
        
        System.out.println("How many dollars do you have?");
        startMoney = getInputDouble();
        
        money = startMoney;
        quitMoney = startMoney;
        
        while(money > 0){
            int dice1 = rollDice();
            int dice2 = rollDice();
            
            if((dice1 + dice2) == 7){
                money += 4;
                if(quitMoney< money){
                    quitRollNum = numberRolls+1;
                    quitMoney = money;
                }
            }
            else
                money -= 1;
            numberRolls++;
            //System.out.println(money + "$ Dice: " + (dice1+dice2));
        }
        
        
        System.out.println("You are broke after " + numberRolls + " rolls.");
        System.out.println("You should have quit after " + quitRollNum + 
                " rolls when you had $" + quitMoney + ".");
        

    }
    
}
