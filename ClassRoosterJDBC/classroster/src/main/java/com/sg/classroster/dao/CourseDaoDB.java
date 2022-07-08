
package com.sg.classroster.dao;

import com.sg.classroster.dao.StudentDaoDB.StudentMapper;
import com.sg.classroster.dao.TeacherDaoDB.TeacherMapper;
import com.sg.classroster.entities.Course;
import com.sg.classroster.entities.Student;
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
 * handles the relationships in the program and has a few additional public methods.
 * @author Jasen Ratnam
 */
@Repository
public class CourseDaoDB implements CourseDao{

    @Autowired
   JdbcTemplate jdbc;
    
    /**
     *  try-catch in case the Course does not exist.
     * SELECT query to get the basic Course object.
     * getTeacherForCourse method to get teacher
     * getStudentsForCourse method to get a list of Student objects for this Course.
     * @param id of course wanted
     * @return course found, null if not found
     */
    @Override
    public Course getCourseById(int id) {
        try {
            final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
            Course course = jdbc.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
            course.setTeacher(getTeacherForCourse(id));
            course.setStudents(getStudentsForCourse(id));
            return course;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    /**
     * SELECT query and using it to get the list of Courses.
     *  pass the list of Courses into associateTeacherAndStudents
     *      fill in the data for each Course.
     * @return 
     */
    @Override
    public List<Course> getAllCourses() {
        final String SELECT_ALL_COURSES = "SELECT * FROM course";
        List<Course> courses = jdbc.query(SELECT_ALL_COURSES, new CourseMapper());
        associateTeacherAndStudents(courses);
        return courses;
    }

    /**
     * @Transactional so we can retrieve the new ID.
     * write our INSERT query and use it to insert the basic Course information 
     * get a new ID and add it to the Course object.
     * call our insertCourseStudent helper method,
     *  to insert students to course
     * @param course to add to DB
     * @return course object with ID
     */
    @Override
    @Transactional
    public Course addCourse(Course course) {
        final String INSERT_COURSE = "INSERT INTO course(name, description, teacherId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_COURSE,
                course.getName(),
                course.getDescription(),
                course.getTeacher().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        course.setId(newId);
        insertCourseStudent(course);
        return course;
    }

    /**
     * @Transactional here because we are making multiple database-modifying queries in the method.
     * write our UPDATE query and use it in the update method
     * handle the Students by first deleting all the course_student entries 
     * adding them back in with the call to insertCourseStudent.
     * 
     * @param course object with updated information
     */
    @Override
    @Transactional
    public void updateCourse(Course course) {
        final String UPDATE_COURSE = "UPDATE course SET name = ?, description = ?, "
                + "teacherId = ? WHERE id = ?";
        jdbc.update(UPDATE_COURSE, 
                course.getName(), 
                course.getDescription(), 
                course.getTeacher().getId(),
                course.getId());
        
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, course.getId());
        insertCourseStudent(course);
    }

    /**
     * @Transactional because of the multiple database-modifying queries.
     * get rid of the course_student entries that reference our Course.
     * delete the course itself.
     * @param id 
     */
    @Override
    @Transactional
    public void deleteCourseById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE FROM course_student WHERE courseId = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_COURSE = "DELETE FROM course WHERE id = ?";
        jdbc.update(DELETE_COURSE, id);
    }

    /**
     * query is limited by the teacherId
     * call the associateTeacherAndStudent method again.
     * @param teacher of which we want courses of
     * @return list of courses done by prof
     */
    @Override
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        final String SELECT_COURSES_FOR_TEACHER = "SELECT * FROM course WHERE teacherId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_TEACHER, 
                new CourseMapper(), teacher.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }

    /**
     * JOIN with course_student so we can limit the query based on the studentId.
     * use associateTeacherAndStudent to fill in the rest of the data.
     * @param student of which we want course
     * @return list of courses taken by student
     */
    @Override
    public List<Course> getCoursesForStudent(Student student) {
        final String SELECT_COURSES_FOR_STUDENT = "SELECT c.* FROM course c JOIN "
                + "course_student cs ON cs.courseId = c.Id WHERE cs.studentId = ?";
        List<Course> courses = jdbc.query(SELECT_COURSES_FOR_STUDENT, 
                new CourseMapper(), student.getId());
        associateTeacherAndStudents(courses);
        return courses;
    }

    /**
     * we JOIN from Teacher to Course to get a Teacher object.
     * @param id of course teached by teacher
     * @return teacher of course
     */
    private Teacher getTeacherForCourse(int id) {
        final String SELECT_TEACHER_FOR_COURSE = "SELECT t.* FROM teacher t "
                + "JOIN course c ON c.teacherId = t.id WHERE c.id = ?";
        return jdbc.queryForObject(SELECT_TEACHER_FOR_COURSE, new TeacherMapper(), id);
    }

    /**
     * We JOIN from Student to course_student 
     * @param id of course that students are enrolled in 
     * @return list of students objects for the given course
     */
    private List<Student> getStudentsForCourse(int id) {
        final String SELECT_STUDENTS_FOR_COURSE = "SELECT s.* FROM student s "
                + "JOIN course_student cs ON cs.studentId = s.id WHERE cs.courseId = ?";
        return jdbc.query(SELECT_STUDENTS_FOR_COURSE, new StudentMapper(), id);
    }

    /**
     * loop through the list and call our existing Teacher and Students methods 
     * to fill in the data for each Course.
     * @param courses 
     */
    private void associateTeacherAndStudents(List<Course> courses) {
        for (Course course : courses) {
            course.setTeacher(getTeacherForCourse(course.getId()));
            course.setStudents(getStudentsForCourse(course.getId()));
        }
    }

    /**
     * loops through the list of Students in the Course 
     * adds database entries to course_student for each.
     * @param course objects with students taking the course
     */
    private void insertCourseStudent(Course course) {
        final String INSERT_COURSE_STUDENT = "INSERT INTO "
                + "course_student(courseId, studentId) VALUES(?,?)";
        for(Student student : course.getStudents()) {
            jdbc.update(INSERT_COURSE_STUDENT, 
                    course.getId(),
                    student.getId());
        }
    }
    
    //coursemapper
    public static final class CourseMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            return course;
        }
    }

}
