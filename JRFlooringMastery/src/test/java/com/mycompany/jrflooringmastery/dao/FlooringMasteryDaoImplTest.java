/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.jrflooringmastery.dao;

import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class FlooringMasteryDaoImplTest {
    
    FlooringMasteryDao testDao;
    
    public FlooringMasteryDaoImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        String testBackupFile = "Test/Backup/DataExport.txt";
        String testProductFile = "Test/Data/Products.txt";
        String testTaxesFile = "Test/Data/Taxes.txt";
        
        // Use the FileWriter to quickly blank the file
        new FileWriter(testBackupFile);
        testDao = new FlooringMasteryDaoImpl(testBackupFile, testProductFile, testTaxesFile);
    }
    
    /**
     * Test of removeOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        
        List<Order> ordersBefore = testDao.getOrders(LocalDate.of(2022, Month.JUNE, 19));
        
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
        removededOrder.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        removededOrder.setOrderNumber(1);
        
        testDao.removeOrder(removededOrder);
        
        List<Order> ordersAfter = testDao.getOrders(LocalDate.of(2022, Month.JUNE, 19));
       
        //readd order for feature tests
        testDao.addOrder(removededOrder);
        
        assertNotEquals(ordersBefore, ordersAfter);
    }

    /**
     * Test of editOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        
        //change customer name "Bob" to Alex
        Order updatedOrder = new Order("Alex", 
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
        updatedOrder.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        updatedOrder.setOrderNumber(1);
        
        testDao.editOrder(updatedOrder);
        
        Order order = testDao.getOrder(LocalDate.of(2022, Month.JUNE, 19),1);
        
        //reset change
        updatedOrder.setCustomerName("Bob");
        testDao.editOrder(updatedOrder);
        
        assertEquals(order.getCustomerName(), "Alex");
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order("Jasen", 
                "TX", 
                new BigDecimal("4.45"), 
                "Laminate", 
                new BigDecimal("152"), 
                new BigDecimal("1.75"), 
                new BigDecimal("2.10"), 
                new BigDecimal("266"), 
                new BigDecimal("319.20"), 
                new BigDecimal("26.04"), 
                new BigDecimal("611.24"));
        
        //order number that should be set by service layer
        order.setOrderNumber(3);
        //order date that should be added to order object by service layer
        order.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));

        testDao.addOrder(order); 
        
        Order newOrder = testDao.getOrder(LocalDate.of(2022, Month.JUNE, 19), 3);

        //remove it from file for future testing
        testDao.removeOrder(order);
        
        assertEquals(newOrder,order);
    }

    /**
     * Test of getMaxOrderNumber method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testGetMaxOrderNumber() throws Exception {
        int expResult = 2;
        int result = testDao.getMaxOrderNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrder method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
       
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
        int orderNumber = 2;
        Order expResult = new Order("Sponge", 
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
        
        expResult.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        expResult.setOrderNumber(2);
        
        Order result = testDao.getOrder(date, orderNumber);
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getOrders method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testGetOrders() throws Exception {
        LocalDate date = LocalDate.of(2022, Month.JUNE, 19);
         Order order1 = new Order("Sponge", 
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
        
        order1.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        order1.setOrderNumber(2);
         
        Order order2 = new Order("Bob", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.50"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
         
        order2.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        order2.setOrderNumber(1);
        
        List<Order> expResult = new ArrayList();
        expResult.add(order1);
        expResult.add(order2);
        
        List<Order> result = testDao.getOrders(date);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTaxes method, of class FlooringMasteryDaoImpl.
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

        List<Taxes> result = testDao.getTaxes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProducts method, of class FlooringMasteryDaoImpl.
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
        
        
        List<Product> result = testDao.getProducts();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of export method, of class FlooringMasteryDaoImpl.
     */
    @Test
    public void testExport() throws Exception {
        testDao.export();
        
        Order order1 = new Order("Sponge", 
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
        
        order1.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        order1.setOrderNumber(2);
         
        Order order2 = new Order("Bob", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.50"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
         
        order2.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        order2.setOrderNumber(1);
        
        List<Order> expResult = new ArrayList();
        expResult.add(order1);
        expResult.add(order2);
        
        
        
        
        //read file
        Scanner scanner;
        
        List<Order> actResult = new ArrayList();
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Test/Backup/DataExport.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not open " + "Test/Backup/DataExport.txt");
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;

        Order currentOrder;
        
        //skip header
        currentLine = scanner.nextLine();
        
        //go through every line of the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                //create a product with current line
                currentOrder = unmarshallOrder(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException(
                        "Could not load a product: " + e.getMessage());
            }
            //add product to list
            actResult.add(currentOrder);
        }
        // close scanner
        scanner.close();
        
        assertEquals(expResult, actResult);
        
    }
    
    /**
     * unmarshall order text string
     * Create Order object with string information
     * "<OrderNumber>,<CustomerName>,<State>,<TaxRate>,<ProductType>, <Area>,
     * <CostPerSquareFoot>,<LaborCostPerSquareFoot>, <MaterialCost>,<LaborCost>,
     * <Tax>,<Total>"
      
     * @param orderAsText string to converst to Order object
     * @return Order object
     */
    private Order unmarshallOrder(String orderAsText){
       
        //split string and decompose to the different variables 
        String[] orderTokens = orderAsText.split(",");

        int orderNumber = Integer.parseInt(orderTokens[0]);
        String customerName = orderTokens[1];
        String state = orderTokens[2];
        BigDecimal TaxRate = new BigDecimal(orderTokens[3]);
        String ProductType = orderTokens[4];
        BigDecimal Area = new BigDecimal(orderTokens[5]);
        BigDecimal CostPerSquareFoot = new BigDecimal(orderTokens[6]);
        BigDecimal LaborCostPerSquareFoot = new BigDecimal(orderTokens[7]);
        BigDecimal MaterialCost = new BigDecimal(orderTokens[8]);
        BigDecimal LaborCost = new BigDecimal(orderTokens[9]);
        BigDecimal Tax = new BigDecimal(orderTokens[10]);
        BigDecimal Total = new BigDecimal(orderTokens[11]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse(orderTokens[12],formatter);
        
        //create order object and add order number
        Order orderFromFile = new Order(customerName, state, TaxRate, ProductType, 
                Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, 
                LaborCost, Tax, Total);
        orderFromFile.setOrderNumber(orderNumber);
        orderFromFile.setOrderDate(date);

       
        return orderFromFile;
    }
    
}
