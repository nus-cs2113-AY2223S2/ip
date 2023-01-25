import java.util.Scanner;
// Now it is level 2


public class Duke {
    public static String[] requestedList = new String [100]; // The size of this list is initialize to be 100
    public static int listTailIndex = 0;

    public static void add(String line) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + line);
        requestedList[listTailIndex] = line;
        listTailIndex++;
        System.out.println("____________________________________________________________");
    }

    public static void list() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < listTailIndex; i++) {
            System.out.print(i + 1);
            System.out.println(". " + requestedList[i]);
        }
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
            if (line.equals("list")) {
                list();
            } else {
                add(line);
            }
            line = in.nextLine();
        }

        // Bye and terminate the program
        bye();

    }
}
