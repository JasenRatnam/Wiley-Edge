
package com.mycompany.addressbook.dto;

/**
 *
 * @author Jasen
 */
public class Address {
    private String lastName;
    private int number;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalcode;

    public Address(String lastName, int number, String street, String city, String province, String country, String postalcode) {
        this.lastName = lastName;
        this.number = number;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalcode = postalcode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Override
    public String toString() {
        return "Address{" + "number=" + number + ", street=" + street + ", city=" + city + ", province=" + province + ", country=" + country + ", postalcode=" + postalcode + '}';
    }
    
    
            
    
}
