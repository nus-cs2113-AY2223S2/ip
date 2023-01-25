
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

            case "list":
                System.out.println("list");
                break;

            case "blah":
                System.out.println("blah");
                break;

            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isContinue = false;
                break;

            default:
                System.out.println("Sorry, I do not understand what you input");
                break;
            }
        }
    }
}
