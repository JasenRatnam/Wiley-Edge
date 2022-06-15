
package com.mycompany.jrflooringmastery.dao;

import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.service.FlooringMasteryDataValidationException;
import com.mycompany.jrflooringmastery.service.NoOrderException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface FlooringMasteryDao {

    public List<Order> getOrders(LocalDate date) throws NoOrderException, FlooringMasteryPersistenceException;
    public void addOrder(Order order) throws FlooringMasteryDataValidationException;
    public int getNextOrderNumber();
    public void editOrder(Order originalOrder, Order updatedOrder) throws NoOrderException, 
            FlooringMasteryDataValidationException; 
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException; 
    public Order removeOrder(LocalDate date, int orderNumber) throws NoOrderException; 
    
    public void loadProducts() throws FlooringMasteryPersistenceException;
    public void loadTaxes() throws FlooringMasteryPersistenceException;
    public List<Order> loadOrders(LocalDate date) throws FlooringMasteryPersistenceException;
    public void saveAllOrders() throws FlooringMasteryPersistenceException;
}
