
package com.sg.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Controller to indicate that it should be loaded up as a bean by Spring.
 * @author Jasen Ratnam
 */
@Controller
public class MainController {
    
    //variables
    String name = "John";
    int number = 42;
    
    /**
     * indicating the URL path of our method: "test"
     * GET Method
     * page is displayed when we go to https://domain.com/test
     * 
     * @param model an object where we can put any data we want to render on the
     * page
     * put things into the Model as key-value pairs and pick them up from
     * the page using the key.
     * 
     * @return a string that is the name of the HTML page to use as the base.
     */
   @GetMapping("test")
   public String testPage(Model model) {

       //put data into the Model
       //pass in the key with the value
       model.addAttribute("number", number);
       model.addAttribute("firstName", name);
       
       // return the name of the HTML file we will point to
       // pointing to a test.html file
       return "test";
   }
   
   /**
    * value having the same name as the action in our form testForm.
    * parameter is an HttpServletRequest object
    * @param request use to pull out the form data.
    * Use post-redirect-get pattern
    *   form will POST data to the back-end, 
    *   the back-end will process and save the data, 
    *   Tell the browser to redirect to another page, 
    *       in this instance our "test" page.
    *   don't have to duplicate the code that would display the initial page
    * @return a string that is the name of the HTML page to go too
    */
    @PostMapping("testForm")
    public String testForm(HttpServletRequest request) {
        
        //get data based on the names we gave them in our HTML.
        //getParameter will always give us a string, 
        //so we have to convert the number variable to an integer.
        name = request.getParameter("formFirstName");
        number = Integer.parseInt(request.getParameter("formNumber"));
        
        //force the browser to go back to the test page and reload it.
        return "redirect:/test";
    }

}
