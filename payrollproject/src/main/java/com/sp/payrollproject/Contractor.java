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
public class Contractor extends Person implements Payable{
    private boolean permanent;
    private double hourlyRate;

    public Contractor(boolean permanent, double hourlyRate, String name, int age, Address address) {
        super(name, age, address);
        this.permanent = permanent;
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "Contractor{" + "permanent=" + permanent + ", hourlyRate=" + hourlyRate + '}' + super.toString();
    }

    @Override
    public double calculateWeeklyPay() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return getHourlyRate() * 40;
    }
    
    
    
}
