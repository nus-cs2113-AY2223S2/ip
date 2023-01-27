import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n"
                            + logo
                            + "Hello! I'm Duke!\n"
                            + "What I can do for you?\n"
                            + "____________________________________________________________\n"
                            );
        String userInput;
        String outputMessage;
        boolean exit = false;
        while (true) {
            userInput = scanner.nextLine();
            switch(userInput) {
                case "bye":
                    exit = true;
                    outputMessage = "Bye. Hope to see you again soon!";
                    break;
                default:
                    outputMessage = userInput;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + outputMessage);
            System.out.println("\t____________________________________________________________");
            if (exit) {
                break;
            }
        }

    }



}
