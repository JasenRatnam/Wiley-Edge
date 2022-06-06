
package com.mycompany.classroster.dto;

/**
 * This is the DTO that holds all the Student info.
 * @author Jasen
 */
public class Student {
    private String firstName;
    private String lastName;
    private String studentId;   //read only
    
    // Programming Language + cohort month/year
    private String cohort;

    //constructor
    public Student(String studentId) {
        this.studentId = studentId;
    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }   
}
