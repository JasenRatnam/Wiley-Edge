/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

/**
 *
 * @author Jasen
 */
public class MiniZork {
    
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        boolean flag = true;

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.println("There is a old red tractor here.");
        System.out.println("Go to the house, go to the old red tractor or open the mailbox? ");

        String action = userInput.nextLine();

        while(flag){
            if (action.equals("open the mailbox")) {
                System.out.println("You open the mailbox.");
                System.out.println("It's really dark in there.");
                System.out.println("Look inside or stick your hand in? ");
                action = userInput.nextLine();

                if (action.equals("look inside")) {
                    System.out.println("You peer inside the mailbox.");
                    System.out.println("It's really very dark. So ... so very dark.");
                    System.out.println("Run away, go to the house or keep looking? ");
                    action = userInput.nextLine();

                    if (action.equals("keep looking")) {
                        System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                        System.out.println("You've been eaten by a grue.");
                        flag = false;
                    } 
                    else if (action.equals("run away")) {
                        System.out.println("You run away screaming across the fields - looking very foolish.");
                        System.out.println("But you alive. Possibly a wise choice.");
                        flag = false;
                    }
                } 
                else if (action.equals("stick your hand in")) {
                    System.out.println("Turns out, there is a venomous snake in the mailbox.");
                    System.out.println("You've been biten and are slowly dying.");
                    flag = false;
                }
            } 

            if (action.equals("go to the house")) { 
                System.out.println("You go to the house.");
                System.out.println("The back door is open.");
                System.out.println("Go inside or look through the windows? ");
                action = userInput.nextLine();

                if (action.equals("go inside")) {
                    System.out.println("You go inside the house.");
                    System.out.println("You see a figure moving.");
                    System.out.println("Run to the tractor or go towards the figure?");
                    action = userInput.nextLine();
                    if (action.equals("run to the tractor")) {
                        action = "go to the old red tractor";
                    } 
                    else if(action.equals("go towards the figure")){
                        System.out.println("Turns out, the house is occupied by wolfs.");
                        System.out.println("You've been eaten by wolfs.");
                        flag = false;
                    }
                } 
                else if (action.equals("look through the windows")) {
                    System.out.println("The windows where rigged to a bomb");
                    System.out.println("You've been exploded to pink mist");
                    flag = false;
                }
            }

            if (action.equals("go to the old red tractor")) { 
                System.out.println("You go to the old red tractor.");
                System.out.println("The tractor looks in good condition.");
                System.out.println("The keys are in the ignition barrel");
                System.out.println("Start the tractor or push it down the field? ");
                action = userInput.nextLine();

                if (action.equals("start the tractor")) {
                    System.out.println("The tractor starts.");
                    System.out.println("You put the tractor in drive");
                    System.out.println("Go to the house or drive away? ");
                    if(action.equals("drive away")){    

                        System.out.println("You have left the field and succesfully got away from danger");
                        System.out.println("Congratulations, you have won.");
                        flag = false;
                    }
                } 
                else if (action.equals("push it down the field")) {
                     System.out.println("You push it but your foot got stuck and the tractor run over your body.");
                     System.out.println("You have been flattened to death.");
                     flag = false;
                }

            }
        }
    }
}
