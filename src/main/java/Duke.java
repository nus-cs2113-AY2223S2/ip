import java.util.Scanner;

public class Duke {

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String line = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
            if (line.equals("bye")) {
                scanner.close();
                break;
            }
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        greetUser();
        echo();
        exit();
    }
}
