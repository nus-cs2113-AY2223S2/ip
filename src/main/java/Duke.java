import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public Duke() {

    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        ArrayList<String> userInput = new ArrayList<>();
        boolean isRun = true;
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                list(userInput);
            }
            //echoCmd(input);
            addToList(input, userInput);


        }
        exit();
    }
    public static void addToList(String cmd, ArrayList<String> userInput) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + cmd);
        System.out.println("\t____________________________________________________________");
        userInput.add(cmd);
    }

    public static void list(ArrayList<String> userInput) {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < userInput.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + userInput.get(i));
        }
        System.out.println("\t____________________________________________________________");

    }
    public static void echoCmd(String cmd) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + cmd);
        System.out.println("\t____________________________________________________________");

    }

    public static void greetUser() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }

}
