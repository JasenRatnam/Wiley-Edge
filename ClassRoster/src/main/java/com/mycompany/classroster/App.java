
package com.mycompany.classroster;

import com.mycompany.classroster.controller.ClassRosterController;

/**
 * Main app running the program
 * @author Jasen
 */
public class App {
    
    public static void main(String[] args) {
        //instiate the controller and run it
        ClassRosterController controller = new ClassRosterController();
        controller.run();
    }
}
