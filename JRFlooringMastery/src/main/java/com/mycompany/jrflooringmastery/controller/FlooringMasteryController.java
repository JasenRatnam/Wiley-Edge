
package com.mycompany.jrflooringmastery.controller;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import com.mycompany.jrflooringmastery.service.FlooringMasteryDataValidationException;
import com.mycompany.jrflooringmastery.service.FlooringMasteryServiceLayer;
import com.mycompany.jrflooringmastery.service.NoOrderException;
import com.mycompany.jrflooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.ArrayList;

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
        } catch (FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    /**
     * add an order will query the user for each piece of order data necessary:
     * The remaining fields are calculated from the user entry
     * prompt the user as to whether they want to place the order (Y/N)
     * generate an order number for the user based on the next available order #
     */
    private void addOrder() {
        view.displayAddOrderBanner();
        try {
            ArrayList<Taxes> taxes = service.getTaxes();
            ArrayList<Product> products = service.getProducts();
            Order newOrder = view.getNewOrderInformation(taxes, products);
            boolean confirmation = view.confirmOrder(newOrder);
            if(confirmation)
                service.createOrder(newOrder);
        } catch (FlooringMasteryDataValidationException | FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        } 
    }

    private void editOrder() {
        view.displayEditOrderBanner();
        try{
            LocalDate date = view.getOrderDate();
            int orderNumber = view.getOrderNumber();
            ArrayList<Taxes> taxes = service.getTaxes();
            ArrayList<Product> products = service.getProducts();
            
            Order order = service.getOrder(date, orderNumber);
            Order updatedOrder = view.getUpdatedOrder(order, taxes, products);
            Boolean confirmation = view.confirmOrder(updatedOrder);
        
            if(confirmation)
                service.updateOrder(date, updatedOrder);
        }catch (FlooringMasteryDataValidationException | NoOrderException | FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        } 
    }

    private void removeOrder() {
        try {
            view.displayRemoveOrderBanner();
            
            LocalDate date = view.getOrderDate();
            int orderNumber = view.getOrderNumber();
            
            Order order = service.getOrder(date, orderNumber);
            Boolean confirmation = view.confirmOrder(order);
            
            if(confirmation)
                service.removeOrder(date, order);
        } catch (FlooringMasteryDataValidationException | NoOrderException | FlooringMasteryPersistenceException ex) {
             view.displayErrorMessage(ex.getMessage());
        } 
        
    }

    private void exportData() {
        try {
            view.displayExportOrdersBanner();
            service.exportData();
            view.displayExportOrderSuccessBanner();
        } catch (FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
        
    }
}
