package Ui;

import java.util.Scanner;

public class Ui {

    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static String takeUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void rolexGreetsUser() {
        printLines();
        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?");
        printLines();
    }

    public static void rolexSaysBye() {
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }

    public static void printInvalidNumber(){
        printLines();
        System.out.println("Invalid index. Please enter valid index number!");
        printLines();
    }

}
