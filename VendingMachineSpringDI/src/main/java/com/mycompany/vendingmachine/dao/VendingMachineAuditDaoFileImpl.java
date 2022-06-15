
package com.mycompany.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * used to write audits of what happens in the machine into a file
 * @author Jasen Ratnam
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao{

    //file name to use as audit file
    public static final String AUDIT_FILE = "audit.txt";
    
    /**
     * Writes audits to a text file
     * Writes important actions happening in the machine
     * @param entry string to be added to the audit
     * @throws VendingMachinePersistenceException when an error occurs while saving the file
     */
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
       
        try {
            //append to file with keyword true
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not persist audit information.", e);
        }
 
        //add timestamp along with entry to file
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
