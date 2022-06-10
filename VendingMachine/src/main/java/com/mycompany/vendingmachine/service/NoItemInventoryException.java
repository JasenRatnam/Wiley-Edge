
package com.mycompany.vendingmachine.service;

/**
 * exception used when there is no inventory of an item
 * @author Jasen Ratnam
 */
public class NoItemInventoryException extends Exception{

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
