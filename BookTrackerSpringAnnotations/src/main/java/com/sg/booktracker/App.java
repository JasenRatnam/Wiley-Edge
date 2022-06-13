package com.sg.booktracker;

import com.sg.booktracker.controller.BookController;
import com.sg.booktracker.dao.BookDao;
import com.sg.booktracker.dao.BookDaoMemoryImpl;
import com.sg.booktracker.service.BookService;
import com.sg.booktracker.ui.BookView;
import com.sg.booktracker.ui.UserIO;
import com.sg.booktracker.ui.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kyle David Rudy
 */
public class App {
    public static void main(String[] args) {
        /**
        UserIO io = new UserIOConsoleImpl();
        BookView view = new BookView(io);
        
        BookDao dao = new BookDaoMemoryImpl();
        BookService service = new BookService(dao);
        
        BookController controller = new BookController(service, view);
        controller.run();
        **/
        
        //AnnotationConfigApplicationContext class inported from Spring library
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        //tell where to start scanning project annotations
        //start scanning from the package your App is in
        appContext.scan("com.sg.booktracker");
        //check all classees it can find for annotations
        appContext.refresh();
        
        //Anything that is a @Component is created in memory 
        //with dependencies injected where it sees @Autowired.

        //default IDs are their names converted to camel-case
        BookController controller = appContext.getBean("bookController", BookController.class);
        controller.run();
        
    }
}
