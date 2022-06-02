/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sp.payrollproject;

import java.util.Arrays;

/**
 *
 * @author SPalm
 */
public class PersonelTest {
    private Person[] people = new Person[3];
    private int numOfPersons=0;
    
    public void addPerson(Person p){
        if(numOfPersons <= people.length){
            people[numOfPersons++] = p;
        }
    }
    
    public void printPerson(){
        for(Person e : people){
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        Address a = new Address("Quohog", "123 main", "12345");
        Person p = new Person("Stewie", 2, a);
        
        Employee e = new Employee(20000, "Peter", 44, a);
        Contractor c = new Contractor(true, 10.00, "Lois", 40, a);
        
        PersonelTest pt = new PersonelTest();
        pt.addPerson(p);
        pt.addPerson(e);
        pt.addPerson(c);
        
        Arrays.sort(pt.people);
        
        pt.printPerson();
        
    }
    
}
