
package com.mycompany.addressbook.controller;

import com.mycompany.addressbook.dao.AddressBookDao;
import com.mycompany.addressbook.dao.AddressBookDaoImpl;
import com.mycompany.addressbook.dto.Address;
import com.mycompany.addressbook.ui.AddressView;
import com.mycompany.addressbook.ui.UserIO;
import com.mycompany.addressbook.ui.UserIOConsoleImpl;
import java.util.Collection;

/**
 *
 * @author Jasen
 */
public class AddressBookController {
    
    //initialise view
    private AddressView view = new AddressView();
    //initialise UI
    private UserIO io = new UserIOConsoleImpl();
    //DAO to store the newly created Student
    private AddressBookDao dao = new AddressBookDaoImpl();

    //run program
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //while program is running
        while (keepGoing) {
            
            // print choices and get choice from user
            menuSelection = getMenuSelection();

            //cases for choice chosen
            switch (menuSelection) {
                case 1:
                    listAddresses();
                    break;
                case 2:
                    addAddress();
                    break;
                case 3:
                    findAddress();
                    break;
                case 4:
                    getNumberOfAddress();
                    break;
                case 5:
                    deleteAddress();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }
        
    private void addAddress(){
        view.displayCreateAddressBanner();
        //create student from view
        Address newAddress = view.getNewAddressInfo();
        dao.addAddress(newAddress.getLastName(), newAddress);
        view.displayCreateSuccessBanner();
    }
    
    private void findAddress(){
        view.displayFindAddressBanner();
        String lastName = view.getLastNameChoice();
        Address address = dao.findAddress(lastName);
        view.displayAddress(address);
    }
    
    private void getNumberOfAddress(){
        view.displayAddressCountBanner();
        int count = dao.getNumberOfAddress();
        view.displayCount(count);
    }
    
    private void deleteAddress(){
        view.displayRemoveAddressBanner();
        String lastName = view.getLastNameChoice();
        Address removedAddress = dao.removeAddress(lastName);
        view.displayRemoveResult(removedAddress);
    }
    
    private void listAddresses(){
        view.displayDisplayAllBanner();
        Collection<Address> addressList = dao.getAllAddresses();
        view.displayAddressList(addressList);
    }
        
    //get selection of the user from the view
     private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
     
     
      //Display the unknown command messahe
     private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

     //display exit message
    private void exitMessage() {
        view.displayExitBanner();
    }
}
