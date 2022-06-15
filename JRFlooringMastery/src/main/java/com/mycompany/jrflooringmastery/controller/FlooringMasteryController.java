
package com.mycompany.jrflooringmastery.controller;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.service.FlooringMasteryServiceLayer;
import com.mycompany.jrflooringmastery.service.NoOrderException;
import com.mycompany.jrflooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryController {

    //initialise view
    private FlooringMasteryView view;
    
    //DAO to store the newly created Student
    private FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.view = view;
        this.service = service;
    }
    
    //run program
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //while program is running
        /*
        try{
        */
            while (keepGoing) {

                // print choices and get choice from user
                menuSelection = getMenuSelection();


                    //cases for choice chosen
                    switch (menuSelection) {
                        case 1:
                            displayOrders();
                            break;
                        case 2:
                            addOrder();
                            break;
                        case 3:
                            editOrder();
                            break;
                        case 4:
                            removeOrder();
                            break;
                        case 5:
                            exportData();
                            break;
                        case 6:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                    }
            }
        
          /*  
        } catch(FlooringMasteryPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }*/
        
        
        exitMessage();
    }

    //get selection of the user from the view
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
     
    //Display the unknown command messahe
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    //display exit message
    private void exitMessage() {
        view.displayExitBanner();
    }

    /**
     * Ask the user for a date : Date
     * Display the orders for that date. 
     * If no orders exist for that date, 
     *      Display an error message: NoOrderException 
     * Return the user to the main menu.

     */
    private void displayOrders(){
        view.displayOrdersBanner();
        ArrayList<Order> orders = null;
        try {
            LocalDate date = view.getOrderDate();
            orders = service.getAllOrders(date);
            view.displayOrders(orders);
        } catch (NoOrderException | FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private void addOrder() {
        System.out.println("Add orders");
    }

    private void editOrder() {
        System.out.println("Edit orders");
    }

    private void removeOrder() {
        System.out.println("Remove orders");
    }

    private void exportData() {
        System.out.println("Export Data");
    }
}
