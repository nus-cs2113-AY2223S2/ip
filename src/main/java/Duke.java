import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Greeting and Exiting instantly
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        String exit = "Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";
        System.out.println(line + greet); // Duke saying hello
        Scanner in = new Scanner(System.in);
        String[] commands = new String[100];
        int count = 0; // counter for how many commands
        while (true) {
            String command = in.nextLine();
            commands[count] = command;
            if (command.equals("bye")) {
                System.out.print(line + exit + System.lineSeparator() + line); // Duke saying goodbye and closes program
                break;
            }
            else if (command.equals("list")) { // List out all recorded commands
                System.out.print(line);
                for (int i = 0; i < count; ++i) {
                    System.out.println((i+1) + ". " + commands[i]);
                }
                System.out.print(line);
            }
            else {
                System.out.println(line + "added: " + command + System.lineSeparator() + line); // Duke Echoing back commands
                count++;
            }
        }
    }
}
