
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingItem;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * interface of DAO of the Vending Machine application
 * @author Jasen Ratnam
 */
public interface VendingMachineDao {

    /**
     * get all the items in the program
     * include items with 0 inventory
     * @return arraylist of all items
     */
    public ArrayList<VendingItem> getAllItems();

    /**
     * get number of items with an inventory
     * @return the number of items in the machine with inventory available
     */
    public int getItemCount();

    /**
     * add funds to the machine
     * @param fund to add to the machine
     */
    public void addFund(BigDecimal fund);

    /**
     * vend item selected by the user
     * providing they have enough funds and the item is in inventory
     * @param item selected by the user
     * @return the name of the item selected, null if it cannot be bought
     * @throws InsufficientFundsException if the machine does not have enough funds
     * @throws NoItemInventoryException if the item does not have inventory
     */
    public String vendItem(int item) throws InsufficientFundsException,
                                            NoItemInventoryException;
        
    /**
     * load items from a text file
     * @throws VendingMachinePersistenceException if an error occurs while reading the file
     */
    public void loadItems() throws VendingMachinePersistenceException;
    
    /**
     * save items to a text file
     * with updated inventory
     * @throws VendingMachinePersistenceException if an error occurs while writing to file
     */
    public void writeItems() throws VendingMachinePersistenceException;

    /**
     * get the total funds in the system
     * @return BigDecimal value of the funds in the machine
     */
    public BigDecimal getFunds();

    /**
     * empty the machine of all funds
     */
    public void emptyFunds();

}
