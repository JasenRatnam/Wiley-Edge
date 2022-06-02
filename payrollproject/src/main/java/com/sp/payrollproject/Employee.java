/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sp.payrollproject;

/**
 *
 * @author SPalm
 */
public class Employee extends Person implements Payable{
    private double salary;

    public Employee(double salary, String name, int age, Address address) {
        super(name, age, address); // calls Person constructor
        this.salary = salary;
    }
    
    public Employee(double salary){
        this(salary,"", 0, new Address("","",""));
        //this.salary=salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "salary=" + salary + '}' + super.toString();
    }

    @Override
    public double calculateWeeklyPay() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return getSalary()/52;
    }
    
    
    
}
