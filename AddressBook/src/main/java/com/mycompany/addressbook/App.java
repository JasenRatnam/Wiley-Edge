/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.addressbook;

import com.mycompany.addressbook.controller.AddressBookController;

/**
 *  Allow the user to add addresses to the address book.
    Allow the user to remove addresses from the address book.
    Allow the user to see how many addresses are in the book.
    Allow the user to list all the addresses in the book.
    Allow the user to find an address by last name.
 * @author Jasen
 */
public class App {
    
    public static void main(String[] args) {
       AddressBookController controller = new AddressBookController();
       controller.run();
    }
}
