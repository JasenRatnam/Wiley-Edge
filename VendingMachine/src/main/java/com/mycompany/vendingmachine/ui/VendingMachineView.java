
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jasen Ratnam
 */
public class VendingMachineView {
    //use the IO class to communicate with user
    private UserIO io;

    public VendingMachineView(UserIO myIo) {
        this.io = myIo;
    }
    
    //menu
    public int printMenuAndGetSelection(List<VendingItem> items) {
        io.print("Main Menu");
        io.print("1. Insert Money");
        
        List<String> itemNames = items.stream()
                               .map((i) -> i.toString())
                               .collect(Collectors.toList());
        
        int i = 2;
        for(String name : itemNames){
            System.out.println(i + ". " + name);
            i++;
        }
        
        io.print(i + ". Exit");

        return io.readInt("Please select from the above choices.", 1, i);
    }
    
    public void addFundsBanner() {
        io.print("=== Adding Funds ===");
    }
     
    public void displayFundsSuccessBanner(BigDecimal originalFund, BigDecimal newFund) {
        if(originalFund.compareTo(newFund) == 0){
            io.readString(
                "No new funds were added.  Please hit enter to continue");            
        } else if(originalFund.compareTo(newFund) > 0){
            io.readString(
                newFund + "$ was added.  Please hit enter to continue");            
        } 
    }
    
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
    
    public void displayAddFundsBanner() {
        io.readString(
            "Please add funds before taking a item out.  Please hit enter to continue");
    }
    
     //display exit banner
    public void displayVendingBanner() {
        io.print("=== Vending: ===");
    }
    
    //display exit banner
    public void displayFundsBanner(BigDecimal b) {
         BigDecimal rounded = b.setScale(2, RoundingMode.HALF_UP);
        io.print("=== Funds: " + rounded.toString() +"$ ===");
    }
    
    //display exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    //display exit banner
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    //display unkown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public BigDecimal getNewFundInfo() {
        BigDecimal bigDecimalFund = new BigDecimal("0.00");
        String fund = io.readString("Enter funds to add to the machine: ");
        if(isNumeric(fund)) 
            bigDecimalFund = new BigDecimal(fund);
            
        if(bigDecimalFund.compareTo( new BigDecimal("0.00")) <= 0){
            io.readString("You must enter a number bigger than 0. Please hit enter to continue");
            
            return new BigDecimal("0.00");
        }
        
        BigDecimal b = bigDecimalFund.setScale(2, RoundingMode.HALF_UP);

        return b;
    }
    
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void displayChangeBanner() {
        io.print("=== Here is your change: ===");
    }

    public void displayChange(Change change) {
        io.readString(change.toString() + "\nPlease hit enter to continue");
    }
}
