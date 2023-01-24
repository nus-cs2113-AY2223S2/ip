import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    public static void echo() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        if (s.equals("bye")) {
            goodBye();
            return;
        }
        System.out.println(s);
        System.out.println("____________________________________________________________");
        echo();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}
