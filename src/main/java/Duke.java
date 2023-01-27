
import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            System.out.println("\t" + line);
            System.out.println("____________________________________________________________\n");
            line = in.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
    }
}
