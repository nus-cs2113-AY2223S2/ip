import java.util.Scanner;

public class Duke {
    private static String line = "____________________________________________________________";
    public static void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);
    }
    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        String list;
        while (true) {
            Scanner in = new Scanner(System.in);
            list = in.nextLine();
            if (list.equalsIgnoreCase("bye")) {
                bye();
                break;
            }
            else {
                System.out.println(line);
                System.out.println(list);
                System.out.println(line);
            }
        }
    }
}
