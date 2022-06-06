
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Jasen
 */
public interface DVDDao {
    
    DVD removeDVD(String title);
        
    void addDVD(DVD newDVD);
    
    void editDVD(String title);
    
    DVD getDVD(String title);
   
    List<DVD> getAllDVDs();
    
    void setAllDVDs(List<DVD> allDVDs);
}
