import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        //level -1
        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?\n");
        System.out.println("    ____________________________________");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String text = userInput.nextLine();
            if ("bye".equalsIgnoreCase(text)) {
                break;
            }

            System.out.println("    ____________________________________");
            System.out.println("    " + text);
            System.out.println("    ____________________________________");
        }

        System.out.println("____________________________________");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________");
    }
}
