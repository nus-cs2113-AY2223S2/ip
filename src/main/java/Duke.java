import java.util.Scanner;

public class Duke {
    private static String lineBreak = " ____________________________________________________________";
    private static String greeting = "Hello! I'm Duke\n  What can I do for you?";
    private static String exiting = "Bye. Hope to see you again soon!";

    private static Tasks tasks = new Tasks();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        speak(greeting);
        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            if (command.equals("bye")) { // whats the difference between equals and ==
                speak(exiting);
                running = false;
            } else if (command.equals("list")) {
                speak(tasks.listTasks());
            } else {
                speak(tasks.addTask(command));
            }
        }
    }

    static void speak(String message) {
        System.out.println(lineBreak);
        System.out.println("  " + message);
        System.out.println(lineBreak);
        System.out.println();
    }
}
