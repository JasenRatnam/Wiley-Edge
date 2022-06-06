
package com.mycompany.classroster.controller;

import com.mycompany.classroster.dao.ClassRosterDaoFileImpl;
import com.mycompany.classroster.dto.Student;
import com.mycompany.classroster.ui.ClassRosterView;
import com.mycompany.classroster.ui.UserIO;
import com.mycompany.classroster.ui.UserIOConsoleImpl;
import java.util.List;

/**
 * This is the orchestrator of the application. It knows what needs to be done, 
 * when it needs to be done, and what component can do the job.
 *  
 * Create a method that displays the menu, gets the user's menu choice, 
 * and then calls a method that performs an action based on the user's menu 
 * choice. 
 * @author Jasen
 */
public class ClassRosterController {
    //initialise view
    private ClassRosterView view = new ClassRosterView();
    //initialise UI
    private UserIO io = new UserIOConsoleImpl();
    //DAO to store the newly created Student
    private ClassRosterDaoFileImpl dao = new ClassRosterDaoFileImpl();

    //run program
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //while program is running
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
     private void createStudent() {
        view.displayCreateStudentBanner();
        //create student from view
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
     }
     
     /*
        get a list of all Student objects in the system from the DAO 
        hand that list to the view to display to the user
     */
     private void listStudents() {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
     
    /**
     * asks the view to display the View Student banner
     * get the student ID from the user. 
     * asks the DAO for the Student associated with the ID. 
     * asks the view to display the Student information. 
     */
     private void viewStudent() {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
     
     /**
      * ask the view to display the Remove Student banner 
      * ask the user for the ID of the Student to be removed. 
      * ask the DAO to remove the Student 
      * capturing the returned Student, 
      * pass the record to the view to display the results. 
      */
     private void removeStudent() {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
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
