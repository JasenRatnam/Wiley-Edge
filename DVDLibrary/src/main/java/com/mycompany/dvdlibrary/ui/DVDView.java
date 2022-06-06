
package com.mycompany.dvdlibrary.ui;

import com.mycompany.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jasen
 */
public class DVDView {
     //use the IO class to communicate with user
    private UserIO io = new UserIOConsoleImpl();

    //menu
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List all DVDs");
        io.print("5. Search a DVD");
        io.print("6. Load DVD from Files");
        io.print("7. Save DVD from Files");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }
    
    public void displayCreateDVDBanner(){
        io.print("=== Create DVD ===");
    }
    
    public DVD getNewDVDInfo(){
        String title = io.readString("Please enter the DVDs title");
        String releaseDate = io.readString("Please enter the DVDs release data");
        Double MPAARating = io.readDouble("Please enter the MPAA rating");
        String directorName = io.readString("Please enter the directors name");
        String studio = io.readString("Please enter the studio");
        String note = io.readString("Please enter any notes");
        
        DVD newDVD = new DVD(title, releaseDate, MPAARating, directorName, studio, note);
        
        return newDVD;
    }
    
    public void displayCreateSuccessBanner(){
        io.readString(
            "DVD successfully created.  Please hit enter to continue");
    }
    
    public void displayRemoveDVDBanner(){
        io.print("=== REMOVE DVD ===");
    }
    
    public String getTitleChoice(){
        return io.readString("Please enter the DVD title.");
    }
    
    public void displayRemoveResult(DVD removedDVD){
        if(removedDVD != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditDVDBanner(){
        io.print("=== EDIT DVD ===");
    }
     
    public void displayDisplayAllBanner(){
        io.print("=== ALL DVD's ===");
    }
    
    public void displayDVDList(List<DVD> DVDList){
        for (DVD currentDVD : DVDList) {
            String dvdInfo = String.format("#%s : %s %s %s %s %s",
                  currentDVD.getTitle(),
                  currentDVD.getReleaseDate(),
                  currentDVD.getMPAARating(),
                  currentDVD.getDirectorName(),
                  currentDVD.getStudio(),
                  currentDVD.getNote());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayDVDBanner(){
        io.print("=== DVD: ===");
    }
    
    public void displayDVD(DVD DVDDisplay){
        if (DVDDisplay != null) {
            String dvdInfo = String.format("#%s : %s %s %s %s %s",
                  DVDDisplay.getTitle(),
                  DVDDisplay.getReleaseDate(),
                  DVDDisplay.getMPAARating(),
                  DVDDisplay.getDirectorName(),
                  DVDDisplay.getStudio(),
                  DVDDisplay.getNote());
            io.print(dvdInfo);   
        } else {
            io.print("No such address.");
        }
        io.readString("Please hit enter to continue.");
    }
    
     //display exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    //display unkown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public DVD editDVD(DVD d){
        String title = io.readString("Please enter the DVDs title");
        String releaseDate = io.readString("Please enter the DVDs release data");
        Double MPAARating = io.readDouble("Please enter the MPAA rating");
        String directorName = io.readString("Please enter the directors name");
        String studio = io.readString("Please enter the studio");
        String note = io.readString("Please enter any notes");
        
        d.setTitle(title);
        d.setReleaseDate(releaseDate);
        d.setMPAARating(MPAARating);
        d.setDirectorName(directorName);
        d.setStudio(studio);
        d.setNote(note);
        
        return d;
    }
    
    public void displayLoadingBanner(){
        io.print("=== LOADING DVDS ===");
    }
    
    public void displaySavingBanner(){
        io.print("=== SAVING DVDS ===");
    }
    
    public void saveDVDToFile(List<DVD> DVDList){
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("DVDList.txt"));
            for(DVD d : DVDList) {
               out.println(d.toString());
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            io.print("ERROR: Saving to file saved");
        } 
        io.print("DVDs saved");
    }
    
    public String getFileName(){
        return io.readString("Please enter the FileName to choose from:");
    }
    
    public List<DVD> loadDVDFromFile(String fileName){
        List<DVD> DVDList = new ArrayList<>();
        
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(new FileReader(fileName)));
            
             // go through the file line by line
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                
                String[] fileKey = currentLine.split("::");
                
                String title = fileKey[0];
                String releaseDate = fileKey[1]; 
                Double MPAARating = Double.parseDouble(fileKey[2]);
                String directorName = fileKey[3];
                String studio = fileKey[4];
                String note  =fileKey[5];

                DVD d = new DVD(title, releaseDate, MPAARating, directorName, studio, note);
                
                DVDList.add(d);
                
            }
        } catch (FileNotFoundException ex) {
            io.print("The File could not be found.");
        }
        
        return DVDList;
    }
}
