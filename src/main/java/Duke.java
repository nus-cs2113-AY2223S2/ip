import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        //level-2
        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?\n");
        System.out.println("    ____________________________________");

        String[] userInputs = new String[100];
        int index = 0;

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String text = userInput.nextLine();
            if ("bye".equalsIgnoreCase(text)) {
                break;
            }

            if ("list".equalsIgnoreCase(text)) {
                for (int i = 0; i < userInputs.length; i += 1) {
                    if (userInputs[i] == null) {
                        break;
                    }
                    System.out.println((i+1) + ". " + userInputs[i]);
                }
                System.out.println("    ____________________________________");
            } else {
                userInputs[index] = text;

                System.out.println("    ____________________________________");
                System.out.println("     added: " + userInputs[index]);
                System.out.println("    ____________________________________");

                index += 1;
            }
        }

        System.out.println("____________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________");
    }
}
