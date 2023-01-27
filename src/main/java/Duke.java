import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> userInput;

    public Duke() {
        this.userInput = new ArrayList<>();
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
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
            duke.userInput.add(input);
            if (input.equals("bye")) {
                break;
            }
            echoCmd(input);


        }
        exit();
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
