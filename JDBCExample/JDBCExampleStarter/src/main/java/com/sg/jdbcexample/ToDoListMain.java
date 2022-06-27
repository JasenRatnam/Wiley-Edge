/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbcexample;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.DataSource;

/**
 *
 * @author kylerudy
 */
public class ToDoListMain {

    private static Scanner sc;
    private static DataSource ds;

    public static void main(String[] args) {
        
        /*
        Sets up the DataSource, 
        Also catches if there is a SQL Exception. 
        If that Exception is thrown, it means the connection the database failed
            Show a simple message and exit the program.
        */
        try {
            ds = getDataSource();
        } catch (SQLException ex) {
            System.out.println("Error connecting to database");
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        //Now that we have the DataSource set up, 
        //we can implement the different CRUD features, 
        //starting with displaying the full list of ToDo items.

        sc = new Scanner(System.in);

        do {
            System.out.println("To-Do List");
            System.out.println("1. Display List");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Remove Item");
            System.out.println("5. Exit");

            System.out.println("Enter an option:");
            String option = sc.nextLine();
            try {
                switch (option) {
                    case "1":
                        displayList();
                        break;
                    case "2":
                        addItem();
                        break;
                    case "3":
                        updateItem();
                        break;
                    case "4":
                        removeItem();
                        break;
                    case "5":
                        System.out.println("Exiting");
                        System.exit(0);
                }
            } catch (SQLException ex) {
                System.out.println("Error communicating with database");
                System.out.println(ex.getMessage());
                System.exit(0);
            }

        } while (true);
    }
    
    /**
     * method to instantiate our DataSource 
     * Will contain the settings to connect to our database. 
     * @return database
     * @throws SQLException  SQL connection error
     */
    private static DataSource getDataSource() throws SQLException {
        
        //instantiate a MysqlDataSource object 
        //Use that object to establish communication with the database
        MysqlDataSource ds = new MysqlDataSource();
        //The Server Name is 'localhost', connecting to our local database, 
        ds.setServerName("localhost");
        //Database Name is 'todoDB' – the database we created
        ds.setDatabaseName("todoDB");
        //The User and Password should match what your local database is using.
        ds.setUser("root");
        ds.setPassword("");
        //Server Timezone helps the server know how to handle dates and times
        ds.setServerTimezone("America/Chicago");
        //Use SSL toggles SSL encryption. 
        //We would typically set this to true, 
        //but for our local development, it would be overkill 
        //We default to false for this activity.
        ds.setUseSSL(false);
        //Public Key Retrieval allows our JDBC driver to send the password 
        //to our database securely.
        ds.setAllowPublicKeyRetrieval(true);

        return ds;
    }

    /**
     * SELECT all information from TODO table
     * And print the selected information out
     * @throws SQLException 
     */
    private static void displayList() throws SQLException {
        // create our Connection.
        try ( Connection conn = ds.getConnection()) {
            // create a Statement with our Connection
            // can use statement because the query we are running will never 
            // change due to user input
            Statement stmt = conn.createStatement();
            //use the executeQuery method of the Statement to run our query 
            // to select all todo entries and put them into a ResultSet.
            ResultSet rs = stmt.executeQuery("SELECT * FROM todo");
            
            //use a loop to move through the ResultSet
            while (rs.next()) {
                //we use getString, getBoolean, or getInt on a ResultSet, 
                // it is pulling data from whatever row it is currently looking at.
                // we print out the data for each row as we move through the ResultSet.
                System.out.printf("%s: %s -- %s -- %s\n",
                        rs.getString("id"),
                        rs.getString("todo"),
                        rs.getString("note"),
                        rs.getBoolean("finished"));
            }
            System.out.println("");
        }
    }

    private static void addItem() throws SQLException {
       
        // ask the user for the name of the task 
        System.out.println("Add Item");
        System.out.println("What is the task?");
        String task = sc.nextLine();
        //Ask any notes that should accompany the task.
        System.out.println("Any additional notes?");
        String note = sc.nextLine();
        
        //get our Connection.
        try (Connection conn = ds.getConnection()) {
            String sql = "INSERT INTO todo(todo, note) VALUES(?,?)";
            //create a PreparedStatement to avoid SQL Injection attacks.
            PreparedStatement pStmt = conn.prepareCall(sql);
            //we add the data at the appropriate positions in the query
            pStmt.setString(1, task);
            pStmt.setString(2, note);
            
            //call executeUpdate.
            pStmt.executeUpdate();
            System.out.println("Add Complete");
            
           // We don't include 'id' or 'finished' in our INSERT because 
           // the 'id' is generated automatically by MySQL and 
           // we set the default for 'finished' to false.
        }
    }

    private static void updateItem() throws SQLException {
        // Asking for the ID of which item we want to update.
        // Get our Connection and create a Prepared Statement to get the 
        // ToDo item based off the ID.
        // Once we have the ResultSet, we use it to build a ToDo object, 
        // getting the fields necessary for the object out of the ResultSet.
        // Now that we have the original object, 
        System.out.println("Update Item");
        System.out.println("Which item do you want to update?");
        String itemId = sc.nextLine();
        try( Connection conn = ds.getConnection()) {
            String sql = "SELECT * FROM todo WHERE id = ?";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, itemId);
            ResultSet rs = pStmt.executeQuery();
            rs.next();
            ToDo td = new ToDo();
            td.setId(rs.getInt("id"));
            td.setTodo(rs.getString("todo"));
            td.setNote(rs.getString("note"));
            td.setFinished(rs.getBoolean("finished"));
            
            
            // can look at the rest of the code to do the actual updating.
            
            // Displaying the info for the existing item 
            // Asking which field we want to update.
            System.out.println("1. ToDo - " + td.getTodo());
            System.out.println("2. Note - " + td.getNote());
            System.out.println("3. Finished - " + td.isFinished());
            System.out.println("What would you like to change?");

            //Based on what we want to update, 
            //we get that data from the user and update the ToDo object
            String choice = sc.nextLine();
            switch(choice) {
                case "1":
                    System.out.println("Enter new ToDo:");
                    String todo = sc.nextLine();
                    td.setTodo(todo);
                    break;
                case "2":
                    System.out.println("Enter new Note:");
                    String note = sc.nextLine();
                    td.setNote(note);
                    break;
                case "3":
                    System.out.println("Toggling Finished to " + !td.isFinished());
                    td.setFinished(!td.isFinished());
                    break;
                default:
                    System.out.println("No change made");
                    return;
            }
            
            // use our existing Connection to make a Prepared Statement 
            // to perform the Update.
            String updateSql = "UPDATE todo SET todo = ?, note = ?, finished = ? WHERE id = ?";
            PreparedStatement updatePStmt = conn.prepareCall(updateSql);
            
            //pull the data from the now updated ToDo object 
            //set it into the Prepared Statement.
            updatePStmt.setString(1, td.getTodo());
            updatePStmt.setString(2, td.getNote());
            updatePStmt.setBoolean(3, td.isFinished());
            updatePStmt.setInt(4, td.getId());
            //executeUpdate and the change should be in our database.
            updatePStmt.executeUpdate();
            System.out.println("Update Complete");
        }
    }

    private static void removeItem() throws SQLException {
       
        // Ask for the ID of the item we want to remove.
        System.out.println("Remove Item");
        System.out.println("Which item would you like to remove?");
        String itemId = sc.nextLine();
        
        // Create the Connection and Prepared Statement.
        try(Connection conn = ds.getConnection()) {
            String sql = "DELETE FROM todo WHERE id = ?";
            // With the ID we can pinpoint a single item to delete, 
            // so we add that in and executeUpdate.
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, itemId);
            pStmt.executeUpdate();
            System.out.println("Remove Complete");
        }    
    }
}

