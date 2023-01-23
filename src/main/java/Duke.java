import java.util.Scanner;

public class Duke {

    public static void echosCommands(String line) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + line);
        System.out.println("____________________________________________________________" + '\n');
    }


    public static void bye() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet the user
        initialGreeting();

        

        // Bye and terminate the program
        bye();

    }
}
