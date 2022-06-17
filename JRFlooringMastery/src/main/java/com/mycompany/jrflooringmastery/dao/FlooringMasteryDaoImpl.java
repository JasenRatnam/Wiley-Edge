
package com.mycompany.jrflooringmastery.dao;

import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import com.mycompany.jrflooringmastery.service.NoOrderException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryDaoImpl implements FlooringMasteryDao{

    private final String BACKUP_FILE;
    private final String DATA_PRODUCTS_FILE;
    private final String DATA_TAXES_FILE;
    private final String DELIMITER = ",";
    private int maxOrderNumber;
    
    private List<Taxes> taxe = new ArrayList<>();
    private List<Product> product = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    
    public FlooringMasteryDaoImpl() {
        this.BACKUP_FILE = "Backup/DataExport.txt";
        this.DATA_PRODUCTS_FILE = "Data/Products.txt";
        this.DATA_TAXES_FILE = "Data/Taxes.txt";
    }

    public FlooringMasteryDaoImpl(String BACKUP_FILE, String DATA_PRODUCTS_FILE, String DATA_TAXES_FILE) {
        this.BACKUP_FILE = BACKUP_FILE;
        this.DATA_PRODUCTS_FILE = DATA_PRODUCTS_FILE;
        this.DATA_TAXES_FILE = DATA_TAXES_FILE;
    }
    
    @Override
    public void removeOrder(Order removededOrder)throws FlooringMasteryPersistenceException {
        String filename = getOrderFileName(removededOrder.getOrderDate());
        
        List<Order> ordersDate = loadOrders(removededOrder.getOrderDate());
        
        int i = 0;
        int chosen = -1;
        for(Order order : ordersDate){
            if(order.getOrderNumber() == removededOrder.getOrderNumber()){
                chosen = i;
            }
            i++;
        }
        if(chosen != -1){
            ordersDate.remove(chosen);
        }else
            throw new FlooringMasteryPersistenceException("Could not remove Order " 
                    + removededOrder.getOrderNumber());
        
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(filename));
            
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                   "Could not save order data");
        }
        
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        
        String orderAsText;
        for(Order order : ordersDate){
            orderAsText = marshallOrder(order);
            out.println(orderAsText);
            out.flush();
        }
        
        out.close();
        
    }

    @Override
    public void editOrder(Order updatedOrder) throws FlooringMasteryPersistenceException{
        String filename = getOrderFileName(updatedOrder.getOrderDate());
        
        List<Order> ordersDate = loadOrders(updatedOrder.getOrderDate());
        
        int i = 0;
        int chosen = -1;
        for(Order order : ordersDate){
            if(order.getOrderNumber() == updatedOrder.getOrderNumber()){
                chosen = i;
            }
            i++;
        }
        if(chosen != -1){
            ordersDate.remove(chosen);
            ordersDate.add(updatedOrder);
        }else
            throw new FlooringMasteryPersistenceException("Could not update Order " 
                    + updatedOrder.getOrderNumber());
        
        
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(filename));
            
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                   "Could not save order data");
        }
        
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        
        String orderAsText;
        for(Order order : ordersDate){
            orderAsText = marshallOrder(order);
            out.println(orderAsText);
            out.flush();
        }
        
        out.close();
        
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException, FlooringMasteryPersistenceException {
        List<Order> ordersDate = loadOrders(date);
        
        Order order = ordersDate.stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findAny()
                .orElse(null);
        
        return order;
    }
    
    @Override
    public void addOrder(Order order) throws FlooringMasteryPersistenceException {
        
        PrintWriter out;
       
        String filename = getOrderFileName(order.getOrderDate());
        String orderAsText = marshallOrder(order);
        File f = new File(filename);
        
        try {
            if(f.exists() && !f.isDirectory()){
                //append to file with keyword true
                out = new PrintWriter(new FileOutputStream(new File(filename), true));
            }
            else{
                out = new PrintWriter(filename);
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            }
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not add order " 
                    + order.getOrderNumber() + "to the file: " + filename);
        }
        
        out.println(orderAsText);
        out.flush();
        out.close();
        
    }
    
    @Override
    public int getMaxOrderNumber() throws FlooringMasteryPersistenceException{
        
        loadAllOrders();
                
        try{
            maxOrderNumber = orders.stream()
                .mapToInt(i -> i.getOrderNumber())
                .max().orElseThrow(NoSuchElementException::new);
        }catch(NoSuchElementException e){
            throw new FlooringMasteryPersistenceException("Couldn't get the order number");
        }

        return maxOrderNumber;
    }
    
    @Override
    public List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException {

        
        List<Order> ordersDate = loadOrders(date);
        
        if(ordersDate != null){
            return ordersDate;
        }
        else{
            throw new FlooringMasteryPersistenceException(
                    "There does not exist orders on " +  date.toString());
        }
    }
    
    @Override
    public List<Taxes> getTaxes() throws FlooringMasteryPersistenceException {
        loadTaxes();
        
        if(taxe != null){
            return taxe;
        }
        else{
            throw new FlooringMasteryPersistenceException(
                    "Could not load taxes information.");
        }
    }

    @Override
    public List<Product> getProducts() throws FlooringMasteryPersistenceException {
        loadProducts();
        
        if(product != null){
            return product;
        }
        else{
            throw new FlooringMasteryPersistenceException(
                    "Could not load product information.");
        }
    }
    
    private String getOrderFileName(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String filename = "Orders/Orders_" + date.format(formatter) + ".txt";
        
        return filename;
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

    private Product unmarshallProduct(String productAsText){
       
        String[] productTokens = productAsText.split(DELIMITER);

        String productType = productTokens[0];
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        BigDecimal LaborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        
        Product product = new Product(productType, costPerSquareFoot, LaborCostPerSquareFoot);
       
        return product;
    }
    
    private Taxes unmarshallTaxes(String taxesAsText){
       
        String[] taxesTokens = taxesAsText.split(DELIMITER);

        String state = taxesTokens[0];
        String StateName = taxesTokens[1];
        BigDecimal taxRate = new BigDecimal(taxesTokens[2]);
        
        Taxes tax = new Taxes(state, StateName, taxRate);

       
        return tax;
    }
    
    private String marshallOrder(Order order){
       
        String itemAsText = order.getOrderNumber() + DELIMITER;
        itemAsText += order.getCustomerName()+ DELIMITER;
        itemAsText += order.getState()+ DELIMITER;
        itemAsText += order.getTaxRate()+ DELIMITER;
        itemAsText += order.getProductType()+ DELIMITER;
        itemAsText += order.getArea()+ DELIMITER;
        itemAsText += order.getCostPerSquareFoot()+ DELIMITER;
        itemAsText += order.getLaborCostPerSquareFoot()+ DELIMITER;
        itemAsText += order.getMaterialCost()+ DELIMITER;
        itemAsText += order.getLaborCost()+ DELIMITER;
        itemAsText += order.getTax()+ DELIMITER;
        itemAsText += order.getTotal();

        return itemAsText;
    }
    
    private void loadProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        product = new ArrayList<>();
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DATA_PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not open " + DATA_PRODUCTS_FILE);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        Product currentProduct;
        
        //skip header
        currentLine = scanner.nextLine();
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                currentProduct = unmarshallProduct(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException(
                        "Could not load a product: " + e.getMessage());
            }
            product.add(currentProduct);
        }
        // close scanner
        scanner.close();
        
    }

    private void loadTaxes() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        taxe = new ArrayList<>();
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DATA_TAXES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not open " + DATA_TAXES_FILE);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        Taxes currentTaxe;
        
        //skip header
        currentLine = scanner.nextLine();
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                currentTaxe = unmarshallTaxes(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException(
                        "Could not load a product: " + e.getMessage());
            }
            taxe.add(currentTaxe);
        }
        // close scanner
        scanner.close();
        
    }
    
    private void loadAllOrders() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        orders = new ArrayList<>();
                
        List<File> filesInFolder = null;
        
        try (Stream<Path> filePathStream=Files.walk(Paths.get("./Orders"))) {
           filesInFolder = filePathStream.filter(Files::isRegularFile)
                          .map(Path::toFile)
                          .collect(Collectors.toList());
        } catch(IOException e){
            throw new FlooringMasteryPersistenceException("ERROR: can't load Order files");
        }
        
        for(File f : filesInFolder){
            
            String filename = f.getName();
            String strPattern = "\\d{2}\\d{2}\\d{4}";
            Pattern pattern = Pattern.compile(strPattern);
            Matcher matcher = pattern.matcher(filename);
            LocalDate dateFormat = null;
            while( matcher.find() ) {
                
                dateFormat = LocalDate.parse(matcher.group(), DateTimeFormatter.ofPattern("MMddyyyy"));
            }
            
            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(f.getPath())));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException("Could not open Order Files");
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
                    currentOrder.setOrderDate(dateFormat);
                } catch(NumberFormatException e){
                    throw new FlooringMasteryPersistenceException("Could not load an order: " + e.getMessage());
                }
                orders.add(currentOrder);
            }
            // close scanner
            scanner.close();
        }
    }
    
    private List<Order> loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        List<Order> orderDate = new ArrayList<>();
        
        String filename = getOrderFileName(date);
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(filename)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("There does not exit any orders on " + date);
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
    public void export() throws FlooringMasteryPersistenceException{
        loadAllOrders();
        
        PrintWriter out;

       try {
           out = new PrintWriter(new FileWriter(BACKUP_FILE));
       } catch (IOException e) {
           throw new FlooringMasteryPersistenceException(
                   "Could not save student data.", e);
       }

       
       String orderAsText;
       out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate");
       for (Order currentOrder : orders) {
           orderAsText = marshallOrder(currentOrder);
           
           out.println(orderAsText + DELIMITER + currentOrder.getOrderDate()
                            .format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          
           out.flush();
       }
       // Clean up
       out.close();
        
    }
}
