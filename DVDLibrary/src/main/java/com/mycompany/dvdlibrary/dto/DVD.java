
package com.mycompany.dvdlibrary.dto;

/**
 *
 * @author Jasen
 */
public class DVD {
    private String title;
    private String releaseDate;
    private Double MPAARating;
    private String directorName;
    private String studio;
    private String note;

    public DVD(String title, String releaseDate, Double MPAARating, String directorName, String studio, String note) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.MPAARating = MPAARating;
        this.directorName = directorName;
        this.studio = studio;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getMPAARating() {
        return MPAARating;
    }

    public void setMPAARating(Double MPAARating) {
        this.MPAARating = MPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return  title + "::" + releaseDate + "::" + MPAARating + "::" + 
                directorName + "::" + studio + "::" + note;
        
    }
    
    
}
