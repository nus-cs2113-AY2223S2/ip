import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "_________________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.print(line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line);
        while(true) {
            Scanner userInput = new Scanner(System.in);
            String name = userInput.nextLine();
            if (name.equals("bye")) {
                System.out.print("Bye. Hope to see you again soon!\n" + line);
                break;
            } else {
                System.out.print(line + name + "\n" + line);
            }
        }
    }
}
