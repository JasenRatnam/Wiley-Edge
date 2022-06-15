
package com.mycompany.vendingmachine.dto;

/**
 * ENUM with the different types of coins and their values
 * @author Jasen Ratnam
 */
public enum Coin {
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
    
    private final int value;
   
    /**
     * constructor of coin
     * sets the value of coins
     * @param value of the coin
     */
    private Coin(int value){
        this.value = value;
    }
   
    /**
     * get the value of the coin
     * @return the value
     */
    public int getValue(){
        return value;
    }
}
