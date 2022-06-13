
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Jasen Ratnam
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    
    public VendingItem onlyItem;
    public BigDecimal fund = BigDecimal.ZERO;
    public boolean loaded = false;
    public boolean written = false;

    public VendingMachineDaoStubImpl() {
        onlyItem = new VendingItem("Lays Chip", new BigDecimal("1.94"),20);
    }
    
    @Override
    public ArrayList<VendingItem> getAllItems() {
        ArrayList<VendingItem> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void addFund(BigDecimal fund) {
        this.fund = fund;
    }

    @Override
    public String vendItem(int item) throws InsufficientFundsException, NoItemInventoryException {
        if(item == 0){
            onlyItem.setInventory(onlyItem.getInventory()-1);
            return onlyItem.getItemName();
        } else
            return null;
    }

    @Override
    public void loadItems() throws VendingMachinePersistenceException {
        //do nothing
        loaded = true;
    }

    @Override
    public void writeItems() throws VendingMachinePersistenceException {
        //do nothing
        written = true;
    }

    @Override
    public BigDecimal getFunds() {
        return fund.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void emptyFunds() {
        fund = BigDecimal.ZERO;
    }

}
