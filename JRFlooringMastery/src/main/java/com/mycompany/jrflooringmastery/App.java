
package com.mycompany.jrflooringmastery;

import com.mycompany.jrflooringmastery.controller.FlooringMasteryController;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryAuditDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryAuditDaoImpl;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryDaoImpl;
import com.mycompany.jrflooringmastery.service.FlooringMasteryServiceLayer;
import com.mycompany.jrflooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.mycompany.jrflooringmastery.ui.FlooringMasteryView;
import com.mycompany.jrflooringmastery.ui.UserIO;
import com.mycompany.jrflooringmastery.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * main app of application
 * @author Jasen Ratnam
 */
public class App {

    public static void main(String[] args) {
        
         /**
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        // Instantiate the DAO
        FlooringMasteryDao myDao = new FlooringMasteryDaoImpl();
        // Instantiate the Audit DAO
        FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
        // Kick off the Controller
        controller.run();
        **/
        
         ApplicationContext ctx = 
          new ClassPathXmlApplicationContext("applicationContext.xml");
       
        //use get bean to retrieve the beans instantiated by the Spring
        //bean to retreive, type of bean wanted
        FlooringMasteryController controller = 
           ctx.getBean("controller", FlooringMasteryController.class);
       controller.run();
         
    }

}
