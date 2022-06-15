
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryAuditDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void createOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Order getOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeOrder() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void exportData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Order> getAllOrders(LocalDate date) throws NoOrderException, FlooringMasteryPersistenceException{
        return (ArrayList<Order>) dao.getOrders(date);
    }
    
    
}
