
package com.mycompany.daysuntilfriday;

import java.util.Scanner;

/**
 *
 * @author Jasen Ratnam
 */
public class App {

    private static int calculateDays(DaysEnum daysEnum) {

        switch(daysEnum) {
            case MONDAY:
                return 4;
            case TUESDAY:
                return 3;
            case WEDNESDAY:
                return 2;
            case THURSDAY:
                return 1;
            case FRIDAY:
                return 0;
            case SATURDAY:
                return 6;
            case SUNDAY:
                return 5;    
            default:
                return -1;
        }
    }
    
    private static String getInput(){
        
        Scanner myScanner = new Scanner(System.in);
                
        boolean isValid = false;
        String input = null;

        while(!isValid){
            input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                isValid = true;
            }
        }
        return input;
    }
    
    public static void main(String[] args){
        System.out.println("Enter the day: ");
        String input = getInput();
        DaysEnum daysEnum = null;
        try{
            daysEnum = DaysEnum.valueOf(input);
            int daysLeft = calculateDays(daysEnum);
        
            System.out.println(daysLeft + " days left before Friday");
        } catch(Exception e){
            System.out.println("Wrong input!");
        }
    }
    
    

}
