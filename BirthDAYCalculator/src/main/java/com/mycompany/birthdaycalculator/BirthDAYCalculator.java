/*
 * Copyright Jasen Ratnam
 */

package com.mycompany.birthdaycalculator;

import static java.lang.Math.abs;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 *
 * @author Jasen Ratnam
 */
public class BirthDAYCalculator {

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
    
    public static void main(String[] args) {
        System.out.println("What is your birthday? (mm-dd-yyyy)");
        String input = getInput();
        
        LocalDate birthDay = LocalDate.parse(input,DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        LocalDate dateNow = LocalDate.now();
        
        LocalDate birthDayNow = birthDay.withYear(dateNow.getYear());
        
        String dayOfWeek = birthDay.getDayOfWeek().toString();
        String dayThisYear = birthDayNow.getDayOfWeek().toString();
        
        System.out.println(birthDayNow);
        
        if (birthDayNow.isBefore(dateNow) || birthDayNow.isEqual(dateNow)) {
            birthDayNow = birthDayNow.plusYears(1);
        }
        
        Period diffDays = Period.between(dateNow, birthDayNow);
        
        Period diffAge = dateNow.until(birthDay);
        int age = abs(diffAge.getYears());
        
        System.out.println("That's means you were born on a " +  dayOfWeek + "!");
        
        System.out.println("This year it falls on a " + dayThisYear +"...");
      
        System.out.println("And since today is " + dateNow + ", there's " + 
                diffDays.getMonths() +" more months and " + diffDays.getDays() + 
                " days until the next one!");

        System.out.println("Bet yer excited to be turning " + age + "!");

    }
}
