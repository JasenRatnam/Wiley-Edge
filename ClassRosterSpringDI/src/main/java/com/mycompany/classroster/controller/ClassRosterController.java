
package com.mycompany.classroster.controller;

import com.mycompany.classroster.dao.ClassRosterPersistenceException;
import com.mycompany.classroster.dto.Student;
import com.mycompany.classroster.service.ClassRosterDataValidationException;
import com.mycompany.classroster.service.ClassRosterDuplicateIdException;
import com.mycompany.classroster.service.ClassRosterServiceLayer;
import com.mycompany.classroster.ui.ClassRosterView;
import java.util.List;

/**
 * This is the orchestrator of the application. It knows what needs to be done, 
 * when it needs to be done, and what component can do the job.
 *  
 * Create a method that displays the menu, gets the user's menu choice, 
 * and then calls a method that performs an action based on the user's menu 
 * choice. 
 *  
 * The application should not allow the user to create a student with an ID that 
 * already exists in the system.
 * 
 * The application should not allow the user to create a student that has empty 
 * values for First Name, Last Name, or Cohort.
 * 
 * The application will record an entry to an audit log every time a Student 
 * object is created or removed from the system.
 * @author Jasen
 */
public class ClassRosterController {
    //initialise view
    private ClassRosterView view;
    
    //DAO to store the newly created Student
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }
    
    //run program
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //while program is running
        try{
            while (keepGoing) {

                // print choices and get choice from user
                menuSelection = getMenuSelection();


                    //cases for choice chosen
                    switch (menuSelection) {
                        case 1:
                            listStudents();
                            break;
                        case 2:
                            createStudent();
                            break;
                        case 3:
                            viewStudent();
                            break;
                        case 4:
                            removeStudent();
                            break;
                        case 5:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                    }
            }
        } catch(ClassRosterPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }
    
    //get selection of the user from the view
     private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
     
     /**
      * Display the Create Student banner
        Get all the student data from the user 
        Create the new Student object
        Store the new Student object
        Display the Create Student Success banner   
      */
     private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
     }
     
     /*
        get a list of all Student objects in the system from the DAO 
        hand that list to the view to display to the user
     */
     private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
     
    /**
     * asks the view to display the View Student banner
     * get the student ID from the user. 
     * asks the DAO for the Student associated with the ID. 
     * asks the view to display the Student information. 
     */
     private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
     
     /**
      * ask the view to display the Remove Student banner 
      * ask the user for the ID of the Student to be removed. 
      * ask the DAO to remove the Student 
      * capturing the returned Student, 
      * pass the record to the view to display the results. 
      */
     private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }
     
     //Display the unknown command messahe
     private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

     //display exit message
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
