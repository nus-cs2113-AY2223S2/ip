package userInterface;
import java.util.Scanner;

public class UserInput { 
    private static Scanner in = new Scanner(System.in);
    
    /*
     * Scans the user's input in the CLI and return as a string
     * 
     * @return String userInput 
     */
    public static String getInput(){
        String userInput = in.nextLine();
        return userInput;
    }   
}
