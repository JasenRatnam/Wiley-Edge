
package com.mycompany.dvdlibrary.ui;

/**
 * This interface defines the methods that must be implemented by any class 
 * that wants to directly interact with the user interface technology. 
 * We will implement a console-based user interface in the code-along. 
 * You could image, however, an implementation that used a windowing system or 
 * some other technology. Again, each class would be different but all would 
 * implement the same interface, ensuring that they are all well encapsulated. 
 * Note that the ClassRosterView only uses this interface to interact with the 
 * user — it is completely unaware of the implementation details.
 * @author Jasen
 */
public interface UserIO {
    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
}
