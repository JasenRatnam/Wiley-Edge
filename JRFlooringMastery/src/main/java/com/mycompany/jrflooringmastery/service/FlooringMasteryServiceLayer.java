
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jasen Ratnam
 */
public interface FlooringMasteryServiceLayer {

    public void createOrder();
    public Order getOrder();
    public void removeOrder();
    public void exportData();

    public ArrayList<Order> getAllOrders(LocalDate date) throws NoOrderException, FlooringMasteryPersistenceException;
    
}
