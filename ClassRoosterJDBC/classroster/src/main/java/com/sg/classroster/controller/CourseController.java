
package com.sg.classroster.controller;

import com.sg.classroster.dao.CourseDao;
import com.sg.classroster.dao.StudentDao;
import com.sg.classroster.dao.TeacherDao;
import com.sg.classroster.entities.Course;
import com.sg.classroster.entities.Student;
import com.sg.classroster.entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *Course manages the relationships with its Teacher and its list of Students
 * @author Jasen Ratnam
 */
@Controller
public class CourseController {
   @Autowired
   TeacherDao teacherDao;

   @Autowired
   StudentDao studentDao;

   @Autowired
   CourseDao courseDao;
   
   /**
    * GET mapping for the Courses page
    * bring in the Model to send data to the page.
    * pull in our list of all Courses and add it to the Model.
    * pull in our list of all Students and Teachers and add them to the Mode
    * 
    * @param model
    * @return courses, which will point us at the courses.html
    */
   @GetMapping("courses")
    public String displayCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "courses";
    }
    
    /**
     * pick up the information from the Add Course form
     * POST mapping for addCourse
     * @param course object to add captures the name and description fields
     * @param request form data capture the teacherId and studentIds.
     * @return redirecting back to the main Courses page.
     */
    @PostMapping("addCourse")
    public String addCourse(Course course, HttpServletRequest request) {
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");
        
        //use the teacherId to get the Teacher out of the Teacher DAO 
        //set too course
        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
        
        //create an empty list of Students, 
        //loop through the studentIds, 
        //retrieve each Student, and add it to the list.
        List<Student> students = new ArrayList<>();
        for(String studentId : studentIds) {
            students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
        }
        
        //set that list into the Course.
        course.setStudents(students);
        
        //put it into the database.
        courseDao.addCourse(course);
        
        //redirecting back to the main Courses page.
        return "redirect:/courses";
    }
    
    /**
     * @GetMapping for courseDetail.
     * 
     * @param id from the URL to get the Course and add it to the Model
     * @param model so we can send data to the page.
     * @return courseDetail, which will send us to the courseDetail.html page.
     */
    @GetMapping("courseDetail")
    public String courseDetail(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "courseDetail";
    }
    
    /**
     * @GetMapping of deleteCourse.
     * take in the ID from the URL.
     * call deleteCourseById in the Course DAO.
     * @param id of course to delete
     * @return redirect back to the main Courses page.
     */
    @GetMapping("deleteCourse")
    public String deleteCourse(Integer id) {
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }
    
    /**
     * GET mapping to display the Edit Course page
     * get our Course 
     * the lists of Students and Teachers 
     * so we can display all of them for the Edit.
     * put those three variables into the Model.
     * @param id
     * @param model
     * @return editCourse, taking us to the editCourse.html 
     */
    @GetMapping("editCourse")
    public String editCourse(Integer id, Model model) {
        Course course = courseDao.getCourseById(id);
        List<Student> students = studentDao.getAllStudents();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("course", course);
        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);
        return "editCourse";
    }
    
    /**
     * @PostMapping for editCourse.
     * actually edit a course
     * 
     * @param course for the simple data (ID, name, description)
     * @param request for the more complicated data (Teacher, Students).
     * @return redirect back to the main Courses page.
     */
    @PostMapping("editCourse")
    public String performEditCourse(@Valid Course course, BindingResult result,
            HttpServletRequest request, Model model) {
        
        //get our teacherId and array of studentIds out of the HttpServletRequest.
        String teacherId = request.getParameter("teacherId");
        String[] studentIds = request.getParameterValues("studentId");
        
        //set the Teacher using the teacherId to retrieve the Teacher from its DAO.
        course.setTeacher(teacherDao.getTeacherById(Integer.parseInt(teacherId)));
        
        //loop through the studentIds 
        //fill a list of Students that we retrieve from the Student DAO.
        List<Student> students = new ArrayList<>();
        if(studentIds != null) {
            for(String studentId : studentIds) {
                students.add(studentDao.getStudentById(Integer.parseInt(studentId)));
            }
        } else {
            FieldError error = new FieldError("course", "students", "Must include one student");
            result.addError(error);
        }
        
        //set our Students with the list we filled in the loop.
        course.setStudents(students);
        
        if(result.hasErrors()) {
            model.addAttribute("teachers", teacherDao.getAllTeachers());
            model.addAttribute("students", studentDao.getAllStudents());
            model.addAttribute("course", course);
            return "editCourse";
        }
        
        //update course
        courseDao.updateCourse(course);
        
        //redirect back to the main Courses page.
        return "redirect:/courses";
    }
    
}
