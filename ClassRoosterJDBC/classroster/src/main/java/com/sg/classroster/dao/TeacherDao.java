
package com.sg.classroster.dao;

import com.sg.classroster.entities.Teacher;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface TeacherDao {
    //CRUD methods
    Teacher getTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}
