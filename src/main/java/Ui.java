import java.util.Scanner;

/**
 * The Ui class handles reading input and displaying output.
 */
public class Ui {
    public static void welcomeMessage() {
        System.out.println(StrIntLib.welcomeText);
    }
    public static void byeMessage() {
        System.out.println(StrIntLib.byeText);
    }
    public static void invalidCommand() {
        System.out.println(StrIntLib.invalidCmdText);
    }

    /**
     * This method takes in user input.
     *
     * @return String This returns user input.
     */
    public static String takeInput() {
        String input;
        Scanner scan = new Scanner(System.in); //place in UI
        input = scan.nextLine();
        return input;
    }
}
