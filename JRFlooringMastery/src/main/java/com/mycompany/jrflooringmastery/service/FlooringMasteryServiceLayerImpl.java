
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
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{

    private FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public void removeOrder(LocalDate date, Order removededOrder) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        removededOrder.setOrderDate(date);
        validateOrderData(removededOrder);
        
        dao.removeOrder(removededOrder);
        
    }

    @Override
    public void exportData() throws FlooringMasteryPersistenceException {
        dao.export();
    }
    
    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException ,FlooringMasteryPersistenceException{
        return dao.getOrder(date, orderNumber);
    }
    
    @Override
    public void updateOrder(LocalDate date, Order updatedOrder) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {
        updatedOrder.setOrderDate(date);
        validateOrderData(updatedOrder);
        
        dao.editOrder(updatedOrder);
        
    }
    
    @Override
    public ArrayList<Order> getAllOrders(LocalDate date) throws FlooringMasteryPersistenceException{
        auditDao.writeAuditEntry("orders on: " +  date + " gotten.");
        return (ArrayList<Order>) dao.getOrders(date);
    }

    @Override
    public ArrayList<Taxes> getTaxes() throws FlooringMasteryPersistenceException{
        auditDao.writeAuditEntry("Taxes has bean loaded");
        
        return (ArrayList<Taxes>) dao.getTaxes();
    }

    @Override
    public ArrayList<Product> getProducts() throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry("Products has been products");
        return (ArrayList<Product>) dao.getProducts();
    }

    @Override
    public void createOrder(Order newOrder) throws FlooringMasteryDataValidationException, 
            FlooringMasteryPersistenceException{
       
        validateOrderData(newOrder);
        
        int orderNumber = dao.getMaxOrderNumber()+1;
        
        newOrder.setOrderNumber(orderNumber);
        
        dao.addOrder(newOrder);
        
        auditDao.writeAuditEntry("Order " + orderNumber + "CREATED.");
        
    }
    
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
