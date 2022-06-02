import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    /**
     * Get a String input from a user
     * @return the users choice
     */
    private static String getInput(){
        Scanner myScanner = new Scanner(System.in);
        boolean isValid = false;
        String input = null;

        while(!isValid){
            input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                isValid = true;
            }
        }
        return input;
    }

    public static void main(String[] args) {

        //initialise
        int stBernard = 0;
        int chihuahua = 0;
        int dramaticRedNosedAsianPug = 0;
        int commonCur = 0;
        int kingDoberman = 0;
        int total = 0;
        Random random  = new Random();


        //get name
        System.out.println("What is your dog's name?");
        String name = getInput();

        System.out.println("Well then, I have this highly reliable report on Sir Fluffy McFlufferkins Esquire's " +
                "prestigious background right here.\n");

        System.out.println(name + " is: \n");

        //get random percentage
        stBernard = random.nextInt(100)+1;
        //save total of percentages
        total += stBernard;
        System.out.println(stBernard + "% St. Bernard");

        //ensure that we are not at 100% yet, if we are, everything else is zero
        if(total != 100)
            chihuahua = random.nextInt((100-stBernard))+1;
        else
            chihuahua = 0;
        total += chihuahua;
        System.out.println(chihuahua + "% Chihuahua");

        if(total != 100)
            dramaticRedNosedAsianPug = random.nextInt((100-stBernard-chihuahua))+1;
        else
            dramaticRedNosedAsianPug = 0;
        total += dramaticRedNosedAsianPug;
        System.out.println(dramaticRedNosedAsianPug + "% Dramatic RedNosed Asian Pug");

        if(total != 100)
            commonCur = random.nextInt((100-stBernard-chihuahua-dramaticRedNosedAsianPug))+1;
        else
            commonCur = 0;
        total += commonCur;
        System.out.println(commonCur + "% Common Cur");

        if(total != 0)
            kingDoberman = 100-stBernard-chihuahua-dramaticRedNosedAsianPug-commonCur;
        else
            kingDoberman = 0;
        System.out.println(kingDoberman + "% King Doberman\n");

        System.out.println("Wow, that's QUITE the dog!");

    }
}
