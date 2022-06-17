
package com.mycompany.jrflooringmastery.dao;

import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import com.mycompany.jrflooringmastery.service.NoOrderException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface FlooringMasteryDao {

    public List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException;
    public void addOrder(Order order) throws FlooringMasteryPersistenceException;
    public int getMaxOrderNumber() throws FlooringMasteryPersistenceException;
    public void editOrder(Order updatedOrder) throws FlooringMasteryPersistenceException; 
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException, FlooringMasteryPersistenceException; 
    public void removeOrder(Order removededOrder)throws FlooringMasteryPersistenceException; 
    
    public List<Taxes> getTaxes() throws FlooringMasteryPersistenceException;

    public List<Product> getProducts() throws FlooringMasteryPersistenceException;

    public void export() throws FlooringMasteryPersistenceException;

}
