
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Jasen Ratnam
 */
public interface FlooringMasteryServiceLayer {
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException, FlooringMasteryPersistenceException;
    
    public void removeOrder(LocalDate date, Order updatedOrder) throws NoOrderException, FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
    
    public void exportData() throws FlooringMasteryPersistenceException;

    public ArrayList<Order> getAllOrders(LocalDate date) throws NoOrderException, FlooringMasteryPersistenceException;

    public ArrayList<Taxes> getTaxes() throws FlooringMasteryPersistenceException;

    public ArrayList<Product> getProducts() throws FlooringMasteryPersistenceException;

    public void createOrder(Order newOrder) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException;

    public void updateOrder(LocalDate date, Order updatedOrder) throws NoOrderException, FlooringMasteryPersistenceException, FlooringMasteryDataValidationException;
    
}
