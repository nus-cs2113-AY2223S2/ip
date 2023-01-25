import java.util.Objects;
import java.util.Scanner;
public class Duke {
    static String[] list = new String[100];
    static int listId = 0;
    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke  U ´ᴥ` U\n" + "What can I do for you?");
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
        if (s.equals("list")) {
            for (int i = 0; i < listId; i += 1) {
                System.out.print(i+1);
                System.out.println(". " + list[i]);
            }
            System.out.println("____________________________________________________________");
        } else {
            list[listId] = s;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + s);
            System.out.println("____________________________________________________________");
            listId += 1;
        }
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
