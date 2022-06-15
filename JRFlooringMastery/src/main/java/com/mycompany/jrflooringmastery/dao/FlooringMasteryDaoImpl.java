
package com.mycompany.jrflooringmastery.dao;

import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import com.mycompany.jrflooringmastery.service.FlooringMasteryDataValidationException;
import com.mycompany.jrflooringmastery.service.NoOrderException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryDaoImpl implements FlooringMasteryDao{

    private final String BACKUP_FILE;
    private final String DATA_PRODUCTS_FILE;
    private final String DATA_TAXES_FILE;
    private String ORDERS_FILE;
    private final String DELIMITER = ",";
    
    private ArrayList<Taxes> taxes = null;
    private ArrayList<Product> products = null;
    private ArrayList<Order> orders = new ArrayList<>();


    public FlooringMasteryDaoImpl() {
        this.BACKUP_FILE = "DataExport.txt";
        this.DATA_PRODUCTS_FILE = "Products.txt";
        this.DATA_TAXES_FILE = "Taxes.txt";
    }

    public FlooringMasteryDaoImpl(String BACKUP_FILE, String DATA_PRODUCTS_FILE, String DATA_TAXES_FILE, String ORDERS_FILE) {
        this.BACKUP_FILE = BACKUP_FILE;
        this.DATA_PRODUCTS_FILE = DATA_PRODUCTS_FILE;
        this.DATA_TAXES_FILE = DATA_TAXES_FILE;
        this.ORDERS_FILE = ORDERS_FILE;
    }

    @Override
    public List<Order> getOrders(LocalDate date) throws NoOrderException,FlooringMasteryPersistenceException {

        
        List<Order> ordersDate = loadOrders(date);
        
        if(ordersDate != null){
            return ordersDate;
        }
        else{
            throw new NoOrderException("There does not exist orders on " +  date.toString());
        }
    }

    @Override
    public void addOrder(Order order) throws FlooringMasteryDataValidationException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getNextOrderNumber() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editOrder(Order originalOrder, Order updatedOrder) throws NoOrderException, FlooringMasteryDataValidationException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws NoOrderException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void loadProducts() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void loadTaxes() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        List<Order> orderDate = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String filename = "Orders/Orders_" + date.format(formatter) + ".txt";
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(filename)));
        } catch (FileNotFoundException e) {
            return null;
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        Order currentOrder;
        
        //skip header
        currentLine = scanner.nextLine();
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                currentOrder = unmarshallOrder(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException("Could not load an order: " + e.getMessage());
            }
            orderDate.add(currentOrder);
        }
        // close scanner
        scanner.close();
        
        return orderDate;
    }

    @Override
    public void saveAllOrders() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private Order unmarshallOrder(String orderAsText){
       
        String[] orderTokens = orderAsText.split(DELIMITER);

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
        
        Order orderFromFile = new Order(customerName, state, TaxRate, ProductType, 
                Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, 
                LaborCost, Tax, Total);
        orderFromFile.setOrderNumber(orderNumber);

       
        return orderFromFile;
    }
    
    
    
    
}
