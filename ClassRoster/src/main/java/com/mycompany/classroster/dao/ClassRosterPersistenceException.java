
package com.mycompany.classroster.dao;

/**
 *
 * @author Jasen
 */
public class ClassRosterPersistenceException extends Exception{
    public ClassRosterPersistenceException(String message) {
        super(message);
    }
    
    public ClassRosterPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
