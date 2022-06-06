
package com.mycompany.addressbook.ui;

import com.mycompany.addressbook.dto.Address;
import java.util.Collection;

/**
 *
 * @author Jasen
 */
public class AddressView {
    //use the IO class to communicate with user
    private UserIO io = new UserIOConsoleImpl();

    //menu
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Addresses");
        io.print("2. Add an address");
        io.print("3. Find an Address");
        io.print("4. Get number of addresses");
        io.print("5. Delete address");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }
    
    //display remove address banner
    public void displayRemoveAddressBanner () {
        io.print("=== Remove Address ===");
    }
    
     public void displayFindAddressBanner () {
        io.print("=== Find Address ===");
    }
     
      public void displayAddressCountBanner () {
        io.print("=== Count of Address ===");
    }              
    public void displayCount(int count) {
        io.print("There are " + count + " addresses in the book.");
        io.readString("Press enter to continue.");
    }
     
     //display a address object
    public void displayAddress(Address address) {
        if (address != null) {
            String addressInfo = String.format("#%s : %s %s %s %s %s",
                  address.getLastName(),
                  address.getNumber(),
                  address.getCity(),
                  address.getProvince(),
                  address.getCountry(),
                  address.getPostalcode());
            io.print(addressInfo);        
        } else {
            io.print("No such address.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //get last name as input
    public String getLastNameChoice() {
        return io.readString("Please enter the last name.");
    }
    
    //display result of address removal
    public void displayRemoveResult(Address addressRecord) {
        if(addressRecord != null){
          io.print("Address successfully removed.");
        }else{
          io.print("No such address.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //banner creating a student
    public void displayCreateAddressBanner() {
        io.print("=== Create Address ===");
    }
    
    //get the information we need from the user to create a new Student object.
    public Address getNewAddressInfo() {
     
        String lastName = io.readString("Please enter the person's last name");
        int number = io.readInt("Please enter street number");
        String street = io.readString("Please enter Street Name");
        String city = io.readString("Please enter City Name");
        String province = io.readString("Please enter Province");
        String country = io.readString("Please enter Country");
        String postalcode = io.readString("Please enter Postal Code");
        
        Address newAddress = new Address(lastName,number, street, city, province, country, postalcode);
        
        return newAddress;
    }
    
    //Displays a message that the new student was successfully created 
    //Waits for the user to hit Enter to continue. 
    public void displayCreateSuccessBanner() {
        io.readString(
            "Address successfully created.  Please hit enter to continue");
    }
    
    //display banner for all students
    public void displayDisplayAllBanner() {
        io.print("=== Display All Addresses ===");
    }
    
    //display list of students given
    public void displayAddressList(Collection<Address> addressList) {
        for (Address currentAddress : addressList) {
            String addressInfo = String.format("#%s : %s %s %s %s %s",
                  currentAddress.getLastName(),
                  currentAddress.getNumber(),
                  currentAddress.getCity(),
                  currentAddress.getProvince(),
                  currentAddress.getCountry(),
                  currentAddress.getPostalcode());
            io.print(addressInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    //display exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    //display unkown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
}

