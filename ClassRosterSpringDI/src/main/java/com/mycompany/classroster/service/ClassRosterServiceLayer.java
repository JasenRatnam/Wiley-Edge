
package com.mycompany.classroster.service;

import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import java.util.List;

/**
 *errors:
 * The Id of the given Student object might already exist
 * First Name, Last Name, or Cohort values might be missing
 * Something might go wrong when the DAO tries to read from or write to the underlying datastore
 * @author Jasen
 */
public interface ClassRosterServiceLayer {
    
    void createStudent(Student student) throws
    ClassRosterDuplicateIdException,
    ClassRosterDataValidationException,
    ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws ClassRosterPersistenceException;
    
    Student getStudent(String studentId) throws ClassRosterPersistenceException;
    
    Student removeStudent(String studentId) throws ClassRosterPersistenceException;  
}
