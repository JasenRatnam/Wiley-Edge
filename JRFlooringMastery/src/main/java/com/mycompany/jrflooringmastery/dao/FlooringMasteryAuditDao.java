
package com.mycompany.jrflooringmastery.dao;

/**
 *
 * @author Jasen Ratnam
 */
public interface FlooringMasteryAuditDao {

    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException;
}
