
package com.sg.classroster.controller;

import com.sg.classroster.dao.CourseDao;
import com.sg.classroster.dao.StudentDao;
import com.sg.classroster.dao.TeacherDao;
import com.sg.classroster.entities.Teacher;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jasen Ratnam
 */
@Controller
public class TeacherController {
   
   //hold the set of ConstraintViolations from our Validator 
   Set<ConstraintViolation<Teacher>> violations = new HashSet<>();

   @Autowired
   TeacherDao teacherDao;

   @Autowired
   StudentDao studentDao;

   @Autowired
   CourseDao courseDao;
    
   /**
    * GET mapping
    * display the list of Teachers in our system
    * retrieve the list of Teachers from the DAO and add it to the Model.
    * need a teachers.html file to push our data to.
    * @param model to send data out to a page.
    * @return teachers
    */
   @GetMapping("teachers")
    public String displayTeachers(Model model) {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("teachers", teachers);
        
        //violations variable is added to the Model for the main Teachers page
        model.addAttribute("errors", violations);
        return "teachers";
    }
    
    /**
     * addTeacher POST mapping
     * create the Teacher object and fill it with the data.
     * use our teacherDao to save it to the database.
     * @param request HttpServletRequest to retrieve the fields from the form
     * @return redirect our browser back to the Teachers page.
     */
    @PostMapping("addTeacher")
    public String addTeacher(HttpServletRequest request) {
        //retrieve the fields from the form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");
        
        //create the Teacher object and fill it with the data.
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setSpecialty(specialty);
        
        //Use our teacherDao to save it to the database.
        //instantiate our Validator object.
        //pass the full Teacher object into the Validator 
        //save the results in a “violations” class variable.
        //check if we found any validation errors; 
        //  if not, we add the Teacher.
        
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(teacher);
        
        
        if(violations.isEmpty()) {
            teacherDao.addTeacher(teacher);
        }        
        //redirect our browser back to the Teachers page.
        return "redirect:/teachers";
    }
    
    /**
     * @GetMapping for deleteTeacher.
     * pull in the ID from the URL using HttpServletRequest 
     * use the DAO to delete the Teacher.
     * @param request link with delete info
     * @return redirect the browser back to the main Teachers page.
     */
    @GetMapping("deleteTeacher")
    public String deleteTeacher(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        teacherDao.deleteTeacherById(id);
        
        return "redirect:/teachers";
    }
    
    /**
     * GET mapping to display our Edit Teacher page
     * @param request get the ID
     * @param model to send the Teacher object we are editing to the page.
     * @return 
     */
    @GetMapping("editTeacher")
    public String editTeacher(HttpServletRequest request, Model model) {
        //retrieve our ID from the HttpServletRequest, 
        int id = Integer.parseInt(request.getParameter("id"));
        // retrieve the Teacher for that ID
        Teacher teacher = teacherDao.getTeacherById(id);
        
        //put it into the Model.
        model.addAttribute("teacher", teacher);
        
        //point us to an editTeacher.html page.
        return "editTeacher";
    }
    
    /**
     * Post method to perform edit
     * @param request form information
     * @return redirect to teachers page
     */
    @PostMapping("editTeacher")
    public String performEditTeacher(HttpServletRequest request) {
        //getting the hidden ID and pulling in the original version of the object.
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDao.getTeacherById(id);
        
        
        //get all the new data out of the form 
        //set it into the Teacher object.
        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setSpecialty(request.getParameter("specialty"));
        
        //call to our DAO update method.
        teacherDao.updateTeacher(teacher);
        
        //redirect back to the main Teachers page.
        return "redirect:/teachers";
    }
    

   
}
