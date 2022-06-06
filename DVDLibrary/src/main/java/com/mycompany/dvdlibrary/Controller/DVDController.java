
package com.mycompany.dvdlibrary.Controller;

import com.mycompany.dvdlibrary.dao.DVDDao;
import com.mycompany.dvdlibrary.dao.DVDDaoImpl;
import com.mycompany.dvdlibrary.dto.DVD;
import com.mycompany.dvdlibrary.ui.DVDView;
import com.mycompany.dvdlibrary.ui.UserIO;
import com.mycompany.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Jasen
 */
public class DVDController {
    //initialise view
    private DVDView view = new DVDView();
    //initialise UI
    private UserIO io = new UserIOConsoleImpl();
    //DAO to store the newly created Student
    private DVDDao dao = new DVDDaoImpl();

    //run program
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        //while program is running
        while (keepGoing) {
            
            // print choices and get choice from user
            menuSelection = getMenuSelection();

            //cases for choice chosen
            switch (menuSelection) {
                case 1:
                    addDVD();
                    break;
                case 2:
                    removeDVD();
                    break;
                case 3:
                    editDVD();
                    break;
                case 4:
                    listAllDVD();
                    break;
                case 5:
                    searchDVD();
                    break;
                case 6:
                    loadFile();
                    break;
                case 7:
                    saveFile();
                    break;
                case 8:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
        
    private void addDVD(){
        view.displayCreateDVDBanner();
        //create student from view
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD);
        view.displayCreateSuccessBanner();
    }
    
    private void removeDVD(){
        view.displayRemoveDVDBanner();
        String title = view.getTitleChoice();
        DVD removedDVD = dao.removeDVD(title);
        view.displayRemoveResult(removedDVD);
    }
    
    private void editDVD(){
        view.displayEditDVDBanner();
        String title = view.getTitleChoice();
        dao.editDVD(title);
    }
    
    private void listAllDVD(){
        view.displayDisplayAllBanner();
        List<DVD> DVDList = dao.getAllDVDs();
        view.displayDVDList(DVDList);
    }
    
    private void searchDVD(){
        view.displayDisplayDVDBanner();
        String title = view.getTitleChoice();
        DVD DVDDisplay = dao.getDVD(title);
        view.displayDVD(DVDDisplay);
    }
    
    private void loadFile(){
        view.displayLoadingBanner();
        String fileName = view.getFileName();
        List<DVD> DVDList = view.loadDVDFromFile(fileName);
        dao.setAllDVDs(DVDList);
    }
    
    private void saveFile(){
        view.displaySavingBanner();
        
        List<DVD> DVDList= dao.getAllDVDs();
        
        view.saveDVDToFile(DVDList);
    }
        
    //get selection of the user from the view
     private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
     
     
      //Display the unknown command messahe
     private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

     //display exit message
    private void exitMessage() {
        view.displayExitBanner();
    }
}
