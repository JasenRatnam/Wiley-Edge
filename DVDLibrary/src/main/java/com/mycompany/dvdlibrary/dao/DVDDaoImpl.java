
package com.mycompany.dvdlibrary.dao;

import com.mycompany.dvdlibrary.dto.DVD;
import com.mycompany.dvdlibrary.ui.DVDView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jasen
 */
public class DVDDaoImpl implements DVDDao{

    private List<DVD> DVDList = new ArrayList<>();
    private DVDView view = new DVDView();
    
    @Override
    public DVD removeDVD(String title) {
        for(DVD d : DVDList) {
            if(d.getTitle().equals(title)){
                DVDList.remove(d);
                return d;
            }
        }
        return null;
    }

    @Override
    public void addDVD(DVD newDVD) {
        DVDList.add(newDVD);
    }

    @Override
    public void editDVD(String title) {
        int i =0;
        for(DVD d : DVDList) {
            if(d.getTitle().equals(title)){
                view.displayDVD(d);
                DVDList.set(i, view.editDVD(d));
            }
            i++;
        }
    }

    @Override
    public DVD getDVD(String title) {
        for(DVD d : DVDList) {
            if(d.getTitle().equals(title)){
                return d;
            }
        }
        return null;
    }

    @Override
    public List<DVD> getAllDVDs() {
       return DVDList;
    }

    @Override
    public void setAllDVDs(List<DVD> allDVDs) {
        DVDList = allDVDs;
    }
    
}
