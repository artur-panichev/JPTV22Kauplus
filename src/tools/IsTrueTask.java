package tools;

import java.util.Scanner;

public class IsTrueTask {
    public static boolean checkTask(String taskName, Scanner scanner){
        System.out.printf("Is it right task? %s (y/n): ", taskName);
        String userAgree = scanner.nextLine();
        if(userAgree.equals("y")){
            return true;
        } else return false;
    }
}
