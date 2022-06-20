/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryServiceLayerImplTest {
    
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerImplTest() {
         ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service = 
            ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
    }
    
    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
        Order removededOrder = new Order("Bob", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.5"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
        removededOrder.setOrderNumber(1);
        
        List<Order> orderListBefore = service.getAllOrders(date);
        
        service.removeOrder(date, removededOrder);
        
        List<Order> orderListAfter = service.getAllOrders(date);
        
        removededOrder.setOrderDate(date);
        service.createOrder(removededOrder);
        
        assertEquals(orderListBefore, orderListAfter);
    }

    /**
     * Test of exportData method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testExportData() throws Exception {
       try{
            service.exportData();
       } catch(Exception e){
           fail("Incorrect exception thrown");
       }
        
        
    }

    /**
     * Test of getOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
        int orderNumber = 1;
        
        
        Order expResult = new Order("Bob", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.5"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
        
        expResult.setOrderDate(date);
        expResult.setOrderNumber(1);
        
        Order result = service.getOrder(date, orderNumber);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of updateOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testUpdateOrder() throws Exception {
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
        Order updatedOrder = new Order("Jasen", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.5"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
        
        List<Order> orderListBefore = service.getAllOrders(date);
        
       service.updateOrder(date, updatedOrder);
        
        List<Order> orderListAfter = service.getAllOrders(date);
        
        assertEquals(orderListBefore, orderListAfter);
        
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
        Order onlyOrder = new Order("Bob", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.5"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
        onlyOrder.setOrderDate(date);
        onlyOrder.setOrderNumber(1);
        
        ArrayList<Order> expResult = new ArrayList();
        expResult.add(onlyOrder);
        
        ArrayList<Order> result = service.getAllOrders(date);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTaxes method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetTaxes() throws Exception {
        Taxes tax1 = new Taxes("TX", "Texas", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
        Taxes tax2 = new Taxes("WA", "Washington", new BigDecimal("9.25").setScale(2, RoundingMode.HALF_UP));
        Taxes tax3 = new Taxes("KY", "Kentucky", new BigDecimal("6").setScale(2, RoundingMode.HALF_UP));
        Taxes tax4 = new Taxes("CA", "Calfornia", new BigDecimal("25").setScale(2, RoundingMode.HALF_UP));

        List<Taxes> expResult = new ArrayList();
        expResult.add(tax1);
        expResult.add(tax2);
        expResult.add(tax3);
        expResult.add(tax4);
        
        ArrayList<Taxes> result = service.getTaxes();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getProducts method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetProducts() throws Exception {
        Product product1 = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        Product product2 = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        Product product3 = new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        Product product4 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        
        
        List<Product> expResult = new ArrayList();
        expResult.add(product1);
        expResult.add(product2);
        expResult.add(product3);
        expResult.add(product4);
        
        ArrayList<Product> result = service.getProducts();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testCreateOrder() throws Exception {
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
        Order newOrder = new Order("Sponge", 
                "WA", 
                new BigDecimal("9.25"), 
                "Wood", 
                new BigDecimal("500"), 
                new BigDecimal("5.15"), 
                new BigDecimal("4.75"), 
                new BigDecimal("2575"), 
                new BigDecimal("2375"), 
                new BigDecimal("457.88"), 
                new BigDecimal("5407.88"));
        newOrder.setOrderDate(date);

        ArrayList<Order> expResult = service.getAllOrders(date);
        expResult.add(newOrder);
        
        service.createOrder(newOrder);
        
        ArrayList<Order> result = service.getAllOrders(date);
        assertEquals(expResult, result);
        
        
    }
    
}
