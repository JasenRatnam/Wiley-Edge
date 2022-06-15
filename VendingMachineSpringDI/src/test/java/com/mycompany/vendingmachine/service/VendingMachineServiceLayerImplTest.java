/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Jasen Ratnam
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    /**
     * Test of getAllItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAllItems() {
        VendingItem onlyItem = new VendingItem("Lays Chip", new BigDecimal("1.94"),20);
        ArrayList<VendingItem> expResult = new ArrayList();
        expResult.add(onlyItem);
        
        ArrayList<VendingItem> result = service.getAllItems();
        assertEquals(expResult, result);
    }

    /**
     * Test of getItemsCount method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetItemsCount() {
        int expResult = 1;
        int result = service.getItemsCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFunds method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testAddFunds() {
        BigDecimal fund = new BigDecimal("5.00");
        try {
            service.addFunds(fund);
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
            Assertions.fail(ex.getMessage());
        }
        
        assertEquals(fund, service.getFunds());
    }

    /**
     * Test of vend method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testVend() {
        int item = 0;
        String expResult = "Lays Chip";
        String result = null;
        try {
            result = service.vend(item);
        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException ex) {
            System.out.println(ex.getMessage());
            Assertions.fail(ex.getMessage());
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of loadItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testLoadItems() {
        try {
            service.loadItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
            Assertions.fail(ex.getMessage());
        }
    }

    /**
     * Test of getFunds method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetFunds() {
        BigDecimal result = service.getFunds();
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    /**
     * Test of writeItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testWriteItems() {
        try {
            service.writeItems();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
            Assertions.fail(ex.getMessage());
        }
        
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetChange() {
        Change expResult = new Change(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        
        Change result = null;
        try {
            result = service.getChange();
        } catch (VendingMachinePersistenceException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(expResult.toString(), result.toString());
    }
}
