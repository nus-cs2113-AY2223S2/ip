
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
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
        List <String> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (line.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i+1) + "." + " " + tasks.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("\t" + "added: " + line);
                System.out.println("____________________________________________________________\n");
                tasks.add(line);
            }
            line = in.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
