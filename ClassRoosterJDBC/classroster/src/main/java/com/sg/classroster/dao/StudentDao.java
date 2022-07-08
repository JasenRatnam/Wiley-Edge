
package com.sg.classroster.dao;

import com.sg.classroster.entities.Student;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface StudentDao {
    //CRUD methods
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
}
