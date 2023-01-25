
import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userCommand;
        Scanner in = new Scanner(System.in);

        Boolean isContinue = true;
        while (isContinue) {
            userCommand = in.nextLine();

            switch (userCommand) {

            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isContinue = false;
                break;

            default:
                System.out.println(userCommand);
                break;
            }
        }
    }
}
