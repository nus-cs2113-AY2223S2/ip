import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Greeting and Exiting instantly
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        String exit = "Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";
        System.out.println(line + greet); // Duke saying hello
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                System.out.print(line + exit + line); // Duke saying goodbye and closes program
                break;
            }
            System.out.println(line + command + System.lineSeparator() + line); // Duke Echoing back commands
        }
    }
}
