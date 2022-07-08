
package com.sg.classroster.dao;

import com.sg.classroster.entities.Student;
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
public class StudentDaoDB implements StudentDao{
   @Autowired
   JdbcTemplate jdbc;

   /**
    * create our SELECT query string and use it in queryForObject 
    * surround our code with a try-catch that will catch the exception thrown 
    * when there is no Student with that ID,
    * @param id of student wanted
    * @return student searched for
    */
    @Override
    public Student getStudentById(int id) {
        try {
            final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
            return jdbc.queryForObject(SELECT_STUDENT_BY_ID, new StudentMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    /**
     * create our SELECT query and use it in the query method to 
     * return a list of all Students.
     * If no Students are found, it will return an empty list.
     * @return list of students
     */
    @Override
    public List<Student> getAllStudents() {
        final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
        return jdbc.query(SELECT_ALL_STUDENTS, new StudentMapper());
    }

    /**
     * @Transactional because we are using the LAST_INSERT_ID
     * create our INSERT query and use it with the update method
     * get the ID for our new Student using the LAST_INSERT_ID
     * set it in the Student before returning it.
     * @param student to add to DB
     * @return Student with ID addedd
     */
    @Override
    @Transactional
    public Student addStudent(Student student) {
        final String INSERT_STUDENT = "INSERT INTO student(firstName, lastName) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_STUDENT,
                student.getFirstName(),
                student.getLastName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        student.setId(newId);
        return student;
    }

    /**
     * create the UPDATE query and use it in the update method
     * @param student object with updated information
     */
    @Override
    public void updateStudent(Student student) {
        final String UPDATE_STUDENT = "UPDATE student SET firstName = ?, lastName = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_STUDENT,
                student.getFirstName(),
                student.getLastName(),
                student.getId());
    }

    /**
     * @Transactional because we are running multiple queries
     * start by deleting the course_student entries associated with the Student.
     * deleting the Student itself.
     * @param id 
     */
    @Override
    @Transactional
    public void deleteStudentById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE studentId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
        jdbc.update(DELETE_STUDENT, id);
    }
    
    //StudentMapper 
    public static final class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));

            return student;
        }
    }

}
