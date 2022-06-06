
package com.mycompany.classroster.dao;

import com.mycompany.classroster.dto.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the text file-specific implementation of the ClassRosterDao interface.
 * @author Jasen
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao{
    
    // data structure to save students
    private Map<String, Student> students = new HashMap<>();
    
    //add a student to the map with student id as key
    @Override
    public Student addStudent(String studentId, Student student) {
        Student prevStudent = students.put(studentId, student);
        return prevStudent;
    }

    /**
     * Gets all of the Student objects out of the students map as a list 
     */
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

    //get a student with matching student id
    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }
    
    // remove student with matching student id
    @Override
    public Student removeStudent(String studentId) {
        Student removedStudent = students.remove(studentId);
        return removedStudent;
    }
}
