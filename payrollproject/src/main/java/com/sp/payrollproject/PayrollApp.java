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
public class PayrollApp {
    public static void main(String[] args) {
        Address a = new Address("Quohog", "123 main", "12345");
        Person p = new Person("Stewie", 2, a);
        
        Employee e = new Employee(20000, "Peter", 44, a);
        
        System.out.println(e);
    }
}
