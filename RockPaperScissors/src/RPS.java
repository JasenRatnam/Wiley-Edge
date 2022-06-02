import java.util.Random;
import java.util.Scanner;

public class RPS {

    /**
     * Get an integer input from a user
     * Ensures an integer is entered
     * if the integer entered is not in the range of 1-10 returns -1
     * Used to get the number of rounds user wants to play
     *
     * @param myScanner used to get entered information from console
     * @return the integer given by the user
     */
    private static int getIntInput(Scanner myScanner){

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
                    if (inputInt <= 10 && inputInt>=1)
                        isValid = true;
                    else {
                        System.out.println("ERROR: number of rounds exceeds allowed rang of 1-10");
                        return -1;
                    }
                } catch(NumberFormatException ex){
                    System.out.println("Input could not be parsed into an integer, try again.");
                    isValid = false;
                }
            }
        }
        return inputInt;
    }

    /**
     * Get a String input from a user
     * Ensures the string entered is 'yes' or 'no'
     * if the integer entered is not 'yes' or 'no', prompt to try again
     * Used to get the users choice for another game or not
     *
     * @param myScanner used to get entered information from console
     * @return the users choice
     */
    private static String getInput(Scanner myScanner){
        boolean isValid = false;
        String input = null;

        while(!isValid){
            input = myScanner.nextLine();

            if(input == null || input.isEmpty()){
                System.out.println("Please enter a valid entry");
            }
            else{
                if(input.equals("yes") || input.equals("no"))
                    isValid = true;
                else
                    System.out.println("Please enter a valid entry");
            }
        }
        return input;
    }

    /**
     * Handles the RPS game
     * @param args
     */
    public static void main(String[] args){
        //initiliazation
        Scanner myScanner = new Scanner(System.in);
        Random random = new Random();
        int choice = 0;
        int rounds = 0;
        int tie = 0;
        int win = 0;
        int lose = 0;

        //get number of rounds
        //range is handled by the method
        System.out.println("How many rounds do you want to play?");
        rounds = getIntInput(myScanner);

        //while there are still rounds to play
        //new rounds are added if user wants to play a new game
        //loop for entire session of all games
        while(rounds != -1){

            //loop for each game played
            //plays each round of a game
            while(rounds>0){

                //get the computers choice
                int choiceRobot = random.nextInt(3)+1;

                //get users choice
                System.out.println("Rounds left: " + rounds);
                System.out.println("What is your choice?");
                System.out.println("1 = Rock, 2 = Paper, 3 = Scissors");
                choice = getIntInput(myScanner);

                //if user choice is valid
                if(choice == 1 || choice == 2 || choice == 3){
                    //if user and robot have the same choice, its a tie
                    if(choice == choiceRobot){
                        System.out.println("It's a tie");
                        tie++;
                    } else if((choiceRobot == 1 && choice == 3) ||
                            (choiceRobot == 2 && choice == 1) ||
                            (choiceRobot == 3 && choice == 2)){
                        //if the robot wins
                        System.out.println("You lost");
                        lose++;
                    } else if((choice == 1 && choiceRobot == 3) ||
                            (choice == 2 && choiceRobot == 1) ||
                            (choice == 3 && choiceRobot == 2)){
                        //if you win
                        System.out.println("You Won");
                        win++;
                    }
                    System.out.println("\n");
                    //remove a round
                    rounds--;
                }else{
                    //if the user input was in valid try again
                    System.out.println("Invalid input, Try again");
                    System.out.println("HINT: 1 = Rock, 2 = Paper, 3 = Scissors");
                }
            }

            //print results
            System.out.println("Number of ties: " + tie);
            System.out.println("Number of wins: " + win);
            System.out.println("Number of lose: " + lose);

            if(win>lose){
                System.out.println("You have won!!");
            }
            else if(win<lose){
                System.out.println("The computer has won..");
            }
            else if(win==lose){
                System.out.println("It's a tie!");
            }

            //ask user for another game
            System.out.println("\nDo you wanna play again? (yes/no)");
            String input = getInput(myScanner);

            if(input.equals("yes")){
                System.out.println("How many rounds do you want to play?");
                rounds = getIntInput(myScanner);

                //assuming new game resets all results
                tie = 0;
                win = 0;
                lose = 0;
            }else if(input.equals("no")){
                System.out.println("Thanks for playing!");
                rounds = -1;
            }
        }
    }
}
