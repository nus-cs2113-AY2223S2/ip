import java.util.Scanner;

public class Duke {
    public static String DIVIDER_LINE = "______________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = DIVIDER_LINE
                + "Hello! I'm Duke\n"
                + "What can i do for you\n"
                + DIVIDER_LINE;
        System.out.println(greet);
        boolean shouldContinue = true;
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        String action;
        Task[] tasks = new Task[100];
        while (shouldContinue) {
            action = in.nextLine();
            if (action.equals("bye")) {
                System.out.println(DIVIDER_LINE + "Bye. Hope to see you again soon!\n" + DIVIDER_LINE);
                shouldContinue = false;
            }else if (action.equals("list")) {
                System.out.print(DIVIDER_LINE);
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println(Integer.toString(i + 1) + ". " +tasks[i].toString());
                }
                System.out.println(DIVIDER_LINE);
            }else if (action.startsWith("mark")){
                int dividerPos = action.indexOf(" ");
                int toBeMarked = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
                tasks[toBeMarked].markAsDone();
                System.out.println(DIVIDER_LINE + "Nice! I've marked this task as done:\n"
                        + tasks[toBeMarked].toString() + "\n" + DIVIDER_LINE);
            }else if (action.startsWith("unmark")) {
                int dividerPos = action.indexOf(" ");
                int toBeUnmarked = Integer.parseInt(action.substring(dividerPos + 1)) - 1;
                tasks[toBeUnmarked].markAsUndone();
                System.out.println(DIVIDER_LINE + "Nice! I've marked this task as undone:\n"
                        + tasks[toBeUnmarked].getStatusIcon() + " " + tasks[toBeUnmarked].description
                        + "\n" + DIVIDER_LINE);
            }else if (action.startsWith("todo")){
                tasks[taskCount] = new Task(action.substring(5));
                System.out.println(DIVIDER_LINE + "added:\n" + tasks[taskCount].toString() + "\n" + DIVIDER_LINE);
                taskCount += 1;
                printNumTask(taskCount);
            }else if (action.startsWith("deadline")) {
                int dividerPosition = action.indexOf("/by");
                tasks[taskCount] = new Deadline(action.substring(9,dividerPosition - 1),
                        action.substring(dividerPosition + 4));
                System.out.println(DIVIDER_LINE + "added:\n" + tasks[taskCount].toString() + "\n" + DIVIDER_LINE);
                taskCount += 1;
                printNumTask(taskCount);
            }else if (action.startsWith("event")) {
                int dividerPosition1 = action.indexOf("/from");
                int dividerPosition2 = action.indexOf("/to");
                tasks[taskCount] = new Event(action.substring(6,dividerPosition1 - 1),
                        action.substring(dividerPosition1 + 6, dividerPosition2 - 1),
                        action.substring(dividerPosition2 + 4));
                System.out.println(DIVIDER_LINE + "added:\n" + tasks[taskCount].toString() + "\n" + DIVIDER_LINE);
                taskCount += 1;
                printNumTask(taskCount);
            }
        }
    }

    public static void printNumTask(int taskCount) {
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }
}
