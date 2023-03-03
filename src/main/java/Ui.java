import java.util.Scanner;

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
    public static String takeInput() {
        String input;
        Scanner scan = new Scanner(System.in); //place in UI
        input = scan.nextLine();
        return input;
    }
}
