
package com.mycompany.classroster.service;

/**
 *
 * @author Jasen
 */
public class ClassRosterDuplicateIdException extends Exception{
    public ClassRosterDuplicateIdException(String message) {
        super(message);
    }

    public ClassRosterDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
