import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static String[] userMessages = new String[100];
    private static int userMessageCount = 0;

    public static void addUserMessage(String userMessage) {
        userMessages[userMessageCount] = userMessage;
        userMessageCount += 1;
        DukePrinter.printMessage("added: " + userMessage);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukePrinter.printWelcomeMessage();
        String user_input = scanner.nextLine();
        while (!Objects.equals(user_input, "bye")) {
            switch (user_input) {
            case "list":
                DukePrinter.printMessages(userMessages, userMessageCount);
                break;
            default:
                addUserMessage(user_input);
                break;
            }
            user_input = scanner.nextLine();
        }
        DukePrinter.printFarewellMessage();
    }
}
