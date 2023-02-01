import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String lineBreak = "---------------------------------------------";
        String line = "";
        Task[] list = new Task[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println(lineBreak);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(lineBreak);
        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            System.out.println(lineBreak);
            if (line.contains("list")) {
                System.out.println("Here are the tasks in your list:");
                int itemNumber = 1;
                for (Task item : list) {
                    if ((itemNumber - 1) == index) {
                        break;
                    }
                    System.out.println(itemNumber + ".[" + list[itemNumber - 1].getTypeIcon() + "]"
                            + "["  + list[itemNumber - 1].getStatusIcon() + "] " + list[itemNumber - 1].description);
                    itemNumber++;
                }
                System.out.println(lineBreak);
            }
            else if (line.startsWith("mark")) {
                int itemNumber = Integer.parseInt(words[1]);
                list[itemNumber - 1].markAsDone();
            }
            else if (line.startsWith("unmark")) {
                int itemNumber = Integer.parseInt(words[1]);
                list[itemNumber - 1].markAsUndone();
            }
            else if (line.equals("bye")) {
                break;
            }
            else {
                Task item;
                if (line.startsWith("todo")) {
                    String[] inputLine = line.split(" ", 2);
                    item = new Todo(inputLine[1]);
                    list[index] = item;
                    index++;
                    System.out.println("Got it. I've added this task: " + "[" + item.getTypeIcon() + "]"
                            + "["  + item.getStatusIcon() + "] " + item.description);
                }
                else if (line.startsWith("deadline")) {
                    String[] inputLine = line.split(" ", 2);
                    inputLine = inputLine[1].split("/by ");
                    String description = inputLine[0];
                    String deadline = inputLine[1];
                    item = new Deadline(description, deadline);
                    list[index] = item;
                    index++;
                    System.out.println("Got it. I've added this task: " + "[" + item.getTypeIcon() + "]"
                            + "["  + item.getStatusIcon() + "] " + item.description);
                }
                else if (line.startsWith("event")) {
                    String[] inputLine = line.split(" ", 2);
                    inputLine = inputLine[1].split("/from ");
                    String description = inputLine[0];
                    inputLine = inputLine[1].split("/to ");
                    String start = inputLine[0];
                    String end = inputLine[1];
                    item = new Event(description, start, end);
                    list[index] = item;
                    index++;
                    System.out.println("Got it. I've added this task: " + "[" + item.getTypeIcon() + "]"
                            + "["  + item.getStatusIcon() + "] " + item.description);
                }


                System.out.println("Now you have " + index + " tasks in the list.");
                System.out.println(lineBreak);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
        return;
    }
}
