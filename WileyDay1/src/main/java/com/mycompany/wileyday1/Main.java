/**
 * Day 1 of Wiley training
 */
package com.mycompany.wileyday1;

/**
 *
 * @author Jasen Ratnam
 */
public class Main {
    
    private static void printMultiples(){
        /**
         * write an app to print "foo" for multiples of 3
         * "buz" for multiples of 5
         * "biz" for multiples of 7
         */
        
        for(int i = 1; i<= 50;i++){
            System.out.println(i);
            if(i%3 == 0)
                System.out.print(" foo ");
            if(i%5 == 0)
                System.out.print(" buz ");
            if(i%7 == 0)
                System.out.print(" biz ");
        }
    }
    
    //main method
    public static void main(String[] args) {
        
        printMultiples();
        
    }
    
}
