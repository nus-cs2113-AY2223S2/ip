import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        String[] tasks = new String[100];
        int counter = 0;
        String line;
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (!line.equals("list")) {
                tasks[counter] = line;
                counter++;
                printLine();
                System.out.println("added: " + line);
                printLine();
            } else {
                printLine();
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printLine();
            }
            line = in.nextLine();
        }
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
