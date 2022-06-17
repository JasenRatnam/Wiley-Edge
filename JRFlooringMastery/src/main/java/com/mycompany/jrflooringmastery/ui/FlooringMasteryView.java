
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
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryView {

    //use the IO class to communicate with user
    private UserIO io;

    public FlooringMasteryView(UserIO myIo) {
        this.io = myIo;
    }

    //menu
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
    
    //display exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    //display exit banner
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.readString(errorMsg + ". Please press enter to continue.");
    }

    //display unkown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayOrdersBanner() {
        io.print("=== Orders For Given Date ===");
    }
    
    public void displayOrders(ArrayList<Order> orders) {
        io.print("=== Orders: ===");
        orders.stream()
             .map((i) -> i.toString())
             .forEach((i) -> io.print(i));
    }

    public void displayAddOrderBanner() {
        io.print("=== Add an Order ===");
    }

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

    public Order getNewOrderInformation(List<Taxes> taxes, List<Product> products) throws FlooringMasteryDataValidationException {
        boolean valid = false;
        BigDecimal area = BigDecimal.ZERO;
        LocalDate date = null;
        
        io.print("=== Enter Information about Order ===");
        
        valid = false;
        while(!valid){
            String dateString = io.readString("Please enter the Order date (Must be in the future)");
           
            try{
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if(date.isAfter(LocalDate.now()))
                    valid = true;
                else
                    io.readString("The order date must be in the future. Press enter and try again.");
            } catch(Exception e){
                io.readString(dateString + " is of invalid format." 
                        + "\n Please enter a date of the following format: dd/MM/yyyy" 
                        + "Press enter to continue.");
            }
        }
        
        String customerName = "";
        while(customerName.equals("")){
            customerName = io.readString("Please enter the Customer Name");
        }
     
        String stateGiven = io.readString("Please enter the State abbreviation (SS)");
        BigDecimal taxRate = BigDecimal.ZERO;
        String state = null;
        valid = false;
        for(Taxes tax : taxes){
            if(tax.getState().equals(stateGiven)){
               valid = true; 
               taxRate = tax.getTaxRate();
               state = tax.getState();
               break;
            }
        }
        if(!valid)
            throw new FlooringMasteryDataValidationException("We cannot sell in " 
                    + stateGiven + " yet");
        
        AtomicInteger j = new AtomicInteger(1);        
        
        products.stream()
                .map((i) -> i.getProductType())
                .forEach((i) -> io.print(j.getAndIncrement() + ". " + i));
           
        int productType = io.readInt("Please enter the Product Type",1,j.intValue()-1);
        
        Product producTypeChosen = products.get(productType-1);
        String productTypeString = producTypeChosen.getProductType();
        
        valid = false;
        while(!valid){
            String areaString = io.readString("Please enter Area");
            if(isNumeric(areaString)) 
                area = new BigDecimal(areaString);
            if(area.compareTo( new BigDecimal("100")) < 0){
                io.readString("You must enter a number bigger than or equal to 100. Please hit enter to continue");
            }else{
                valid = true;
                area.setScale(2, RoundingMode.HALF_UP);
            }
        }
        
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
        
        Order order = new Order(customerName, state, taxRate, productTypeString, 
                area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, 
                laborCost, tax, total);
        
        order.setOrderDate(date);
        return order;
    }

    public boolean confirmOrder(Order newOrder) {
                
        io.print("Here is a summary of the order:");
        io.print(newOrder.toString());
        
        while(true){
            String input = io.readString("Do you want to confirm this order? (Y/N)");
            if(input.equals("Y")){
                return true;
            } else if(input.equals("N")){
                return false;
            }
            else
                io.print("Invalid input: \nEnter Y if you want to place the order."
                        + "\nEnter N if you don't want to place the order\n");
        }
    }
    
    /**
     * make sure a string contains numbers
     * @param str
     * @return 
     */
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void displayEditOrderBanner() {
        io.print("=== Edit an Order ===");
    }

    public int getOrderNumber() {
        return io.readInt("What is the order number of the order you want?");
        
    }

    public Order getUpdatedOrder(Order order, List<Taxes> taxes, List<Product> products) throws FlooringMasteryDataValidationException {
        boolean valid = false;
        boolean changed = false;
        BigDecimal area = BigDecimal.ZERO;
        
        io.print("=== Enter new Information about Order " + order.getOrderNumber() 
                + " ===");
        
        String customerName = "";
        customerName = io.readString("Enter customer name (" + order.getCustomerName() + "):");
        
        if(!customerName.equals("")){
            order.setCustomerName(customerName);
            changed = true;
        }
     
        String stateGiven = io.readString("Please enter the State abbreviation (" + order.getState() + ")");
        if(!stateGiven.equals("")){
            BigDecimal taxRate = BigDecimal.ZERO;
            String state = null;
            for(Taxes tax : taxes){
                if(tax.getState().equals(stateGiven)){
                   valid = true; 
                   order.setTaxRate(tax.getTaxRate());
                   order.setState(tax.getState());
                   changed = true;
                   break;
                }
            }
        }
        if(!valid)
            throw new FlooringMasteryDataValidationException("We cannot sell in " 
                    + stateGiven + " yet");
        valid = false;
        
        AtomicInteger j = new AtomicInteger(1);        
        
        products.stream()
                .map((i) -> i.getProductType())
                .forEach((i) -> io.print(j.getAndIncrement() + ". " + i));
           
        int productType = io.readInt("Please enter the Product Type (" + order.getProductType() +"):",1,j.intValue()-1);
        
        Product producTypeChosen = products.get(productType-1);
        String productTypeString = producTypeChosen.getProductType();
        
        if(!productTypeString.equals(order.getProductType())){
            order.setProductType(productTypeString);
            changed = true;
        }
        
        valid = false;
        while(!valid){
            String areaString = io.readString("Please enter Area (" + order.getArea() +"):");
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
        
        if(changed){
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

    public void displayRemoveOrderBanner() {
        io.print("=== Remove an Order ===");
    }

    public void displayExportOrdersBanner() {
        io.print("=== Export Orders ===");
    }

    public void displayExportOrderSuccessBanner() {
        io.readString("=== Export Complete ===, Please press enter to continue.");
    }

}
