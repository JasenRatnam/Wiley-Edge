
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * handle the change in the machine
 * @author Jasen Ratnam
 */
public class Change {
    //variables
    private int penny;
    private int nickle;
    private int dime;
    private int quarter;
    private BigDecimal changeToGiveInDollar;

    /**
     * constructor using the number of all the coins
     * @param penny
     * @param nickle
     * @param dime
     * @param quarter
     * @param dollar 
     */
    public Change(int penny, int nickle, int dime, int quarter, int dollar) {
        this.penny = penny;
        this.nickle = nickle;
        this.dime = dime;
        this.quarter = quarter;
    }

    /**
     * constructor using a BigDecimal to get the change for that number
     * @param changeCents value to make changes 
     */
    public Change(BigDecimal changeCents) {
        setChangeToGive(changeCents);
        setChangeCoins();
    }
    
    //getters
    public int getPenny() {
        return penny;
    }
    
    public int getNickle() {
        return nickle;
    }
    
    public int getDime() {
        return dime;
    }
    
    public int getQuarter() {
        return quarter;
    }
    
    private BigDecimal getChangeToGive() {
        return changeToGiveInDollar;
    }
    
    //setters
    private void setChangeToGive(BigDecimal changeToGive) {
        this.changeToGiveInDollar = changeToGive;
    }

    private void setPenny(int penny) {
        this.penny = penny;
    }

    private void setNickle(int nickle) {
        this.nickle = nickle;
    }

    private void setDime(int dime) {
        this.dime = dime;
    }

    private void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    /**
     * set the change needed for the BigDecimal value
     * get the number in cents
     * get the coins needed for the number
     * use the COIN ENUM for the value of coins
     */
    private void setChangeCoins() {        
        BigDecimal rounded = getChangeToGive().setScale(2, RoundingMode.HALF_UP);
        BigDecimal bigDecimalInCents = rounded.movePointRight(2);
        int cents = bigDecimalInCents.intValueExact();

        setQuarter(Math.round(cents/Coin.QUARTER.getValue()));
        cents=cents%Coin.QUARTER.getValue();
        setDime(Math.round(cents/Coin.DIME.getValue()));
        cents=cents%Coin.DIME.getValue();
        setNickle(Math.round(cents/Coin.NICKLE.getValue()));
        cents=cents%Coin.NICKLE.getValue();
        setPenny(Math.round(cents/Coin.PENNY.getValue()));   
    }

    /**
     * get string with the number of different coins used
     * @return 
     */
    @Override
    public String toString() {
        return "Change:\n" + "Penny=" + getPenny() 
                         + "\nNickle=" + getNickle() 
                         + "\nDime=" + getDime() 
                         + "\nQuarter=" + getQuarter() 
                         + "\nTotal= $" + changeToGiveInDollar ;
    }
}
