package userInterface;
import java.util.Scanner;

public class UserInput { 
    private static Scanner in = new Scanner(System.in);
    
    public static String getInput(){
        String userInput = in.nextLine();
        return userInput;
    }   
}
