import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static final int MAX_TASK_NUM = 100;
    public static void main(String[] args) {
        showGreetings();
        processInputs();
        showGoodbye();
    }

    private static void processInputs() {
        Scanner in = new Scanner (System.in);
        Task[] storeValues = new Task[MAX_TASK_NUM];
        int counter = 0;

        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(storeValues, counter);
            } else if (line.startsWith("mark ")) {
                markItem(storeValues, line);
            } else if (line.startsWith("unmark ")) {
                unmarkItem(storeValues, line);
            } else if (line.startsWith("deadline")) {
                int forwardSlash = line.indexOf('/');
                int endOfTask = forwardSlash - 1;
                int dates = forwardSlash + 4;

                Deadline deadlineInput = new Deadline(line.substring(9,endOfTask), line.substring(dates));
                /* System.out.println(deadlineInput.toString());
                deadlineInput.markAsDone();*/
                //System.out.println(deadlineInput);
                counter = addTask(storeValues, counter, deadlineInput);
            } else if (line.startsWith("todo")) {
                String removeCommand = line.substring(5);
                Todo todoInput= new Todo(removeCommand);
                //System.out.println(todoInput.toString());
                //todoInput.markAsDone();
                //System.out.println(todoInput.getStatusIcon());
                //System.out.println(todoInput.toString());
                counter = addTask(storeValues, counter, todoInput/*.toString()*/);
            } else if (line.startsWith("event")) {
                int firstForwardSlash = line.indexOf('/');
                String taskName = line.substring(6,firstForwardSlash-1);
                String duration = line.substring(firstForwardSlash+6);
                int secondForwardSlash = duration.indexOf('/');
                String startingTime = duration.substring(0,secondForwardSlash-1);
                String endingTime = duration.substring(secondForwardSlash+4);
                Event eventInput = new Event(taskName, startingTime, endingTime);

                counter = addTask(storeValues, counter, eventInput);
            } else {
                //commands that are not listed above
                System.out.println("Invalid command, try again! \n");
                //counter = addTask(storeValues, counter, line);
            }
            line = in.nextLine();
        }
    }

    private static int addTask(Task[] storeValues, int counter, Task line) {
        formattingLine();
        System.out.println("Got it. I've added this task: \n" + line.toString() + "\n"+
                "Now you have " + (counter + 1) + " tasks in the list.\n");
        formattingLine();
        //store value in line into list
        Task newInput = line;
        storeValues[counter] = newInput;
        counter += 1;
        return counter;
    }

    private static void unmarkItem(Task[] storeValues, String line) {
        int length = line.length();
        String itemToMark = line.substring(7, length);
        int numToMark = Integer.parseInt(itemToMark);
        //unmark the item
        storeValues[numToMark-1].unmarkAsDone();
        formattingLine();
        System.out.println("OK, I've marked this task as not done yet: \n" +
                storeValues[numToMark-1].toString() + "\n");
        formattingLine();
    }

    private static void markItem(Task[] storeValues, String line) {
        int length = line.length();
        String itemToMark = line.substring(5, length);
        int numToMark = Integer.parseInt(itemToMark);
        //mark the item as complete
        storeValues[numToMark-1].markAsDone();
        //System.out.println(storeValues[numToMark-1].getStatusIcon());
        formattingLine();
        System.out.println("Nice! I've marked this task as done: \n"
                /*"[" + storeValues[numToMark-1].getStatusIcon() + "] " */+ storeValues[numToMark-1].toString() + "\n");
        formattingLine();
    }

    private static void printList(Task[] storeValues, int counter) {
        int currValue = 0;
        Task[] existingValues = Arrays.copyOf(storeValues, counter);
        formattingLine();
        System.out.println("Here are the tasks in your list:");
        for (Task value : existingValues) {
            System.out.println((currValue+1) + "." + value.toString());
            currValue += 1;
        }
        formattingLine();
    }

    private static void showGoodbye() {
        String bye = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(bye);
    }

    private static void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(hello);
    }

    private static void formattingLine() {
        String lineFormatting = "____________________________________________________________\n";
        System.out.println(lineFormatting);
    }
}
