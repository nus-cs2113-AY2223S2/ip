import java.util.Scanner;

public class UI {
    static String lineBreak = "-----------------";
    public static void showWelcome(){
        System.out.println(lineBreak + '\n' + "Hello! I'm Duke" + '\n'
                + "What can I do for you?" );
    }
    public static String readCommand(){
        Scanner myObj = new Scanner(System.in);
        return  myObj.nextLine();
    }
    public static void showBye(){
        System.out.println(lineBreak + '\n'
                + "Bye. Hope to see you again soon!");
    }
    public static void showLine(){
        System.out.println(lineBreak);
    }



}
