
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.jrflooringmastery.dto.Order;
import com.mycompany.jrflooringmastery.dto.Product;
import com.mycompany.jrflooringmastery.dto.Taxes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.datetime.joda.LocalDateParser;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao{

    public List<Order> orders = new ArrayList();

    public FlooringMasteryDaoStubImpl() {
        Order onlyOrder = new Order("Bob", 
                "TX", 
                new BigDecimal("4.45"), 
                "Tile", 
                new BigDecimal("174"), 
                new BigDecimal("3.5"), 
                new BigDecimal("4.15"), 
                new BigDecimal("609"), 
                new BigDecimal("722.10"), 
                new BigDecimal("59.23"), 
                new BigDecimal("1390.33"));
        onlyOrder.setOrderDate(LocalDate.of(2022, Month.JUNE, 19));
        onlyOrder.setOrderNumber(1);
        
        orders.add(onlyOrder);
        
    }

    public FlooringMasteryDaoStubImpl(Order testOrder){
         orders.add(testOrder);
    }
    
    @Override
    public List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException, NoOrderException {
       
       return orders;
    }

    @Override
    public void addOrder(Order order) throws FlooringMasteryPersistenceException {
        
        orders.add(order);
    }

    @Override
    public int getMaxOrderNumber() throws FlooringMasteryPersistenceException {
        return orders.size();
    }

    @Override
    public void editOrder(Order updatedOrder) throws FlooringMasteryPersistenceException, NoOrderException {
        int i = 0;
        int choice = 0;
        for(Order order : orders){
            if(order.getOrderNumber() == updatedOrder.getOrderNumber()){
                choice = i;
                break;
            }
            i++;
        }
        orders.remove(choice);
        orders.add(updatedOrder);
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws NoOrderException, FlooringMasteryPersistenceException {
        for(Order order : orders){
            if(order.getOrderNumber() == orderNumber){
                    return order;
                
            }
        }
        return null;
    }

    @Override
    public void removeOrder(Order removededOrder) throws FlooringMasteryPersistenceException, NoOrderException {
        int i = 0;
        int choice = 0;
        for(Order order : orders){
            if(order.getOrderNumber() == removededOrder.getOrderNumber()){
                choice = i;
                break;
            }
            i++;
        }
        orders.remove(choice);
    }

    @Override
    public List<Taxes> getTaxes() throws FlooringMasteryPersistenceException {
        Taxes tax1 = new Taxes("TX", "Texas", new BigDecimal("4.45").setScale(2, RoundingMode.HALF_UP));
        Taxes tax2 = new Taxes("WA", "Washington", new BigDecimal("9.25").setScale(2, RoundingMode.HALF_UP));
        Taxes tax3 = new Taxes("KY", "Kentucky", new BigDecimal("6").setScale(2, RoundingMode.HALF_UP));
        Taxes tax4 = new Taxes("CA", "Calfornia", new BigDecimal("25").setScale(2, RoundingMode.HALF_UP));

        List<Taxes> expResult = new ArrayList();
        expResult.add(tax1);
        expResult.add(tax2);
        expResult.add(tax3);
        expResult.add(tax4);
        
        return expResult;
    }

    @Override
    public List<Product> getProducts() throws FlooringMasteryPersistenceException {
        Product product1 = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        Product product2 = new Product("Laminate", new BigDecimal("1.75"), new BigDecimal("2.10"));
        Product product3 = new Product("Tile", new BigDecimal("3.50"), new BigDecimal("4.15"));
        Product product4 = new Product("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        
        
        List<Product> expResult = new ArrayList();
        expResult.add(product1);
        expResult.add(product2);
        expResult.add(product3);
        expResult.add(product4);
        
        return expResult;
    }

    @Override
    public void export() throws FlooringMasteryPersistenceException {
        //do nothings
    }

}
