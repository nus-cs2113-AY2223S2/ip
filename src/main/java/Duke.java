import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        showGreetings();

        Scanner in = new Scanner (System.in);

        Task[] store_values = new Task[100];
        int counter = 0;

        String line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(store_values, counter);
            } else if (line.startsWith("mark ")) {
                markItem(store_values, line);
            } else if (line.startsWith("unmark ")) {
                unmarkItem(store_values, line);
            } else {
                counter = addTask(store_values, counter, line);
            }
            line = in.nextLine();
        }
        showGoodbye();
    }

    private static int addTask(Task[] store_values, int counter, String line) {
        formattingLine();
        System.out.println("added: " + line);
        formattingLine();
        //store value in line into list
        Task newInput = new Task(line);
        store_values[counter] = newInput;
        counter += 1;
        return counter;
    }

    private static void unmarkItem(Task[] store_values, String line) {
        int length = line.length();
        String item_to_mark = line.substring(7, length);
        int num_to_mark = Integer.parseInt(item_to_mark);
        //unmark the item
        store_values[num_to_mark-1].unmarkAsDone();
        formattingLine();
        System.out.println("OK, I've marked this task as not done yet: \n" +
                "[ ] " + store_values[num_to_mark-1].description + "\n");
        formattingLine();
    }

    private static void markItem(Task[] store_values, String line) {
        int length = line.length();
        String item_to_mark = line.substring(5, length);
        int num_to_mark = Integer.parseInt(item_to_mark);
        //mark the item as complete
        store_values[num_to_mark-1].markAsDone();
        formattingLine();
        System.out.println("Nice! I've marked this task as done: \n" +
                "[X] " + store_values[num_to_mark-1].description + "\n");
        formattingLine();
    }

    private static void printList(Task[] store_values, int counter) {
        int curr_value = 0;
        Task[] existing_values = Arrays.copyOf(store_values, counter);
        formattingLine();
        for (Task value : existing_values) {
            System.out.println((curr_value+1) + ".[" + value.getStatusIcon() + "] " + value.description);
            curr_value += 1;
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
        String formatting_line = "____________________________________________________________\n";
        System.out.println(formatting_line);
    }
}
