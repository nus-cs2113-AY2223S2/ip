import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static final int MAX_NUM_OF_TASKS = 100;
    public static final String LINE_BREAK = "---------------------------------------------";

    public static void registerTodo (Task[] lists, String line, int index) {
        Task item;
        String[] inputLine = line.split(" ", 2);
        item = new Todo(inputLine[1]);
        lists[index] = item;
        printTask(item, index);
    }

    public static void registerDeadline (Task[] lists, String line, int index) {
        Task item;
        String[] inputLines = line.split(" ", 2);
        inputLines = inputLines[1].split("/by ");
        String description = inputLines[0];
        String deadline = inputLines[1];
        item = new Deadline(description, deadline);
        lists[index] = item;
        printTask(item, index);
    }

    public static void registerEvent (Task[] lists, String line, int index) {
        Task item;
        String[] inputLines = line.split(" ", 2);
        inputLines = inputLines[1].split("/from ");
        String description = inputLines[0];
        inputLines = inputLines[1].split("/to ");
        String start = inputLines[0];
        String end = inputLines[1];
        item = new Event(description, start, end);
        lists[index] = item;
        printTask(item, index);
    }

    public static void printTask (Task item, int index) {
        System.out.println("Got it. I've added this task: " + item.getTypeIcon()
                + item.getStatusIcon() + item.description);
        System.out.println("Now you have " + index + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    public static void printIntro () {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public static void printExiting() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    public static void main(String[] args) {
        String line = "";
        Task[] lists = new Task[MAX_NUM_OF_TASKS];
        int index = 0;
        Scanner in = new Scanner(System.in);
        printIntro();
        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            System.out.println(LINE_BREAK);
            if (line.contains("list")) {
                System.out.println("Here are the tasks in your list:");
                int itemNumber = 1;
                for (Task item : lists) {
                    if ((itemNumber - 1) == index) {
                        break;
                    }
                    item.printTask(itemNumber);
                    itemNumber++;
                }
            }
            else if (line.startsWith("mark")) {
                int itemNumber = Integer.parseInt(words[1]);
                lists[itemNumber - 1].markAsDone();
            }
            else if (line.startsWith("unmark")) {
                int itemNumber = Integer.parseInt(words[1]);
                lists[itemNumber - 1].markAsUndone();
            }
            else if (line.equals("bye")) {
                break;
            }
            else {
                Task item;
                if (line.startsWith("todo")) {
                    registerTodo(lists, line, index);
                    index++;
                }
                else if (line.startsWith("deadline")) {
                    registerDeadline(lists, line, index);
                    index++;
                }
                else if (line.startsWith("event")) {
                    registerEvent(lists, line, index);
                    index++;
                }
            }
        }
        printExiting();
        return;
    }
}
