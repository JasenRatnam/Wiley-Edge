
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Jasen Ratnam
 */
public class VendingMachineView {
    //use the IO class to communicate with user
    private UserIO io;

    /**
     * constructor of view
     * @param myIo io object to get io from user
     */
    public VendingMachineView(UserIO myIo) {
        this.io = myIo;
    }
    
    /**
     * print menu of items to the user
     * @param items to print
     * @return choice chosen by user
     */
    public int printMenuAndGetSelection(List<VendingItem> items) {
        io.print("Main Menu");
        io.print("1. Insert Money");
        AtomicInteger j = new AtomicInteger(2);        
        
        items.stream()
             .map((i) -> i.toString())
             .forEach((i) -> System.out.println(j.getAndIncrement() + ". " + i));
           
        io.print(j + ". Exit");

        return io.readInt("Please select from the above choices.", 1, j.intValue());
    }
    
    /**
     * display banner when adding funds to account
     */
    public void addFundsBanner() {
        io.print("=== Adding Funds ===");
    }
     
    /**
     * display the success or failure of adding funds to the account
     * @param originalFund funds before adding 
     * @param newFund funds after to compare
     */
    public void displayFundsSuccessBanner(BigDecimal originalFund, BigDecimal newFund) {
        if(originalFund.compareTo(newFund) == 0){
            io.readString(
                "No new funds were added.  Please hit enter to continue");            
        } else if(originalFund.compareTo(newFund) > 0){
            io.readString(
                newFund + "$ was added.  Please hit enter to continue");            
        } 
    }
    
    /**
     * display the success of vending a item
     * If name is null, then item was not vented
     * @param name 
     */
    public void displayVendingSuccessBanner(String name) {
       if(name == null){
           io.readString(
            "Please hit enter to continue");
       }
       else{
           io.readString(
            name + " successfully vented.  Please hit enter to continue");
       }
        
    }
    
    /**
     * Display message if user tries to get an item without adding funds to system
     */
    public void displayAddFundsBanner() {
        io.readString(
            "Please add funds before taking a item out.  Please hit enter to continue");
    }
    
    /**
     * display banner while vending an item
    **/
    public void displayVendingBanner() {
        io.print("=== Vending: ===");
    }
    
    
    /**
     * display the funds currently in the system
     * @param b funds in the system to display
     */
    public void displayFundsBanner(BigDecimal b) {
         BigDecimal rounded = b.setScale(2, RoundingMode.HALF_UP);
        io.print("=== Funds: " + rounded.toString() +"$ ===");
    }
    
    /**
     * display an exit message to the user
     */
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    /**
     * display an error message
     * @param errorMsg msg to print
     */
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    /**
     * display unkown command banner
     */
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    /**
     * prompt user for fund information to add
     * @return 
     */
    public BigDecimal getNewFundInfo() {
        BigDecimal bigDecimalFund = BigDecimal.ZERO;
        String fund = io.readString("Enter funds to add to the machine: ");
        if(isNumeric(fund)) 
            bigDecimalFund = new BigDecimal(fund);
            
        if(bigDecimalFund.compareTo( BigDecimal.ZERO) <= 0){
            io.readString("You must enter a number bigger than 0. Please hit enter to continue");
            
            return BigDecimal.ZERO;
        }
        
        BigDecimal b = bigDecimalFund.setScale(2, RoundingMode.HALF_UP);

        return b;
    }
    
    /**
     * make sure a string contains numbers
     * @param str
     * @return 
     */
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    /**
     * banner to display change
     */
    public void displayChangeBanner() {
        io.print("=== Here is your change: ===");
    }

    /**
     * print out the change to the user
     * @param change to give
     */
    public void displayChange(Change change) {
        io.readString(change.toString() + "\nPlease hit enter to continue");
    }
}