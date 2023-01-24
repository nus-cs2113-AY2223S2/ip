import java.util.Scanner;

public class Duke {
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
                chatOutput = "\t" + input;
            }
            String horizontalLine = "\t____________________________________________________________\n";
            System.out.println(horizontalLine + chatOutput + System.lineSeparator() + horizontalLine);
        } while (shouldContinueChat);
    }
}
