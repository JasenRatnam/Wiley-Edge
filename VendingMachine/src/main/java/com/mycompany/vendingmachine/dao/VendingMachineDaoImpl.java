
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingItem;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * implementation of vending machine dao interface
 * handles the dao of the application
 * @author Jasen Ratnam
 */
public class VendingMachineDaoImpl implements VendingMachineDao{

    //initilisation
    private final String ITEMS_FILE;
    private static final String DELIMITER = "::";
    
    // data structure to save items
    private List<VendingItem> items = new ArrayList<>(); 
    private BigDecimal fund = BigDecimal.ZERO;

    /**
     * default constructor
     * sets the filename where the items are saved and read from to items.txt
     */
    public VendingMachineDaoImpl() {
        this.ITEMS_FILE = "items.txt";
    }

    /**
     * constructor that allows you to choose the file name to save and read items from
     * @param ITEMS_FILE 
     */
    public VendingMachineDaoImpl(String ITEMS_FILE) {
        this.ITEMS_FILE = ITEMS_FILE;
    }
    
    /**
     * get all the items in the system
     * @return all items as an arraylist no matter their inventory
     */
    @Override
    public ArrayList<VendingItem> getAllItems() {
        
        return (ArrayList<VendingItem>) items;
    }

    /**
     * get the number of items 
     * @return an integer of the number of items in the system 
     */
    @Override
    public int getItemCount(){
        return items.size();
    }

    /**
     * add funds to the machine
     * Uses BigDecimal with a scale of 2 and rounded to half_up
     * @param fund to add 
     */
    @Override
    public void addFund(BigDecimal fund){
        BigDecimal rounded = fund.setScale(2, RoundingMode.HALF_UP);
        this.fund = this.fund.add(rounded);
    }
    
    /**
     * get total amount of funds in the system
     * @return BigDecimal of the funds value in the machine
     */
    @Override
    public BigDecimal getFunds() {
        return fund;
    }
    
    /**
     * empty the funds in the machine
     * set the big decimal to 0
     */
    @Override
    public void emptyFunds() {
        fund = BigDecimal.ZERO;
    }

    /**
     * vend an item from the vending machine
     * @param pos of the item in the list of items
     * @return the name of the item vended
     * @throws InsufficientFundsException if there is not enough funds
     * @throws NoItemInventoryException if there is not enough inventory
     */
    @Override
    public String vendItem(int pos) throws InsufficientFundsException,
                                            NoItemInventoryException{
        
    
        //get chosen item
        VendingItem item = items.get(pos);
        String name;
        name = item.getItemName();
        
        //if have enough fund vend it
        if(fund.compareTo(item.getItemCost()) >= 0){   
            //if there is inventory
            if(item.getInventory() >= 1){
                items.get(pos).setInventory(item.getInventory()-1);
                fund = fund.subtract(item.getItemCost());
                
                return name;
            } else{
                //not enough inventory
                throw new NoItemInventoryException(
                        "ERROR: Could not get " + name + " due to insufficient inventory");
            }
        }
        else{
            //not enough money
            throw new InsufficientFundsException(
                    "ERROR: Could not get " + name + " due to insufficient funds\n"
                            + "You have: " + fund.toString() + "$");
        }
    }
    
    /**
     * load items from a file to the system
     * @throws VendingMachinePersistenceException if there is an error reading the file
     */
    @Override
    public void loadItems() throws VendingMachinePersistenceException{
        Scanner scanner;
        items.clear();

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load items data into memory.", e);
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        VendingItem currentItem;
        
        // Go through ITEMS_FILE line by line, decoding each line into a 
        // Process while we have more lines in the file
        
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();    
            currentItem = unmarshallItem(currentLine);
            items.add(currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    /**
     * unmarshall items from a string line
     * @param itemAsText string line containing information about an item
     * @return an item created with string
     */
    private VendingItem unmarshallItem(String itemAsText){
        
        String[] itemTokens = itemAsText.split(DELIMITER);

        String itemName = itemTokens[0];
        
        BigDecimal b = new BigDecimal(itemTokens[1]);
        BigDecimal rounded = b.setScale(2, RoundingMode.HALF_UP);
        long inventory = Long.parseLong(itemTokens[2]);

        VendingItem itemFromFile = new VendingItem(itemName, rounded, inventory);
        
        return itemFromFile;
    }
    
    
    /**
     * marshall item into a string using delimiter
     * @param aItem item to marshal to a string
     * @return a string representation of the item
     */
    private String marshallItem(VendingItem aItem){
       
        String itemAsText = aItem.getItemName() + DELIMITER;
        itemAsText += aItem.getItemCost() + DELIMITER;
        itemAsText += aItem.getInventory();

        return itemAsText;
    }
    
    /**
    * Writes all items in the file out to a ITEMS_FILE.
    * @throws com.mycompany.vendingmachine.dao.VendingMachinePersistenceException
    */
    @Override
   public void writeItems() throws VendingMachinePersistenceException {
       
       PrintWriter out;

       try {
           out = new PrintWriter(new FileWriter(ITEMS_FILE));
       } catch (IOException e) {
           throw new VendingMachinePersistenceException(
                   "Could not save items data.", e);
       }

       String itemAsText;
       List<VendingItem> itemList = this.getAllItems();
       for (VendingItem currentItem : itemList) {
           // turn a Item into a String
           itemAsText = marshallItem(currentItem);
           // write the Item object to currentItem file
           out.println(itemAsText);
           // force PrintWriter to write line to the file
           out.flush();
       }
       // Clean up
       out.close();
   }
}
