package tools;

import java.util.Scanner;

public class InputFromKeyboard {
    public static int inputFromRange(int from, int to, Scanner scanner){

        boolean repeat = true;
        int userNum = from -1;

        do{
            try{
                userNum = scanner.nextInt(); scanner.nextLine();
                if(userNum >= from && userNum <= to){
                    repeat = false;
                } else inputAgain(from, to);
            }catch (Exception ex){
                scanner.nextLine();
                inputAgain(from, to);
            }
        } while(repeat);

        return userNum;
    }

    private static void inputAgain(int from, int to){
        System.out.println("Error, Incorrect number");
        System.out.printf("Enter number from %d...%d: ", from, to);
    }

}