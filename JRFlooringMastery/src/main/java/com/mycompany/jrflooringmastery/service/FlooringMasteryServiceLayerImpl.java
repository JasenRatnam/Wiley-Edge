
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryAuditDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * service layer of application
 * @author Jasen Ratnam
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{

    private FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    /**
     * remove an order from files
     * add date to order and send order to DAO to be removed
     * @param date date of order to remove
     * @param removededOrder order to remove
     * @throws NoOrderException if the order cannot be found
     * @throws FlooringMasteryPersistenceException if the files cannot be read/written to
     * @throws FlooringMasteryDataValidationException  if the order given is not valid format
     */
    @Override
    public void removeOrder(LocalDate date, Order removededOrder) throws NoOrderException, FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        removededOrder.setOrderDate(date);
        validateOrderData(removededOrder);
        
        dao.removeOrder(removededOrder);
        
    }

    /**
     * export all orders in the folder to one backup file
     * pass-trough to dao
     * @throws FlooringMasteryPersistenceException if the files cannot be read/written to
     */
    @Override
    public void exportData() throws FlooringMasteryPersistenceException {
        dao.export();
    }
    
    /**
     * get an order from a date and order number
     * pass-trough to dao
     * @param date of order
     * @param orderNumber of order
     * @return the Order
     * @throws NoOrderException if a matching order doesnt exist
     * @throws FlooringMasteryPersistenceException  an error happens file dealing with files
     */
    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException ,FlooringMasteryPersistenceException{
        return dao.getOrder(date, orderNumber);
    }
    
    /**
     * update an order on the date given with an updated order object
     * @param date of order
     * @param updatedOrder to update to 
     * @throws NoOrderException if the order could not be found
     * @throws FlooringMasteryPersistenceException if there is an error reading and wrting to file
     * @throws FlooringMasteryDataValidationException if the updated order is not valid
     */
    @Override
    public void updateOrder(LocalDate date, Order updatedOrder) throws NoOrderException, FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        updatedOrder.setOrderDate(date);
        validateOrderData(updatedOrder);
        
        dao.editOrder(updatedOrder);
        
    }
    
    /**
     * get all orders on a date
     * @param date to get orders for 
     * @return all order on the date
     * @throws NoOrderException if no order exists
     * @throws FlooringMasteryPersistenceException if there is an error reading and wrting to file
     */
    @Override
    public ArrayList<Order> getAllOrders(LocalDate date) throws NoOrderException, FlooringMasteryPersistenceException{
        auditDao.writeAuditEntry("orders on: " +  date + " gotten.");
        return (ArrayList<Order>) dao.getOrders(date);
    }

    /**
     * get list of all taxes object
     * @return list of taxes
     * @throws FlooringMasteryPersistenceException if there is an error reading and wrting to file
     */
    @Override
    public ArrayList<Taxes> getTaxes() throws FlooringMasteryPersistenceException{
        auditDao.writeAuditEntry("Taxes has bean loaded");
        
        return (ArrayList<Taxes>) dao.getTaxes();
    }

    /**
     * get list of products
     * @return list of products
     * @throws FlooringMasteryPersistenceException if there is an error reading to file 
     */
    @Override
    public ArrayList<Product> getProducts() throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry("Products has been loaded");
        return (ArrayList<Product>) dao.getProducts();
    }

    /**
     * add the order object created to the files
     * validate the order
     * get the next order number and assign it to the new order
     * use dao to add the order to file
     * @param newOrder to add
     * @throws FlooringMasteryDataValidationException if order is not valid
     * @throws FlooringMasteryPersistenceException  if there is an error reading and writing to file
     */
    @Override
    public void createOrder(Order newOrder) throws FlooringMasteryDataValidationException, 
            FlooringMasteryPersistenceException{
       
        validateOrderData(newOrder);
        
        int orderNumber = dao.getMaxOrderNumber()+1;
        
        newOrder.setOrderNumber(orderNumber);
        
        dao.addOrder(newOrder);
        
        auditDao.writeAuditEntry("Order " + orderNumber + "CREATED.");
        
    }
    
    /**
     * validate an order object
     * @param order to validate
     * @throws FlooringMasteryDataValidationException if the order is not valid
     */
    private void validateOrderData(Order order) throws
            FlooringMasteryDataValidationException {
        if (order.getCustomerName()== null
                || order.getCustomerName().trim().length() == 0
                || order.getState()== null
                || order.getState().trim().length() == 0
                || order.getTaxRate() == null
                || order.getProductType() == null
                || order.getProductType().trim().length() == 0
                || order.getArea() == null
                || order.getCostPerSquareFoot() == null
                || order.getLaborCostPerSquareFoot() == null
                || order.getMaterialCost() == null
                || order.getLaborCost() == null
                || order.getTax() == null
                || order.getTotal() == null) {

            throw new FlooringMasteryDataValidationException(
                    "ERROR: All fields of order are required.");
        }
    }
}
