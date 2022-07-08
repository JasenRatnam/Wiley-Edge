
package com.sg.classroster.controller;

import com.sg.classroster.dao.CourseDao;
import com.sg.classroster.dao.StudentDao;
import com.sg.classroster.dao.TeacherDao;
import com.sg.classroster.entities.Student;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jasen Ratnam
 */
@Controller
public class StudentController {
   @Autowired
   TeacherDao teacherDao;

   @Autowired
   StudentDao studentDao;

   @Autowired
   CourseDao courseDao;
   
   /**
    * display the main Students page
    * GET mapping for students.
    * bring in the Model so we can send data to the page.
    * pick up our list of Students from the DAO 
    * put it into the Model.
    * @param model
    * @return combines the data with the students.html template
    */
   @GetMapping("students")
    public String displayStudents(Model model) {
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
    
    
    /**
     * addStudent POST mapping 
     * bringing the data in directly as parameters of the method.
     * name of the parameter has to match the name in the form exactly
     * create our Student object and save it
     * @param firstName of student 
     * @param lastName of student
     * @return redirect back to the main Students page.
     */
    @PostMapping("addStudent")
    public String addStudent(String firstName, String lastName) {
        //creat student
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        
        //save to DB
        studentDao.addStudent(student);
        
        return "redirect:/students";
    }

    /**
     * using a link to trigger this action
     * pull in the data from the URL using the key, id in this case.
     * Integer can accept a null value, int cannot
     * call our DAO delete method
     * @param id of student to delete
     * @return redirect back to the main Students page.
     */    
    @GetMapping("deleteStudent")
    public String deleteStudent(Integer id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
    
    /**
     * @GetMapping for editStudent.
     * take the ID directly from the URL 
     * bring in a Model so we can send data to the page.
     * @param id to get the Student 
     * @param model
     * @return 
     */
    @GetMapping("editStudent")
    public String editStudent(Integer id, Model model) {
        Student student = studentDao.getStudentById(id);
        //add it to the Model
        model.addAttribute("student", student);
        
        //return to editStudent
        return "editStudent";
    }
    
    /**
     * editStudent POST
     * pull the entire form in as a Student object
     * only works for input fields where the name matches the name 
     * of a field in the Student class. 
     * Any input where the name doesn't match up will have to be pulled in separately
     * call our DAO update method 
     * redirect back to the main Students page.
     * @param student object with updated info, @Valid, should be valid
     * @param result list to hold the results of the validation.
     * @return redirect back to the main Students page.
     */
    @PostMapping("editStudent")
    public String performEditStudent(@Valid Student student, BindingResult result) {
        //takes in the full Student object, 
        //we don't need to create a Validator to validate the data 
        
        //if there is errors
        if(result.hasErrors()) {
            return "editStudent";
        }
        
        studentDao.updateStudent(student);
        return "redirect:/students";
        }

   
   
}
