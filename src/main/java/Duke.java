import java.util.Scanner;
public class Duke {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
        return;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
        String line;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
