
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * item objects of the vending machine
 * @author Jasen Ratnam
 */
public class VendingItem {
    private String itemName;
    private BigDecimal itemCost;
    private long inventory; 

    /**
     * constructor setting name, cost and inventory of the item
     * @param itemName
     * @param itemCost
     * @param inventory 
     */
    public VendingItem(String itemName, BigDecimal itemCost, long inventory) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.inventory = inventory;
    }

    //getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public long getInventory() {
        return inventory;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    /**
     * Get a string of the item object
     * @return 
     */
    @Override
    public String toString() {
        return itemName + ": Cost= $" + itemCost + ", Inventory= " + inventory ;
    }

    //equals and hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.itemName);
        hash = 47 * hash + Objects.hashCode(this.itemCost);
        hash = 47 * hash + (int) (this.inventory ^ (this.inventory >>> 32));
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
        final VendingItem other = (VendingItem) obj;
        if (this.inventory != other.inventory) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        return Objects.equals(this.itemCost, other.itemCost);
    }
    
    
}
