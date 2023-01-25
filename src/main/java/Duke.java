import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userCommand;
        Scanner in = new Scanner(System.in);

        String[] userCommands = new String[0];

        Boolean isContinue = true;
        while (isContinue) {
            userCommand = in.nextLine();

            switch (userCommand) {

            case "list":
                for(int i = 0; i < userCommands.length; i++) {
                    System.out.println( (i+1) + ". " + userCommands[i]);
                }
                break;

            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isContinue = false;
                break;

            default:
                userCommands = Arrays.copyOf(userCommands, userCommands.length+1);
                userCommands[userCommands.length-1] = userCommand;
                System.out.println("added: " + userCommand);
                break;
            }
        }
    }
}