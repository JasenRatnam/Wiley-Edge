
package com.mycompany.jrflooringmastery.ui;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import com.mycompany.jrflooringmastery.service.FlooringMasteryDataValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * control all ui with user
 * @author Jasen Ratnam
 */
public class FlooringMasteryView {

    //use the IO class to communicate with user
    private UserIO io;

    /**
     * constructor for view
     * @param myIo 
     */
    public FlooringMasteryView(UserIO myIo) {
        this.io = myIo;
    }

    /**
     * menu to prompt the user for what they would like to do
     * @return user choice
     */
    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* Main Menu");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");


        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    /**
     * display exit banner
    **/
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    /**
     * display error banner with error message
     * press enter to continue
    **/
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.readString(errorMsg + ". Please press enter to continue.");
    }

    /**
     * display unknown command banner
     **/
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    /**
     * display banner to display orders on a certain date
     */
    public void displayOrdersBanner() {
        io.print("=== Orders For Given Date ===");
    }
    
    /**
     * display remove order banner
     */
    public void displayRemoveOrderBanner() {
        io.print("=== Remove an Order ===");
    }

    /**
     * display export data banner
     */
    public void displayExportOrdersBanner() {
        io.print("=== Export Orders ===");
    }

    /**
     * display export data has completed
     */
    public void displayExportOrderSuccessBanner() {
        io.readString("=== Export Complete ===\nPlease press enter to continue.");
    }
    
    /**
     * display add an order banner
     */
    public void displayAddOrderBanner() {
        io.print("=== Add an Order ===");
    }
    
    /**
     * display edit an order banner
     */
    public void displayEditOrderBanner() {
        io.print("=== Edit an Order ===");
    }
    
    /**
     * display all orders given in a arrayList
     * use lambdas and Stream
     * @param orders to display to user
     */
    public void displayOrders(ArrayList<Order> orders) {
        io.print("=== Orders: ===");
        orders.stream()
             .map((i) -> i.toString())
             .forEach((i) -> io.print(i));
    }

    /**
     * get the order number from the user
     * used to find an order
     * @return the order number user wants
     */
    public int getOrderNumber() {
        return io.readInt("What is the order number of the order you want?");
        
    }

    /**
     * Get an order date from the user
     * Date must be given in the "dd/MM/yyyy" format
     * @return the date given by the user (LocalDate)
     * @throws FlooringMasteryPersistenceException for invalid date format
     */
    public LocalDate getOrderDate() throws FlooringMasteryPersistenceException {
        String date = io.readString("What is the date of order you want? (dd/MM/yyyy)");
        
        LocalDate dateFormat = null;
        try{
            dateFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch(Exception e){
            throw new FlooringMasteryPersistenceException(date + " is of invalid format." 
                    + "\nPlease enter a date of the following format: dd/MM/yyyy. ");
        }
        
        return dateFormat;
    }

    /**
     * Get a new order from the user
     * prompt the user for new order information
     * Prompt for:
     * Order Date
     *      Keep prompting until date entered is in the correct format
     *      Ensure date is in the future
     * Customer Name
     *      May not blank
     *      Allowed to contain [a-z][0-9] as well as periods and comma characters 
     * State
     *     Entered states must be checked against the tax file. 
     *     If the state does not exist in the tax file we cannot sell there. 
     *     Throw FlooringMasteryDataValidationException if cannot sell and go 
     *     back to main menu
     * Product Type
     *     Show a list of available products and pricing information to choose from.
     *     Get user choice as int
     *     Keep prompting until valid answer 
     * Area 
           Must be a positive decimal. 
           Minimum order size is 100 sq ft.
           Keep prompting until valid area is entered
     * The remaining fields are calculated from the user entry and the 
     * tax/product information in the files. 
     * 
     * Create a Order object with user entry and return it
     * @param taxes taxes information for the business
     * @param products product information for the business
     * @return the new Order created
     * @throws FlooringMasteryDataValidationException if the user entry is invalid
     */
    public Order getNewOrderInformation(List<Taxes> taxes, List<Product> products) throws FlooringMasteryDataValidationException {
        boolean valid = false;
        BigDecimal area = BigDecimal.ZERO;
        LocalDate date = null;
        
        io.print("=== Enter Information about Order ===");
        
        //get order date in format: dd/MM/yyyy
        valid = false;
        while(!valid){
            String dateString = io.readString("Please enter the Order date (Must be in the future)");
           
            try{
                //get localdate from user entry
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                //verify that date is in the future
                if(date.isAfter(LocalDate.now()))
                    valid = true;
                else
                    //keep prompting until valid date
                    displayErrorMessage("The order date must be in the future");
            } catch(Exception e){
                displayErrorMessage(dateString + " is of invalid format." 
                        + "\n Please enter a date of the following format: dd/MM/yyyy" 
                        );
            }
        }
        
        //get customer name
        //make sure it is not blank
        String customerName = "";
        while(customerName.equals("")){
            customerName = io.readString("Please enter the Customer Name");
            if(customerName == null || customerName.trim().isEmpty())
                customerName = "";
        }
     
        //get state abreavation
        String stateGiven = io.readString("Please enter the State abbreviation (SS)");
        BigDecimal taxRate = BigDecimal.ZERO;
        String state = null;
        valid = false;
        
        //get corresponding tax information for state given
        for(Taxes tax : taxes){
            if(tax.getState().equals(stateGiven)){
               valid = true; 
               taxRate = tax.getTaxRate();
               state = tax.getState();
               break;
            }
        }
        //if cant find state
        if(!valid)
            throw new FlooringMasteryDataValidationException("We cannot sell in " 
                    + stateGiven + " yet");
        
        //display products to choose from
        AtomicInteger j = new AtomicInteger(1);        
        products.stream()
                .map((i) -> i.toString())
                .forEach((i) -> io.print(j.getAndIncrement() + ". " + i));
        //get product choice
        int productType = io.readInt("Please enter the Product Type",1,j.intValue()-1);
        
        //save product chosen and its name
        Product producTypeChosen = products.get(productType-1);
        String productTypeString = producTypeChosen.getProductType();
        
        //get area of order, keep prompting until valid area is givne
        valid = false;
        while(!valid){
            String areaString = io.readString("Please enter Area");
            if(isNumeric(areaString)) 
                area = new BigDecimal(areaString);
            if(area.compareTo( new BigDecimal("100")) < 0){
                //make sure area is valid
                displayErrorMessage("You must enter a number bigger than or equal to 100");
            }else{
                valid = true;
                area.setScale(2, RoundingMode.HALF_UP);
            }
        }
        
        //calculate remaining fields
        BigDecimal costPerSquareFoot = producTypeChosen.getCostPerSquareFoot();
        costPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal laborCostPerSquareFoot  = producTypeChosen.getLaborCostPerSquareFoot();
        laborCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
       
        BigDecimal materialCost = area.multiply(costPerSquareFoot);
        materialCost.setScale(2, RoundingMode.HALF_UP);
       
        BigDecimal laborCost  = area.multiply(laborCostPerSquareFoot);
        laborCost.setScale(2, RoundingMode.HALF_UP);
     
        BigDecimal tax = (materialCost.add(laborCost)).multiply(taxRate.divide(new BigDecimal("100")));
        tax.setScale(2, RoundingMode.HALF_UP);
       
        BigDecimal total = materialCost.add(laborCost).add(tax);
        total.setScale(2, RoundingMode.HALF_UP);
        
        //create order object
        Order order = new Order(customerName, state, taxRate, productTypeString, 
                area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, 
                laborCost, tax, total);
        
        //set the date of the order 
        order.setOrderDate(date);
        return order;
    }

    /**
     * get confirmation of an order from user
     * display order to user
     * get user choice and return it
     * Keep prompting until valid input
     * @param newOrder
     * @return 
     */
    public boolean confirmOrder(Order newOrder) {
                
        //display order
        io.print("Here is a summary of the order:");
        io.print(newOrder.toString());
        
        //get valid user input
        while(true){
            String input = io.readString("Is this the order you want? (Y/N)");
            if(input.equals("Y")){
                return true;
            } else if(input.equals("N")){
                return false;
            }
            else
                displayErrorMessage("Invalid input: \nEnter Y if you want to place the order."
                        + "\nEnter N if you don't want to place the order");
        }
    }
    
    /**
     * Ask the user for each piece of order data but display the existing data. 
     * If the user enters something new, it will replace that data; 
     * if the user hits Enter without entering data, it will leave the existing data in place
     * Data allowed to change:
     *  CustomerName
     *  State
     *  ProductType
     *  Area
     * If the state, product type, or area are changed, 
     *      the order will need to be recalculated. 
     * @param order to update
     * @param taxes taxes to verify state information
     * @param products to display to user
     * @return the updated order
     * @throws FlooringMasteryDataValidationException for invalid data format
     */
    public Order getUpdatedOrder(Order order, List<Taxes> taxes, List<Product> products) throws FlooringMasteryDataValidationException {
        boolean valid = false;
        boolean changed = false;
        BigDecimal area = BigDecimal.ZERO;
        
        io.print("=== Enter new Information about Order " + order.getOrderNumber() 
                + " ===");
        
        //get new customer name 
        String customerName = "";
        customerName = io.readString("Enter customer name (" + order.getCustomerName() + "):");
        
        //if something new, update
        if(!customerName.equals("")){
            if(customerName != null || !customerName.trim().isEmpty())
            {
                order.setCustomerName(customerName);
                changed = true;
            }
        }
     
        //get state to update
        String stateGiven = io.readString("Please enter the State abbreviation (" + order.getState() + ")");
        
        //if new state, verify state in taxes file
        if(!stateGiven.equals("")){
            if(customerName != null || !customerName.trim().isEmpty())
            {
                BigDecimal taxRate = BigDecimal.ZERO;
                String state = null;
                for(Taxes tax : taxes){
                    //if state exists
                    if(tax.getState().equals(stateGiven)){

                        //update taxrate and state of order
                       valid = true; 
                       order.setTaxRate(tax.getTaxRate());
                       order.setState(tax.getState());
                       changed = true;
                       break;
                    }
                }
            }
        }else{
            valid = true;
        }
        //if state is invalid, throw error and return to main menu
        if(!valid)
            throw new FlooringMasteryDataValidationException("We cannot sell in " 
                    + stateGiven + " yet");
        valid = false;
        
        //display products
        AtomicInteger j = new AtomicInteger(1);        
        
        products.stream()
                .map((i) -> i.getProductType())
                .forEach((i) -> io.print(j.getAndIncrement() + ". " + i));
           
        //get product choice
        int productType = io.readInt("Please enter the Product Type (" + order.getProductType() +"):",1,j.intValue()-1);
        
        //get product chosen
        Product producTypeChosen = products.get(productType-1);
        String productTypeString = producTypeChosen.getProductType();
        
        //update if new choise
        if(!productTypeString.equals(order.getProductType())){
            order.setProductType(productTypeString);
            changed = true;
        }
        
        //update order area 
        valid = false;
        while(!valid){
            String areaString = io.readString("Please enter Area (" + order.getArea() +"):");
            
            //ensure new area information is valid
            //keep prompting until valid area given
            if(!areaString.equals("")){
                if(isNumeric(areaString)) 
                    area = new BigDecimal(areaString);
                if(area.compareTo( new BigDecimal("100")) < 0){
                    io.readString("You must enter a number bigger than or equal to 100. Please hit enter to continue");
                }else{
                    valid = true;
                    area.setScale(2, RoundingMode.HALF_UP);
                    order.setArea(area);
                    changed = true;
                }
            }
            else{
                valid = true;
            }
        }
        valid = false;
        
        //If the state, product type, or area are changed, 
        if(changed){
            
            //recalculate the Order
            
            BigDecimal costPerSquareFoot = producTypeChosen.getCostPerSquareFoot();
            costPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
            order.setCostPerSquareFoot(costPerSquareFoot);
            
            BigDecimal laborCostPerSquareFoot  = producTypeChosen.getLaborCostPerSquareFoot();
            laborCostPerSquareFoot.setScale(2, RoundingMode.HALF_UP);
            order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
            
            BigDecimal materialCost = order.getArea().multiply(costPerSquareFoot);
            materialCost.setScale(2, RoundingMode.HALF_UP);
            order.setMaterialCost(materialCost);
            
            BigDecimal laborCost  = order.getArea().multiply(laborCostPerSquareFoot);
            laborCost.setScale(2, RoundingMode.HALF_UP);
            order.setLaborCost(laborCost);
            
            BigDecimal tax = (materialCost.add(laborCost)).multiply(order.getTaxRate().divide(new BigDecimal("100")));
            tax.setScale(2, RoundingMode.HALF_UP);
            order.setTax(tax);
            
            BigDecimal total = materialCost.add(laborCost).add(tax);
            total.setScale(2, RoundingMode.HALF_UP);
            order.setTotal(total);
        }
        return order;
    }
    
    /**
     * make sure a string contains numbers
     * @param str
     * @return 
     */
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
