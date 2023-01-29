import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukePrinter.printWelcomeMessage();
        String user_input = scanner.nextLine();
        while (!Objects.equals(user_input, "bye")) {
            DukePrinter.printMessage(user_input);
            user_input = scanner.nextLine();
        }
        DukePrinter.printFarewellMessage();
    }
}
