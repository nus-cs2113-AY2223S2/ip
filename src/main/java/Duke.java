import java.util.Scanner;
public class Duke {

    public static boolean isCompleted = false;

    public static void welcomeMessage() {
        separator();
        formatMessage("Hello! I'm Duke");
        formatMessage("What can I do for you?");
        separator();
    }

    public static void endingMessage() {
        separator();
        formatMessage("Bye. Hope to see you again soon!");
        separator();
    }

    public static void separator() {
        System.out.println("____________________________________________________________");
    }

    public static void formatMessage(String message) {
        String outputMessage = String.format("| %-57s|", message);
        System.out.println(outputMessage);
    }

    public static void checkInput(String inputMessage) {
        if (inputMessage.equalsIgnoreCase("bye")) {
            isCompleted = true;
            endingMessage();
        } else {
            separator();
            formatMessage(inputMessage);
            separator();
        }
    }

    public static void main(String[] args) {
        welcomeMessage();
        String line;
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            checkInput(line);
        } while (!isCompleted);
    }
}
