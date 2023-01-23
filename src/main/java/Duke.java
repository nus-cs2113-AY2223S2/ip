import java.util.Scanner;
import java.util.*;

public class
Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = ("____________________________________________________________\n"
                + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + "____________________________________________________________\n"
        );

        String goodBye = ("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");

        System.out.println(greeting);
        Scanner myObj = new Scanner(System.in);
        String userInput;
        userInput = myObj.nextLine();
        ArrayList<String> toDolist = new ArrayList<String>(100);

        while (!userInput.equals("bye")) {
            int i = 1;
            if (userInput.equals("list")) {
                for (String item : toDolist) {
                    System.out.println(i + "." + item);
                    i += 1;
                }
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n"
                        + "added:" + userInput + "\n"
                        + "____________________________________________________________");
                toDolist.add(userInput);
            }
            userInput = myObj.nextLine();
        }
        System.out.println(goodBye);
    }
}
