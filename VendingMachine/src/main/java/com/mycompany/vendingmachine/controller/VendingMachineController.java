
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import com.mycompany.vendingmachine.service.VendingMachineServiceLayer;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Orchestrator of the application. It knows what needs to be done, 
 * when it needs to be done, and what component can do the job.
 * 
 * @author Jasen Ratnam
 */
public class VendingMachineController {

    //initialisation
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    /**
     * Constructor of Vending Machine Controller
     * @param view component of the program that handles user I/O
     * @param service component that has the service layer of the program,
                      handles the business rules
     */
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    /**
     * method that runs the application
     * get user option and runs that option
     */
    public void run(){
        //initialisation
        boolean keepGoing = true;
        int menuSelection;
        boolean haveFunds = false;
        
        //while program is running
        try{
            //load items from file
            loadItems();
            
            while (keepGoing) {
                //display current funds
                displayFundsBanner();
                
                // print choices and get choice from user
                menuSelection = getMenuSelection();
                                
                //if funds have been added
                haveFunds = getFunds().compareTo(new BigDecimal("0.00")) != 0;
                
                //get number of items
                int itemCount = getNumberOfItems();
                
                //cases depending on choice
                if(menuSelection == itemCount){
                    //exit
                    keepGoing = false;
                } else if(menuSelection != 1 && !haveFunds){
                    //no funds added and user tries to buy something
                    insufficientFundsBanner();
                } else if(menuSelection == 1){
                    //add money
                    addMoney();
                } else if(menuSelection > 1 && menuSelection < itemCount){
                    //get items corresponding to number
                    int item = menuSelection -2;
                    selectItem(item);
                } else
                    unknownCommand();
                
                //return change of remaining funds to user
                getChange(haveFunds);
            }
            
            //save all items updated to the same file
            saveItems();
        }
        catch(VendingMachinePersistenceException e){
            displayError(e.getMessage());
        }
        
        //exit program
        exitMessage();
    }
    
    /**
     * Get selection of menu from user
     * Uses list of items to print menu options 
     * Menu option depends on the items in the list
     * Gets selection of the user from the view
     * @return integer value of menu choice chosen
     */
     private int getMenuSelection() {
        ArrayList<VendingItem> itemList = service.getAllItems();
        return view.printMenuAndGetSelection(itemList);
    }
     
     /**
      * Displays an unknown command message from the view
      */
     private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

     /**
      * Displays an exit message from the view
      */
    private void exitMessage() {
        view.displayExitBanner();
    }

    /**
     * add money to the program to buy items from the vending machine
     * get the money to add from the view
     * add the money to the funds using the service layer
     * display results of fund addition using view
     * @throws VendingMachinePersistenceException when an error has occured while adding money
     */
    private void addMoney() throws VendingMachinePersistenceException{
        view.addFundsBanner();
        
        BigDecimal originalFund = service.getFunds();
        BigDecimal addedFund = view.getNewFundInfo();
        service.addFunds(addedFund);
        
        view.displayFundsSuccessBanner(originalFund, service.getFunds());
    }

    /**
     * select an item from the vending machine to buy
     * uses the service to vend the item which returns the name of the item
     * catches InsufficientFundsException and NoItemInventoryException from the service
     * vending has failed if these exception happens
     * @param item integer value corresponding to the item position in the list of items
     * @throws VendingMachinePersistenceException 
     */
    private void selectItem(int item) throws VendingMachinePersistenceException{
        view.displayVendingBanner();
        String name = null;
        try {
            name = service.vend(item);
        } catch (InsufficientFundsException | NoItemInventoryException ex) {
            view.displayErrorMessage(ex.getMessage());
            name = null;
        }
        finally{
            view.displayVendingSuccessBanner(name);
        }
    }

    /**
     * display banner that ask users to add funds before trying to vend item
     */
    private void insufficientFundsBanner() {
        view.displayAddFundsBanner();
    }
    
    /**
     * display the funds currently in the machine
     */
    private void displayFundsBanner() {
        view.displayFundsBanner(service.getFunds());
    }

    /**
     * display any error message caught
     * @param message gotten from the error caught
     */
    private void displayError(String message) {
        view.displayErrorMessage(message);
    }

    /**
     * get the number of items with inventory in the machine
     * add 2 for the add money and exit option
     * @return the number of options that was displayed in the main menu options
     */
    private int getNumberOfItems() {
        return service.getItemsCount()+2;
    }

    /**
     * get the total funds currently in the machine
     * @return BigDecimal value of the funds in the machine
     */
    private BigDecimal getFunds() {
        return service.getFunds();
    }

    /**
     * load all the items from the file
     * @throws VendingMachinePersistenceException if an error happens while reading and saving the items
     */
    private void loadItems() throws VendingMachinePersistenceException{
        service.loadItems();
    }

    /**
     * save all the items in the machine with the updated inventory to the file
     * @throws VendingMachinePersistenceException if an error happens while saving to the file
     */
    private void saveItems() throws VendingMachinePersistenceException{
        service.writeItems();
    }

    /**
     * return the change to the user after vending an item or exiting the machine
     * @param haveFunds return changes only if there is funds in the system
     * @throws VendingMachinePersistenceException if an error happens during the processs
     */
    private void getChange(boolean haveFunds) throws VendingMachinePersistenceException {
        if(haveFunds){
            view.displayChangeBanner();
            Change change = service.getChange();
            view.displayChange(change);
        }
    }
}