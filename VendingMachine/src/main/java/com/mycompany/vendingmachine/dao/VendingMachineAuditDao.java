
package com.mycompany.vendingmachine.dao;

/**
 * interface for the auditing feature of the machine
 * audit when important functions happen while using the machine
 * @author Jasen Ratnam
 */
public interface VendingMachineAuditDao {

    /**
     * write a string to a audit file
     * @param entry string to save to file
     * @throws VendingMachinePersistenceException if an error occurs while saving the audit to a file
     */
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;

}
