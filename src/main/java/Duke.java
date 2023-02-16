import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String DIVIDER = "____________________________________________________\n";
        System.out.println("Hello from\n" + LOGO);
        System.out.print(DIVIDER + "Hello! I'm Duke\n" + "What can I do for you?\n" + DIVIDER);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        int errorCount = 0;
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.print(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
                break;
            } else if (input.startsWith("mark")) {
                int mark = Integer.parseInt(input.substring(5));
                tasks[mark - 1].markDone();
                System.out.print(DIVIDER);
            } else if (input.startsWith("unmark")) {
                int unmark = Integer.parseInt(input.substring(7));
                tasks[unmark - 1].umarkDone();
                System.out.print(DIVIDER);
            } else if (input.equals("list")) {
                System.out.print(DIVIDER + "Here are the tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + "." + tasks[i]);
                }
                System.out.print(DIVIDER);
            } else if (input.startsWith("todo")) {
                tasks[taskCount] = new Todo(input.substring(5));
                taskCount++;
                System.out.println(DIVIDER + "Got it. I've added this tasks:\n" + tasks[taskCount - 1]);
                System.out.print("Now you have " + taskCount + " tasks in the list.\n" + DIVIDER);
            } else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                tasks[taskCount] = new Deadline(input.substring(9, byIndex), input.substring(byIndex + 4));
                taskCount++;
                System.out.println(DIVIDER + "Got it. I've added this tasks:\n" + tasks[taskCount - 1]);
                System.out.print("Now you have " + taskCount + " tasks in the list.\n" + DIVIDER);
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                tasks[taskCount] = new Event(input.substring(6, fromIndex), input.substring(fromIndex + 6, toIndex), input.substring(toIndex + 4));
                taskCount++;
                System.out.println(DIVIDER + "Got it. I've added this tasks:\n" + tasks[taskCount - 1]);
                System.out.print("Now you have " + taskCount + " tasks in the list.\n" + DIVIDER);
            } else if (errorCount == 10) {
                System.out.print(DIVIDER +"Maybe you can try in another language. Goodbye!\n" + DIVIDER);
                break;
            } else {
                System.out.print(DIVIDER + "Sorry, could you please repeat what you just said\n" + DIVIDER);
                errorCount++;
            }
        }
    }
}
