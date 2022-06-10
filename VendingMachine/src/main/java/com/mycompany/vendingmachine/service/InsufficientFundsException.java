
package com.mycompany.vendingmachine.service;

/**
 * exception used when there is insufficient funds to buy an item
 * @author Jasen Ratnam
 */
public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
