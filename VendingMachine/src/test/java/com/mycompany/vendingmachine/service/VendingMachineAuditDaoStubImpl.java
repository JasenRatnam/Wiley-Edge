
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineAuditDao;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author Jasen Ratnam
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao{

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        //do nothing
    }

}
