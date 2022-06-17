
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
 * controller of application
 * @author Jasen Ratnam
 */
public class FlooringMasteryController {

    //initialise view
    private FlooringMasteryView view;
    
    //DAO to store the newly created Student
    private FlooringMasteryServiceLayer service;

    /**
     * constructor of controller
     * @param service of the program
     * @param view  of the program
     */
    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.view = view;
        this.service = service;
    }
    
    /**
     * method to run the application
     * Shows main menu and get users choice
     * goes to the corresponding method to the users choice
     * exit gracefully
     * catch and handle invalid inputs
     */
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

    /**
     * get selection of the user from the view
    **/
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
     
    /**
     * Display the unknown command message
    **/ 
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * display exit message
    **/
    private void exitMessage() {
        view.displayExitBanner();
    }

    /**
     * Ask the user for a date : Date
     * Display the orders for that date. 
     * If no orders exist for that date, 
     *      Display an error message from FlooringMasteryPersistenceException 
     * Return the user to the main menu.
     */
    private void displayOrders(){
        view.displayOrdersBanner();
        
        ArrayList<Order> orders = null;
        try {
            //get date of orders
            LocalDate date = view.getOrderDate();
            //get orders and display them
            orders = service.getAllOrders(date);
            view.displayOrders(orders);
        } catch (NoOrderException | FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        } 
    }

    /**
     * Add an order 
     * Query the user for each piece of order data necessary
     * The remaining fields are calculated from the user entry
     * Prompt the user as to whether they want to place the order (Y/N)
     * Y 
     *      Generate an order number for the user based on the next available order #
     *      Save to file
     * N
     *      Discard Order
     *      return to main menu
     * 
     * Taxes and products are loaded from file
     * display products in file to choose from
     * State given by user is compared to states in taxes file to ensure we can sell to state
     * Catch Exceptions if the order is not in a valid form
     * Catch Exceptions if there is errors reading and writing to files
     */
    private void addOrder() {
        view.displayAddOrderBanner();
        try {
            //get tax and product
            ArrayList<Taxes> taxes = service.getTaxes();
            ArrayList<Product> products = service.getProducts();
            
            //get new order created by user
            Order newOrder = view.getNewOrderInformation(taxes, products);
            //confirm order
            boolean confirmation = view.confirmOrder(newOrder);
            if(confirmation)
                //get order number and save to file
                service.createOrder(newOrder);
        } catch (FlooringMasteryDataValidationException | FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        } 
    }

    /**
     * Edit an order
     * Ask the user for a date and order number. 
     * If the order exists for that date, 
     *     Ask the user for each piece of order data and display the existing data. 
     *     Replace that data if changed
     *     if the user hits Enter without entering data, leave the existing data in place
     * Data allowed to be changed
     *      CustomerName
     *      State
     *      ProductType
     *      Area
     * If state, product type, or area are changed, 
     *      recalculate order
     * Display a summary of the new order information 
     * prompt for confirmation
     * If yes, replace the data in the file then return to the main menu. 
     * If no, do not save and return to the main menu.
     * catch if order information is not valid
     * catch if no order exist for date and number
     * catch for errors reading and writing file
     */
    private void editOrder() {
        view.displayEditOrderBanner();
        try{
            //get order data and number
            LocalDate date = view.getOrderDate();
            int orderNumber = view.getOrderNumber();
            
            //get taxes and products to display and to verify user input
            ArrayList<Taxes> taxes = service.getTaxes();
            ArrayList<Product> products = service.getProducts();
            
            //get the order corresponding to ordernumber
            Order order = service.getOrder(date, orderNumber);
            
            //get user input and update order
            Order updatedOrder = view.getUpdatedOrder(order, taxes, products);
            //confirm update
            Boolean confirmation = view.confirmOrder(updatedOrder);
        
            if(confirmation)
                //if confirmed update order file with new order
                service.updateOrder(date, updatedOrder);
        }catch (FlooringMasteryDataValidationException | NoOrderException | FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        } 
    }

    /**
     * Removing an order, 
     * Ask for the date and order number. 
     * If it exists, display the order information
     *  confirm removal. 
     *  If yes, removed from the list.
     * catch if order does not exist
     * catch if error reading/writing to file
     * catch if data is invalid
     */
    private void removeOrder() {
        try {
            view.displayRemoveOrderBanner();
            
            //get order date and number
            LocalDate date = view.getOrderDate();
            int orderNumber = view.getOrderNumber();
            
            //get corresponding order and get confirmation
            Order order = service.getOrder(date, orderNumber);
            Boolean confirmation = view.confirmOrder(order);
            
            if(confirmation)
                //renmove order from file
                service.removeOrder(date, order);
        } catch (FlooringMasteryDataValidationException | NoOrderException | FlooringMasteryPersistenceException ex) {
             view.displayErrorMessage(ex.getMessage());
        } 
        
    }

    /**
     * Save all orders files to a file
     * Called DataExport.txt within a Backup folder. 
     * Overwrite the data within the file with the latest order information. 
     * Include the date in MM-DD-YYYY format for every order
     * Catch errors reading orders and saving to backup file
     */
    private void exportData() {
        try {
            view.displayExportOrdersBanner();
            //export data
            service.exportData();
            view.displayExportOrderSuccessBanner();
        } catch (FlooringMasteryPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
        
    }
}
