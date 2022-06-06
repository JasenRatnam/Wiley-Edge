
package com.mycompany.addressbook.dao;

import com.mycompany.addressbook.dto.Address;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jasen
 */
public class AddressBookDaoImpl implements AddressBookDao{

     // data structure to save address
    private Map<String, Address> addresses = new HashMap<>();
    
    @Override
    public Address addAddress(String lastName, Address address){
        Address newAddress = addresses.put(lastName, address);
        return newAddress;
    }

    @Override
    public Address removeAddress(String lastName) {
        Address removeAddress = addresses.remove(lastName);
        return removeAddress;
    }

    @Override
    public Address findAddress(String lastName) {
        return addresses.get(lastName);
    }

    @Override
    public int getNumberOfAddress() {
        return addresses.size();
    }

    @Override
    public Collection<Address> getAllAddresses() {
       Collection<Address> addressList = addresses.values();
       return addressList;
    }
    
}
