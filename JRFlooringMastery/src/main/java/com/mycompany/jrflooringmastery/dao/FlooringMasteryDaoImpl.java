
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
 * Data Access Object for the application
 * handles all operation realted to data
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
    
    /**
     * constructor with default file names
     */
    public FlooringMasteryDaoImpl() {
        this.BACKUP_FILE = "Backup/DataExport.txt";
        this.DATA_PRODUCTS_FILE = "Data/Products.txt";
        this.DATA_TAXES_FILE = "Data/Taxes.txt";
    }

    /**
     * constructor with user set filenames for testing purposes
     * @param BACKUP_FILE
     * @param DATA_PRODUCTS_FILE
     * @param DATA_TAXES_FILE
     */
    public FlooringMasteryDaoImpl(String BACKUP_FILE, String DATA_PRODUCTS_FILE, String DATA_TAXES_FILE) {
        this.BACKUP_FILE = BACKUP_FILE;
        this.DATA_PRODUCTS_FILE = DATA_PRODUCTS_FILE;
        this.DATA_TAXES_FILE = DATA_TAXES_FILE;
    }
    
    /**
     * Removing an order, 
     * get all order on the date of the order to remove
     * remove the order from the list
     * rewrite the file with the updated list
     * @param removededOrder order to remove
     * @throws FlooringMasteryPersistenceException if t
     */
    @Override
    public void removeOrder(Order removededOrder)throws FlooringMasteryPersistenceException, NoOrderException {
       //get filename and all otders in the file
        String filename = getOrderFileName(removededOrder.getOrderDate());   
        List<Order> ordersDate = loadOrders(removededOrder.getOrderDate());
        
        //get the int place in the list of the order to remove
        int i = 0;
        int chosen = -1;
        for(Order order : ordersDate){
            if(order.getOrderNumber() == removededOrder.getOrderNumber()){
                chosen = i;
            }
            i++;
        }
        
        //if order was found delete it
        if(chosen != -1){
            ordersDate.remove(chosen);
        }else
            //if the order can be found, throw error
            throw new NoOrderException("Could not remove Order " 
                    + removededOrder.getOrderNumber());
        
        //overwrite file with removed file
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

    /**
     * update an order in the file with given updated order
     * @param updatedOrder updated version of order 
     * @throws FlooringMasteryPersistenceException if there is an error while reading/writing to file
     */
    @Override
    public void editOrder(Order updatedOrder) throws FlooringMasteryPersistenceException, NoOrderException{
        
        //get filename and all orders in the file in the date of the order
        String filename = getOrderFileName(updatedOrder.getOrderDate());
        List<Order> ordersDate = loadOrders(updatedOrder.getOrderDate());
        
        //get the int place in the list of the order to remove
        int i = 0;
        int chosen = -1;
        for(Order order : ordersDate){
            if(order.getOrderNumber() == updatedOrder.getOrderNumber()){
                chosen = i;
            }
            i++;
        }
        //remove the matching order and upload the updated version
        if(chosen != -1){
            ordersDate.remove(chosen);
            ordersDate.add(updatedOrder);
        }else
            throw new NoOrderException("Could not update Order " 
                    + updatedOrder.getOrderNumber());
        
        
        //rewrite the file with updated order
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

    /**
     * save an order to a file
     * Append to file if file for date already exists
     * Create new file, add header and new order if it does not exist
     * @param order to save to  files
     * @throws FlooringMasteryPersistenceException if an error happens while saving to file
     */
    @Override
    public void addOrder(Order order) throws FlooringMasteryPersistenceException {
        
        PrintWriter out;
        
       //get filename to save order based on date of order
        String filename = getOrderFileName(order.getOrderDate());
        //get marshalled string of the order to save
        String orderAsText = marshallOrder(order);
        File f = new File(filename);
        
        try {
            //append if file already exits
            if(f.exists() && !f.isDirectory()){
                //append to file with keyword true
                out = new PrintWriter(new FileOutputStream(new File(filename), true));
            }
            //create new file and add header if it doesnt exist
            else{
                out = new PrintWriter(filename);
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            }
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not add order " 
                    + order.getOrderNumber() + "to the file: " + filename);
        }
        
        //print order into file
        out.println(orderAsText);
        out.flush();
        out.close();
        
    }
    
    /**
     * Get the current maximum order number of all orders
     * this the latest order number created
     * @return the maximum order number
     * @throws FlooringMasteryPersistenceException if the maximum number cannot be found
     */
    @Override
    public int getMaxOrderNumber() throws FlooringMasteryPersistenceException{
        
        //load all the orders from all files into a list
        loadAllOrders();
                
        try{
            //lambda to get maximum order number of all orders
            maxOrderNumber = orders.stream()
                .mapToInt(i -> i.getOrderNumber())
                .max().orElseThrow(NoSuchElementException::new);
        }catch(NoSuchElementException e){
            throw new FlooringMasteryPersistenceException("Couldn't get the order number");
        }

        return maxOrderNumber;
    }
    
    /**
     * Get an order based on the date and the order number
     * go through the list of orders 
     * @param date of the order
     * @param orderNumber of the order
     * @return the corresponding order
     * @throws NoOrderException if there does not exist a corresponding order
     * @throws FlooringMasteryPersistenceException if there is an error loading orders
     */
    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException, FlooringMasteryPersistenceException {
        List<Order> ordersDate = loadOrders(date);
        
        Order order = ordersDate.stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findAny()
                .orElse(null);
        
        if(order == null){
            throw new NoOrderException("Could not find the order " + orderNumber 
                    + " on " + date);
        }
        
        return order;
    }
    
    /**
     * Get all orders on a date
     * list of all orders on the given date
     * @param date to get orders for 
     * @return list of orders with same date
     * @throws FlooringMasteryPersistenceException if 
     */
    @Override
    public List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException, NoOrderException {

        //load all orders on the date
        List<Order> ordersDate = loadOrders(date);
        
        //if list is not null
        if(ordersDate != null){
            return ordersDate;
        }
        else{
            throw new NoOrderException(
                    "There does not exist orders on " +  date.toString());
        }
    }
    
    /**
     * Get all taxes information from the file
     * list of all taxes 
     * @return list of taxes from file 
     * @throws FlooringMasteryPersistenceException if there is not tax info
     */
    @Override
    public List<Taxes> getTaxes() throws FlooringMasteryPersistenceException {
        //load all taxe info
        loadTaxes();
        
        if(taxe != null){
            return taxe;
        }
        else{
            throw new FlooringMasteryPersistenceException(
                    "Could not load taxes information.");
        }
    }

    /**
     * Get all products information from the file
     * list of all products
     * @return list of all products in file
     * @throws FlooringMasteryPersistenceException of there is no product info
     */
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
    
    /**
     * Export all orders in different files into one backup file
     * @throws FlooringMasteryPersistenceException if there is an error rading/writing files
     */
    @Override
    public void export() throws FlooringMasteryPersistenceException{
        //get all the orders in the file
        loadAllOrders();
        
        //write a backup file with all orders
        PrintWriter out;

       try {
           //overwrite file
           out = new PrintWriter(new FileWriter(BACKUP_FILE));
       } catch (IOException e) {
           throw new FlooringMasteryPersistenceException(
                   "Could not save student data.", e);
       }

       
       String orderAsText;
       //add header
       out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate");
       //add all orders
       for (Order currentOrder : orders) {
           orderAsText = marshallOrder(currentOrder);
           
           //add order along with date formatted to "MM-dd-yyyy";
           out.println(orderAsText + DELIMITER + currentOrder.getOrderDate()
                            .format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
          
           out.flush();
       }
       // Clean up
       out.close();
        
    }
    
    /**
     * get order filename with a given date
     * format: Orders_MMddyyyy.txt
     * in the directory Orders/
     * use date.format to create string of filename
     * @param date to create filename with
     * @return filename
     */
    private String getOrderFileName(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        String filename = "Orders/Orders_" + date.format(formatter) + ".txt";
        
        return filename;
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
        
        //create order object and add order number
        Order orderFromFile = new Order(customerName, state, TaxRate, ProductType, 
                Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, 
                LaborCost, Tax, Total);
        orderFromFile.setOrderNumber(orderNumber);

       
        return orderFromFile;
    }

    /**
     * unmarshall Product text string
     * Create Product object with string information
     * "<ProductType>,<CostPerSquareFoot>,<LaborCostPerSquareFoot>"
      
     * @param productAsText string to converst to Product object
     * @return Product object
     */
    private Product unmarshallProduct(String productAsText){
       
        //split string and decompose to the different variables 
        String[] productTokens = productAsText.split(DELIMITER);

        String productType = productTokens[0];
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        BigDecimal LaborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        
        //create product object
        Product product = new Product(productType, costPerSquareFoot, LaborCostPerSquareFoot);
       
        return product;
    }
    
    /**
     * unmarshall Taxes text string
     * Create Taxes object with string information
     * "<State>,<StateName>,<TaxRate>"
      
     * @param taxesAsText string to convert to Taxes object
     * @return Taxes object
     */
    private Taxes unmarshallTaxes(String taxesAsText){
       
        //split string and decompose to the different variables 
        String[] taxesTokens = taxesAsText.split(DELIMITER);

        String state = taxesTokens[0];
        String StateName = taxesTokens[1];
        BigDecimal taxRate = new BigDecimal(taxesTokens[2]);
        
        //create Taxes object
        Taxes tax = new Taxes(state, StateName, taxRate);

        return tax;
    }
    
    /**
     * Marshall an order object to a string to write to file
     * @param order to marshall into a string
     * @return string of order 
     */
    private String marshallOrder(Order order){
       
        //create string with variables
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
    
    /**
     * load all products from the products file in the list of products
     * @throws FlooringMasteryPersistenceException if there is an error 
     * unmarshalling a product or reading the file
     */
    private void loadProducts() throws FlooringMasteryPersistenceException {
        
        //read file
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
        
        //go through every line of the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                //create a product with current line
                currentProduct = unmarshallProduct(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException(
                        "Could not load a product: " + e.getMessage());
            }
            //add product to list
            product.add(currentProduct);
        }
        // close scanner
        scanner.close();
    }

    /**
     * load all taxes from file from file
     * @throws FlooringMasteryPersistenceException if there is an error 
     * unmarshalling a taxe object or reading the file
     * 
     */
    private void loadTaxes() throws FlooringMasteryPersistenceException {
        
        //read file
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
        //read every line
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                currentTaxe = unmarshallTaxes(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException(
                        "Could not load a product: " + e.getMessage());
            }
            //add tax to file
            taxe.add(currentTaxe);
        }
        // close scanner
        scanner.close();
        
    }
    
    /**
     * load all the orders from all the files in the Orders folder in the list
     * @throws FlooringMasteryPersistenceException if there is an error 
     * unmarshalling a order object or reading the file
     */
    private void loadAllOrders() throws FlooringMasteryPersistenceException {
        
        Scanner scanner;
        
        orders = new ArrayList<>();
                
        List<File> filesInFolder = null;
        
        //get list of files in the Orders folder
        try (Stream<Path> filePathStream=Files.walk(Paths.get("./Orders"))) {
           filesInFolder = filePathStream.filter(Files::isRegularFile)
                          .map(Path::toFile)
                          .collect(Collectors.toList());
        } catch(IOException e){
            throw new FlooringMasteryPersistenceException("ERROR: can't load Order files");
        }
        
        //read every single file in the folder
        for(File f : filesInFolder){
            
            //get date of orders in current file
            String filename = f.getName();
            String strPattern = "\\d{2}\\d{2}\\d{4}";
            Pattern pattern = Pattern.compile(strPattern);
            Matcher matcher = pattern.matcher(filename);
            LocalDate dateFormat = null;
            while( matcher.find() ) {
                
                dateFormat = LocalDate.parse(matcher.group(), DateTimeFormatter.ofPattern("MMddyyyy"));
            }
            
            //read file
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
            //read every order in file
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                try{
                    currentOrder = unmarshallOrder(currentLine);
                    currentOrder.setOrderDate(dateFormat);
                } catch(NumberFormatException e){
                    throw new FlooringMasteryPersistenceException("Could not load an order: " + e.getMessage());
                }
                //add order to list
                orders.add(currentOrder);
            }
            // close scanner
            scanner.close();
        }
    }
    
    /**
     * load all orders on the given date
     * finds the file with matching date
     * @param date to find orders for
     * @return list of orders on the date
     * @throws FlooringMasteryPersistenceException if there is an error 
     * unmarshalling a order object or reading the file
     */
    private List<Order> loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        List<Order> orderDate = new ArrayList<>();
        
        String filename = getOrderFileName(date);
        
        //open file with date given
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
        //read every order in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            try{
                currentOrder = unmarshallOrder(currentLine);
            } catch(NumberFormatException e){
                throw new FlooringMasteryPersistenceException("Could not load an order: " + e.getMessage());
            }
            //add order to list
            orderDate.add(currentOrder);
        }
        // close scanner
        scanner.close();
        
        return orderDate;
    }
}
