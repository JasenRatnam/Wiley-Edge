
package com.mycompany.jrflooringmastery.service;

import com.mycompany.jrflooringmastery.dao.FlooringMasteryAuditDao;
import com.mycompany.jrflooringmastery.dao.FlooringMasteryPersistenceException;

/**
 *
 * @author Jasen Ratnam
 */
public class FlooringMasteryAuditDaoStubImpl implements FlooringMasteryAuditDao{

    @Override
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException {
        //do nothing . . .
    }

}
