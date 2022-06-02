/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

import java.util.Scanner;

/**
 *
 * @author TheBoss
 */
public class TraditionalFizzBuzz {
    
    /*
    Ask the user for a number.
    
    Use a for loop to count from zero, replacing every multiple of 3 with "fizz"
    and every multiple of 5 with "buzz". Multiples of BOTH should print out "fizz buzz".
    
    Every time you print out fizz, buzz, or fizz buzz - keep track. 
    When you've reached the number received from the user - stop!
    
    Finish it all up with a large, all caps printout of "TRADITION!!!!!"
    */
    
     public static void main(String[] args) {
         
        Scanner scanner = new Scanner(System.in);
       
        System.out.println("How many units of fizzing and buzzing do you need in your life?");
        String input = scanner.nextLine();
        int unitsNum = Integer.parseInt(input);
        int j = 0;
        
        System.out.println(0);
        for(int i =1; i<20;i++){
            
            if(i%3 == 0 && i%5 == 0){
                System.out.println("fizz buzz");
                j++;
            }
            else if(i%5 == 0){
                System.out.println("buzz ");
                j++;
            }
            else if(i%3 == 0){
                System.out.println("fizz ");
                j++;
            }else{
                System.out.println(i);
            }
            
            if(j==unitsNum)
                break;
        }
        
         System.out.println("TRADITION!!!!!");
     }
    
}
