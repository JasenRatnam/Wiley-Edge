
package com.mycompany.classroster.dao;

/**
 *
 * @author Jasen
 */
public interface ClassRosterAuditDao {
     public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
