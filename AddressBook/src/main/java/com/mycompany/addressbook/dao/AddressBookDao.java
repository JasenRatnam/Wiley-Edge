
package com.mycompany.addressbook.dao;

import com.mycompany.addressbook.dto.Address;
import java.util.Collection;

/**
 *
 * @author Jasen
 */
public interface AddressBookDao {
    Address addAddress(String lastName, Address address);
    
    Address removeAddress(String lastName);
    
    Address findAddress(String lastName);
    
    int getNumberOfAddress();
    
    Collection<Address> getAllAddresses();
    
    
    
    
    
}
