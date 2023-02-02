import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line);
        Task[] task = new Task[100];
        int taskCount = 0;
        int errorCount = 0;
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.print(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (input.startsWith("mark")) {
                int mark = Integer.parseInt(input.substring(5));
                task[mark - 1].markDone();
                System.out.print(line);
            } else if (input.startsWith("unmark")) {
                int unmark = Integer.parseInt(input.substring(7));
                task[unmark - 1].umarkDone();
                System.out.print(line);
            } else if (input.equals("list")) {
                System.out.print(line + "Here are the tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + "." + task[i]);
                }
                System.out.print(line);
            } else if (input.startsWith("todo")) {
                task[taskCount] = new Todo(input.substring(5));
                taskCount++;
                System.out.println(line + "Got it. I've added this task:\n" + task[taskCount - 1]);
                System.out.print("Now you have " + taskCount + " tasks in the list.\n" + line);
            } else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                task[taskCount] = new Deadline(input.substring(9, byIndex), input.substring(byIndex + 4));
                taskCount++;
                System.out.println(line + "Got it. I've added this task:\n" + task[taskCount - 1]);
                System.out.print("Now you have " + taskCount + " tasks in the list.\n" + line);
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                task[taskCount] = new Event(input.substring(6, fromIndex), input.substring(fromIndex + 6, toIndex), input.substring(toIndex + 4));
                taskCount++;
                System.out.println(line + "Got it. I've added this task:\n" + task[taskCount - 1]);
                System.out.print("Now you have " + taskCount + " tasks in the list.\n" + line);
            } else if (errorCount == 10) {
                System.out.print(line +"Maybe you can try in another language. Goodbye!\n" + line);
                break;
            } else {
                System.out.print(line + "Sorry, could you please repeat what you just said\n" + line);
                errorCount++;
            }
        }
    }
}
