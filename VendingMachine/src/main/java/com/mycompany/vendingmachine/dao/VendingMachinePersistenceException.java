
package com.mycompany.vendingmachine.dao;

/**
 * exception for errors happening in the vending machine
 * @author Jasen Ratnam
 */
public class VendingMachinePersistenceException extends Exception{

    public VendingMachinePersistenceException(String message) {
        super(message);
    }

    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
