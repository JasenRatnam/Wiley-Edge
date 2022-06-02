/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sp.payrollproject;

/**
 *
 * @author SPalm
 */
public class Address {
    private String street, city, zip;
    
    /*
    public Address(String s, String c, String z){
        street = s;
        city = c;
        zip =z;
    }
*/

    public Address(String street, String city, String zip) {
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + ", city=" + city + ", zip=" + zip + '}';
    }
    
    
}
