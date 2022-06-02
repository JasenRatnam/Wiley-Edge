import java.util.Scanner;

public class App {

    private static String getInput(){
        Scanner myScanner = myScanner = new Scanner(System.in);
        boolean isValid = false;
        String input = "";
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

    private static int getIntInput(){
        Scanner myScanner = myScanner = new Scanner(System.in);
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
                    System.out.println("Input could not be parsed into an integer");
                    isValid = false;
                }
            }
        }
        return inputInt;
    }

    public static void main(String[] args){
        SimpleCalculator calculator = new SimpleCalculator();
        int operand1 = 0;
        int operand2 = 0;

        System.out.println("What operation do you want to do: ");
        System.out.println("addition, subtraction, multiplication, division, quit");

        String input = getInput();

        while (!input.equals("quit")){

            System.out.println("Enter the first operand:");
            operand1 = getIntInput();

            System.out.println("Enter the second operand:");
            operand2 = getIntInput();

            if(input.equals("addition")){

                System.out.println(calculator.addition(operand1,operand2));

            } else if(input.equals("subtraction")){

                System.out.println(calculator.subtraction(operand1,operand2));

            } else if(input.equals("multiplication")){

                System.out.println(calculator.multiplication(operand1,operand2));

            } else if(input.equals("division")){

                System.out.println(calculator.division(operand1,operand2));

            }



            System.out.println("What operation do you want to do: ");
            System.out.println("addition, subtraction, multiplication, division, quit");

            input = getInput();

        }

        System.out.println("Thank you!!");


    }
}
