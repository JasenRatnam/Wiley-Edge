package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Jasen Ratnam
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    /**
     * constructor that initializes dao and auditdao
     * @param dao
     * @param auditDao 
     */
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    /**
     * get an arraylist of all the items in the machine
     * @return 
     */
    @Override
    public ArrayList<VendingItem> getAllItems(){
        return dao.getAllItems();
    }

    /**
     * get count of items
     * @return an integer of items in the machine
     */
    @Override
    public int getItemsCount(){
        return dao.getItemCount();
    }

    /**
     * add a big decimal fund to the machine
     * scaled to 2
     * rounded to half_up
     * @param fund to add
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public void addFunds(BigDecimal fund) throws VendingMachinePersistenceException {
        BigDecimal rounded = fund.setScale(2, RoundingMode.HALF_UP);
        dao.addFund(rounded);
        auditDao.writeAuditEntry(rounded.toString() + "$ added to funds");
    }

    /**
     * vend item from the machine
     * @param item to vend
     * @return name of item vended
     * null if there is an error
     * @throws VendingMachinePersistenceException
     * @throws InsufficientFundsException   not enough money to buy it
     * @throws NoItemInventoryException     not enough inventory
     */
    @Override
    public String vend(int item) throws VendingMachinePersistenceException,
                                        InsufficientFundsException,
                                        NoItemInventoryException{
        String name = dao.vendItem(item);
        
        if(name == null){
            auditDao.writeAuditEntry("Item could not be vended.");            
        } else{
            auditDao.writeAuditEntry("Item: " + name + " vended.");
        }
        
        return name;
    }

    /**
     * load items from a file
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public void loadItems() throws VendingMachinePersistenceException{
        auditDao.writeAuditEntry("Items loaded from file");  
        
        dao.loadItems();
    }

    /**
     * get the total amount in the machine
     * @return bigdecimal of the funds in the machine
     */
    @Override
    public BigDecimal getFunds() {
        return dao.getFunds();
    }

    /**
     * 
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public void writeItems() throws VendingMachinePersistenceException{
        auditDao.writeAuditEntry("Items saved to file");  
        
        dao.writeItems();
    }

    /**
     * get the change to give back to user
     * based on the fund left in the machine
     * @return the change needed
     * @throws VendingMachinePersistenceException 
     */
    @Override
    public Change getChange() throws VendingMachinePersistenceException{
        Change change = new Change(dao.getFunds());
        dao.emptyFunds();
        auditDao.writeAuditEntry("Change has been returned.\n" + change.toString()); 
        
        return change;
    }
}
