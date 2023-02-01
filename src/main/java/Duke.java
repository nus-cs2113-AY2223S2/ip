import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greetings();
        echoText();
        goodbye();
    }

    private static void echoText() {
        Scanner scanner = new Scanner(System.in);
        String echoInput = "";
        String[] storedText = new String[100];
        int counter = 0;

        while (!echoInput.equals("bye")) {
            echoInput = scanner.nextLine();
            if (echoInput.equals("bye")) {
                break;
            }
            if (echoInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i+1 + ". " + storedText[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else {
                storedText[counter] = echoInput;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + echoInput);
                System.out.println("____________________________________________________________");
                counter = counter + 1;
            }
        }
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
