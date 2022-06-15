
package com.mycompany.jrflooringmastery.service;

/**
 *
 * @author Jasen Ratnam
 */
public class NoOrderException extends Exception {

    public NoOrderException(String message) {
        super(message);
    }

    public NoOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
