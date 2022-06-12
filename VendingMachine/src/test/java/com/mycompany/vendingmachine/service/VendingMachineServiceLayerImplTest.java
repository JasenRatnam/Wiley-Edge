/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Jasen Ratnam
 */
public class VendingMachineServiceLayerImplTest {
    
    public VendingMachineServiceLayerImplTest() {
    }
    
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

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAllItems() {
        System.out.println("getAllItems");
        VendingMachineServiceLayerImpl instance = null;
        ArrayList<VendingItem> expResult = null;
        ArrayList<VendingItem> result = instance.getAllItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsCount method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetItemsCount() {
        System.out.println("getItemsCount");
        VendingMachineServiceLayerImpl instance = null;
        int expResult = 0;
        int result = instance.getItemsCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFunds method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testAddFunds() throws Exception {
        System.out.println("addFunds");
        BigDecimal fund = null;
        VendingMachineServiceLayerImpl instance = null;
        instance.addFunds(fund);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vend method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testVend() throws Exception {
        System.out.println("vend");
        int item = 0;
        VendingMachineServiceLayerImpl instance = null;
        String expResult = "";
        String result = instance.vend(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testLoadItems() throws Exception {
        System.out.println("loadItems");
        VendingMachineServiceLayerImpl instance = null;
        instance.loadItems();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFunds method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetFunds() {
        System.out.println("getFunds");
        VendingMachineServiceLayerImpl instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getFunds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testWriteItems() throws Exception {
        System.out.println("writeItems");
        VendingMachineServiceLayerImpl instance = null;
        instance.writeItems();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetChange() throws Exception {
        System.out.println("getChange");
        VendingMachineServiceLayerImpl instance = null;
        Change expResult = null;
        Change result = instance.getChange();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
}
