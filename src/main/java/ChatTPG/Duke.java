package ChatTPG;

import java.util.Scanner;
public class Duke {

    public static void greet() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm ChatTPG");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    public static void bye() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }

    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while ((input.compareTo("bye") != 0)) {
            System.out.println("________________________________");
            TaskManager.handleCommand(input);
            System.out.println("________________________________");
            input = in.nextLine();
        }
        bye();
    }
}
