import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
    private static int numOfTasks = 0;
    public static String getResponse(String input) {
        String output = "";
        if (input.equals("list")) {
            for (int i = 0; i < numOfTasks; i++) {
                output += ("\t" + Integer.toString(i + 1) + ". " + tasks[i]);
                if (i != numOfTasks - 1) {
                    output += "\n";
                }
            }
        } else {
            tasks[numOfTasks] = input;
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
        Boolean shouldContinueChat = true;
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
