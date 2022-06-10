
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Service lsyer of the vending machin
 * @author Jasen Ratnam
 */
public interface VendingMachineServiceLayer {

    /**
     * get all the items in the machine
     * @return an arraylist of items
     */
    public ArrayList<VendingItem> getAllItems();

    /**
     * get number of items in the machine
     * @return 
     */
    public int getItemsCount();

    /**
     * add funds to the machine
     * @param fund BigDecimal amount added to the machine
     * @throws VendingMachinePersistenceException if there is an error when adding money
     */
    public void addFunds(BigDecimal fund) throws VendingMachinePersistenceException;

    /**
     * vend the chosen item by the user
     * @param item chosen by the user
     * @return the name of the item vended
     * @throws VendingMachinePersistenceException
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException 
     */
    public String vend(int item) throws VendingMachinePersistenceException,
                                        InsufficientFundsException,
                                        NoItemInventoryException;
      
    /**
     * load items from a file
     * @throws VendingMachinePersistenceException 
     */
    public void loadItems() throws VendingMachinePersistenceException;
    
    /**
     * get the BigDecimal value of the funds in the machine
     * @return 
     */
    public BigDecimal getFunds();

    /**
     * saving the items in the machine to a file
     * @throws VendingMachinePersistenceException 
     */
    public void writeItems() throws VendingMachinePersistenceException;

    /**
     * get the change to give back to the user
     * change from fund left in the machine
     * @return the change to give
     * @throws VendingMachinePersistenceException 
     */
    public Change getChange() throws VendingMachinePersistenceException;

}
