import java.util.Scanner;
import java.util.*;

public class
Duke {
    public static boolean containsNumbers(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isDigit(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

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
        ArrayList<Task> toDoList = new ArrayList<Task>(100);

        while (!userInput.equals("bye")) {
            if (!containsNumbers(userInput)) {
                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    for (Task item : toDoList) {
                        System.out.print((toDoList.indexOf(item) + 1) + ".");
                        System.out.print(item.getStatusIcon());
                        System.out.println(item.getDescription());
                    }
                    System.out.println("____________________________________________________________\n");
                } else {
                    System.out.println("____________________________________________________________\n"
                            + "added:" + userInput + "\n"
                            + "____________________________________________________________");
                    toDoList.add(new Task(userInput));
                }
            } else {
                int itemNumber = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                if (userInput.contains("unmark")) {
                    toDoList.get(itemNumber).markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "    "
                            + toDoList.get(itemNumber).getStatusIcon() + toDoList.get(itemNumber).getDescription());
                } else {
                    toDoList.get(itemNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "    "
                            + toDoList.get(itemNumber).getStatusIcon() + toDoList.get(itemNumber).getDescription());
                }
            }
            userInput = myObj.nextLine();
        }
        System.out.println(goodBye);
    }
}