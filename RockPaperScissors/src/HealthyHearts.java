import java.util.Scanner;

public class HealthyHearts {

    /*
    Their maximum heart rate should be 220 - their age.
    The target heart rate zone is the 50 - 85% of the maximum.
     */

    /**
     * Get an integer input from a user
     * Ensures an integer is entered
     * @return the integer given by the user
     */
    private static int getIntInput(){
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        int inputInt = -1;

        while(!isValid){
            String input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                try{
                    //convert string to int
                    inputInt = Integer.parseInt(input);
                    isValid = true;
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer, try again.");
                    isValid = false;
                }
            }
        }
        return inputInt;
    }

    public static void main(String[] args) {

        System.out.println("What is your age?");
        int age = getIntInput();
        int maxHeartRate = 220-age;
        int targetZoneMin = (int) (0.5*maxHeartRate);
        int targetZoneMax = (int) (0.85*maxHeartRate);

        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute");
        System.out.println("Your target HR zone is " + targetZoneMin + " - " + targetZoneMax + " beats per minute");
    }
}
