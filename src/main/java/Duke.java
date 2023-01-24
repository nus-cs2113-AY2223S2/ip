import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int numOfTasks = 0;

    public static String getEachItemLine(int itemNumber) {
        String icon = tasks[itemNumber].getStatusIcon();
        String output;
        output = ("[" + icon + "] " + tasks[itemNumber].description);
        return output;
    }
    public static String getResponse(String input) {
        String output;
        if (input.equals("list")) {
            output = "\tHere are the tasks in your list:\n";
            for (int i = 0; i < numOfTasks; i++) {
                output += "\t" + Integer.toString(i + 1) + "." + getEachItemLine(i);
                if (i != numOfTasks - 1) {
                    output += "\n";
                }
            }
        } else if (input.startsWith("mark")) {
            int itemIndex = Integer.parseInt(input.substring(5)) - 1;
            tasks[itemIndex].setTaskStatus(true);
            output = "Nice! I've marked this task as done:\n\t" + getEachItemLine(itemIndex);
        } else if (input.startsWith("unmark")) {
            int itemIndex = Integer.parseInt(input.substring(7)) - 1;
            tasks[itemIndex].setTaskStatus(false);
            output = "OK, I've marked this task as not done yet:\n\t" + getEachItemLine(itemIndex);
        } else {
            tasks[numOfTasks] = new Task(input);
            numOfTasks++;
            output = "\tadded: " + input;
        }
        return output;
    }
    public static void main(String[] args) {
        String greetMsg = "\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(greetMsg);

        String input;
        String chatOutput;
        Scanner in = new Scanner(System.in);
        boolean shouldContinueChat = true;
        do {
            input = in.nextLine();
            if (input.equals("bye")) {
                shouldContinueChat = false;
                chatOutput = "\tBye. Hope to see you again soon!";
            } else {
                chatOutput = getResponse(input);
            }
            String horizontalLine = "\t____________________________________________________________\n";
            System.out.println(horizontalLine + chatOutput + System.lineSeparator() + horizontalLine);
        } while (shouldContinueChat);
    }
}
