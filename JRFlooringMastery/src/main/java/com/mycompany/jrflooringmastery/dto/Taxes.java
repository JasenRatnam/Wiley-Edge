
package com.mycompany.jrflooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Jasen Ratnam
 */
public class Taxes {

    private String state;
    private String stateName;
    private BigDecimal taxRate;

    public Taxes(String state, String stateName, BigDecimal taxRate) {
        this.state = state;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.state);
        hash = 53 * hash + Objects.hashCode(this.stateName);
        hash = 53 * hash + Objects.hashCode(this.taxRate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Taxes other = (Taxes) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        return Objects.equals(this.taxRate, other.taxRate);
    }

    @Override
    public String toString() {
        return "State: " + state 
                + ": {stateName =" + stateName 
                + ", taxRate =" + taxRate + '}';
    }    
}
