import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static final int MAX_NUM_OF_TASKS = 100;
    public static final String LINE_BREAK = "---------------------------------------------";

    public static void registerTodo (Task[] lists, String line, int index) throws IndexOutOfBoundsException {
        try {
            String[] inputLine = line.split(" ", 2);
            Task item = new Todo(inputLine[1]);
            lists[index] = item;
            printTask(item, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your task must be of the following format: task (task name)");
        }
    }

    public static void registerDeadline (Task[] lists, String line, int index) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split("/by ");
            String description = inputLines[0];
            String deadline = inputLines[1];
            Task item = new Deadline(description, deadline);
            lists[index] = item;
            printTask(item, index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
        }
    }

    public static void registerEvent (Task[] lists, String line, int index) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split("/from ");
            String description = inputLines[0];
            inputLines = inputLines[1].split("/to ");
            String start = inputLines[0];
            String end = inputLines[1];
            Task item = new Event(description, start, end);
            lists[index] = item;
            printTask(item, index);
        } catch (IndexOutOfBoundsException e) {
        System.out.println("Your event must be of the following format: event (event name) /from (date) /to (date)");
        }
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

    public static String getInput() throws NoSuchElementException {
        Scanner in = new Scanner(System.in);
        String line = "";
        try {
            line = in.nextLine();
        } catch (NoSuchElementException e){
            printExiting();
        }
        return line;
    }

    public static int identifyInput(String line, Task[] lists, int index) throws DukeException {
        try {
            if (line.startsWith("todo")) {
                registerTodo(lists, line, index);
            } else if (line.startsWith("deadline")) {
                registerDeadline(lists, line, index);
            } else if (line.startsWith("event")) {
                registerEvent(lists, line, index);
            } else {
                throw new DukeException();
            }
        } catch (DukeException e){
            System.out.println("I am not a chatbot, please do not chat to me.");
        }

        return (index + 1);
    }

    public static void main(String[] args) throws DukeException {
        Task[] lists = new Task[MAX_NUM_OF_TASKS];
        int index = 0;
        printIntro();
        while (true) {
            String line = getInput();
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
                index = identifyInput(line, lists, index);
            }
        }
        printExiting();
    }
}
