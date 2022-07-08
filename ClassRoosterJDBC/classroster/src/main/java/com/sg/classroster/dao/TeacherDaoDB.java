
package com.sg.classroster.dao;

import com.sg.classroster.entities.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public class TeacherDaoDB implements TeacherDao{

    @Autowired
    JdbcTemplate jdbc;
    
    /**
     * create our SELECT query string and use it in queryForObject
     * @param id of the one Teacher we are searching for.
     * @return the one Teacher we are searching for.
     */
    @Override
    public Teacher getTeacherById(int id) {
        try {
            final String GET_TEACHER_BY_ID = "SELECT * FROM teacher WHERE id = ?";
            return jdbc.queryForObject(GET_TEACHER_BY_ID, new TeacherMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    /**
     * create our SELECT query and use it in the query method 
     * If no Teachers are found, it will return an empty list.
     * @return a list of all Teachers.
     */
    @Override
    public List<Teacher> getAllTeachers() {
        final String GET_ALL_TEACHERS = "SELECT * FROM teacher";
        return jdbc.query(GET_ALL_TEACHERS, new TeacherMapper());
    }

    /**
     * @Transactional because we are using the LAST_INSERT_ID
     * create our INSERT query and use it with the update method 
     * and the Teacher data
     * 
     * get the ID for the new Teacher using the LAST_INSERT_ID
     * set it in the Teacher
     * 
     * @param teacher object to add to DB
     * @return teacher object with ID set
     */
    @Override
    @Transactional
    public Teacher addTeacher(Teacher teacher) {
        final String INSERT_TEACHER = "INSERT INTO teacher(firstName, lastName, specialty) " +
                "VALUES(?,?,?)";
        
        jdbc.update(INSERT_TEACHER,
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSpecialty());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        teacher.setId(newId);
        return teacher;
    }

    /**
     * create our UPDATE query and use it in the update method
     * @param teacher object with updated information
     */
    @Override
    public void updateTeacher(Teacher teacher) {
        final String UPDATE_TEACHER = "UPDATE teacher SET firstName = ?, lastName = ?, " +
                "specialty = ? WHERE id = ?";
        jdbc.update(UPDATE_TEACHER,
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSpecialty(),
                teacher.getId());
    }

    /**
     * @Transactional because we are running multiple queries that modify 
     * start by deleting the course_student entries associated with any 
     *  Course we will be deleting.
     * delete any Courses associated with the Teacher.
     * delete the Teacher itself.
     * @param id 
     */
    @Override
    @Transactional
    public void deleteTeacherById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE cs.* FROM course_student cs "
                + "JOIN course c ON cs.courseId = c.Id WHERE c.teacherId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_COURSE = "DELETE FROM course WHERE teacherId = ?";
        jdbc.update(DELETE_COURSE, id);
        
        final String DELETE_TEACHER = "DELETE FROM teacher WHERE id = ?";
        jdbc.update(DELETE_TEACHER, id);
    }
    
    //TeacherMapper
    public static final class TeacherMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int index) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("firstName"));
            teacher.setLastName(rs.getString("lastName"));
            teacher.setSpecialty(rs.getString("specialty"));
            
            return teacher;
        }
    }

}
