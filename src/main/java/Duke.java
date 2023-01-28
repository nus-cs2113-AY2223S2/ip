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
            } else {
                counter = addTask(storeValues, counter, line);
            }
            line = in.nextLine();
        }
    }

    private static int addTask(Task[] storeValues, int counter, String line) {
        formattingLine();
        System.out.println("added: " + line);
        formattingLine();
        //store value in line into list
        Task newInput = new Task(line);
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
                "[ ] " + storeValues[numToMark-1].description + "\n");
        formattingLine();
    }

    private static void markItem(Task[] storeValues, String line) {
        int length = line.length();
        String itemToMark = line.substring(5, length);
        int numToMark = Integer.parseInt(itemToMark);
        //mark the item as complete
        storeValues[numToMark-1].markAsDone();
        formattingLine();
        System.out.println("Nice! I've marked this task as done: \n" +
                "[X] " + storeValues[numToMark-1].description + "\n");
        formattingLine();
    }

    private static void printList(Task[] storeValues, int counter) {
        int currValue = 0;
        Task[] existingValues = Arrays.copyOf(storeValues, counter);
        formattingLine();
        for (Task value : existingValues) {
            System.out.println((currValue+1) + ".[" + value.getStatusIcon() + "] " + value.description);
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
