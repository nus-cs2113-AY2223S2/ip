import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Good day. YodaBot, I am.");
        System.out.println("Assistance, you need?");
        Scanner scanner = new Scanner(System.in);
        String[] stringList = new String[100]; //array to store strings
        int stringIndex = 0;
        while (true) {
            String command = scanner.nextLine(); //reads in input
            if (!command.equalsIgnoreCase("list") && !command.equalsIgnoreCase("bye")) {
                stringList[stringIndex] = command;
                ++stringIndex;
                System.out.println(command + " - Added, I have.");
            }
            if (command.equalsIgnoreCase("list")) {
                if (stringIndex == 0) {
                    System.out.println("Empty, list is.");
                }
                else {
                    System.out.println("As shown, list is:");
                    for (int j = 0; j < stringIndex; ++j) {
                        System.out.println((j + 1) + ". " + stringList[j]);
                    }
                }
            }
            if (command.equalsIgnoreCase("bye")) {
                System.out.println("See you soon, I hope. Goodbye.");
                break;
            }
        }
    }
}
