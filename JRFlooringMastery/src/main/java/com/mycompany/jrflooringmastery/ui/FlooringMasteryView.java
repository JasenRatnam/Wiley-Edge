
package com.mycompany.jrflooringmastery.ui;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryView {

    //use the IO class to communicate with user
    private UserIO io;

    public FlooringMasteryView(UserIO myIo) {
        this.io = myIo;
    }

    //menu
    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* Main Menu");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");


        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    //display exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    //display exit banner
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.readString(errorMsg + ". Please press enter to continue.");
    }

    //display unkown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayOrdersBanner() {
        io.print("=== Orders For Given Data ===");
    }

    public LocalDate getOrderDate() throws FlooringMasteryPersistenceException {
        String date = io.readString("What is the date of orders you want? (dd/MM/yyyy)");
        
        LocalDate dateFormat = null;
        try{
            dateFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch(Exception e){
            throw new FlooringMasteryPersistenceException(date + " is of invalid format." 
                    + "\n Please enter a date of the following format: dd/MM/yyyy");
        }
        
        return dateFormat;
    }

    public void displayOrders(ArrayList<Order> orders) {
        io.print("=== Orders: ===");
        orders.stream()
             .map((i) -> i.toString())
             .forEach((i) -> io.print(i));
    }
}
