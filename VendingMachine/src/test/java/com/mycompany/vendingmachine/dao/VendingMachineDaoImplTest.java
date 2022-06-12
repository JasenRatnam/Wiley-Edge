/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingItem;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jasen Ratnam
 */
public class VendingMachineDaoImplTest {
    
    VendingMachineDaoImpl dao = new VendingMachineDaoImpl("testItems.txt");
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
       
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    @Test
    public void testGetAllItems() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //get list of items
        ArrayList<VendingItem> listItemsMethod = dao.getAllItems();
        
        //createlist that should be given
        VendingItem item1 =new VendingItem("Lays Chips", new BigDecimal("1.94"), listItemsMethod.get(0).getInventory());
        VendingItem item2 =new VendingItem("Snickers Bar", new BigDecimal("2.65"), listItemsMethod.get(1).getInventory());
        VendingItem item3 =new VendingItem("Clif Bars", new BigDecimal("3.30"), listItemsMethod.get(2).getInventory());
        
        ArrayList<VendingItem> listItemsCorrect = new ArrayList();
        listItemsCorrect.add(item1);
        listItemsCorrect.add(item2);
        listItemsCorrect.add(item3);
        
        //compare
        assertEquals(listItemsCorrect,listItemsMethod);  
    }
    
    @Test
    public void testGetItemCount() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //getcount
        int sizeMethod = dao.getItemCount();
        //count that should be given
        int sizeCorrect = 3;
        
        //compare
        assertEquals(sizeMethod,sizeCorrect);  
    }
    
    @Test
    public void testAddFund() {
        //start with 0 funds
        dao.emptyFunds();
        
        //addfunds
        dao.addFund(new BigDecimal("5"));
        
        //should be this fund
        BigDecimal correctFund = new BigDecimal("5");
        BigDecimal c= correctFund.setScale(2, RoundingMode.HALF_UP);
        
        //get the fund in system
        BigDecimal methodFund = dao.getFunds();
       
        //compare
        assertEquals(c,methodFund);  
    }
    
    @Test
    public void testGetFunds() {
        //start with 0
        dao.emptyFunds();
        
        //get funds in system
        BigDecimal methodFund = dao.getFunds();
        BigDecimal correctFund = BigDecimal.ZERO;
        
        //compareto make sure its 0
        assertEquals(correctFund,methodFund); 
        
        //add 5
        dao.addFund(new BigDecimal("5"));
        
        //should be 5
        correctFund = new BigDecimal("5");
        BigDecimal c = correctFund.setScale(2, RoundingMode.HALF_UP);
        methodFund = dao.getFunds();
       
        //compare
        assertEquals(c,methodFund); 
    }
    
    @Test
    public void testEmptyFunds() {
        //already at 0, add funds to empty it
        dao.addFund(new BigDecimal("5"));
        dao.emptyFunds();
        
        
        //should be 5
        BigDecimal correctFund = BigDecimal.ZERO;
        BigDecimal methodFund = dao.getFunds();
       
        assertEquals(correctFund,methodFund); 
    }
    
    @Test
    public void testVendItemWithInventoryNoFunds() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //empty funds
        int pos = 0;
        dao.emptyFunds();
        
        //catch exception
        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
        dao.vendItem(pos);
        });

        String actualMessage = exception.getMessage();

    
        
        String expectedMessage = "ERROR: Could not get " + "Lays Chips" + " due to insufficient funds\n"
                            + "You have: " + dao.getFunds().toString() + "$";
        
        //compare messages
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testVendItemNoInventoryWithFunds() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //vend first item
        int pos = 1;
        dao.addFund(new BigDecimal("5"));
        
        Exception exception = assertThrows(NoItemInventoryException.class, () -> {
        dao.vendItem(pos);
        });

        String actualMessage = exception.getMessage();

        
        String expectedMessage = "ERROR: Could not get " + "Snickers Bar" 
                + " due to insufficient inventory";
                
        assertTrue(actualMessage.contains(expectedMessage));        
    }
    
    @Test
    public void testVendItemWithInventoryWithFunds() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //start with 0
        dao.emptyFunds();
        
        dao.addFund(new BigDecimal("5"));
        
        //vend item 
        String message = "";
        long initalInv = dao.getAllItems().get(0).getInventory();
        try {
            //vend first item
            int pos = 0;
            dao.vendItem(pos);
        } catch (InsufficientFundsException | NoItemInventoryException ex) {
            message = ex.getMessage();
        }
                
        // no error message
        //invetory lost 1
        //funds correctly updated
        assertEquals("", message);
        assertEquals(initalInv-1, dao.getAllItems().get(0).getInventory());
        assertEquals(new BigDecimal("3.06"), dao.getFunds());
    }
    
    @Test
    public void testLoadItem() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //remove 2 lays chips
        //reload file
        //items should be reset to inital inv
        
        dao.addFund(new BigDecimal("5"));
        long initalInv = dao.getAllItems().get(0).getInventory();

        try {
            //vend first item
            int pos = 0;
            dao.vendItem(pos);
            dao.vendItem(pos);
            
            assertEquals(initalInv-2, dao.getAllItems().get(0).getInventory());
            
            dao.loadItems();
            
            assertEquals(initalInv, dao.getAllItems().get(0).getInventory());
        } catch (VendingMachinePersistenceException |InsufficientFundsException | NoItemInventoryException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Test
    public void testWriteItem() {
        //load items
        try {
            dao.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        
        //dispense items
        //save items with new inventory of items
        //load items to see if the new inventory is saved
        
        dao.addFund(new BigDecimal("5"));
        long initalInv = dao.getAllItems().get(0).getInventory();

        try {
            //vend first item
            int pos = 0;
            dao.vendItem(pos);
            dao.vendItem(pos);
            
            //lost 2 inv
            assertEquals(initalInv-2, dao.getAllItems().get(0).getInventory());
            
            //savenew inv
            dao.writeItems();
            
            //reload saved items to make sure inv was updated
            dao.loadItems();
            
            assertEquals(initalInv-2, dao.getAllItems().get(0).getInventory());
        } catch (VendingMachinePersistenceException |InsufficientFundsException | NoItemInventoryException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
