import java.util.Scanner;
// Now it is level 1


public class Duke {

    public static void echosCommands(String line) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + line);
        System.out.println("____________________________________________________________" + '\n');
    }

    public static void initialGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
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

        // Echos
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            echosCommands(line);
            line = in.nextLine();
        }

        // Bye and terminate the program
        bye();

    }
}
