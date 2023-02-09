import java.util.Scanner;

public class Duke {
    private final static String LINE_BREAK = " ____________________________________________________________";
    private final static String GREETING = "Hello! I'm Duke\n  What can I do for you?";
    private final static String EXITING = "Bye. Hope to see you again soon!";
    private final static String ERR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final static Tasks tasks = new Tasks();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        speak(GREETING);
        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            String[] commandByWord = command.split(" ");
            if (command.equals("bye")) {
                speak(EXITING);
                running = false;
            } else if (command.equals("list")) {
                speak(tasks.listTasks());
            } else if (commandByWord[0].equals("mark")) {
                speak(tasks.mark(Integer.parseInt(commandByWord[1])));
            } else if (commandByWord[0].equals("unmark")) {
                speak(tasks.unmark(Integer.parseInt(commandByWord[1])));
            } else if (commandByWord[0].equals("deadline")) {
                speak(tasks.addTask("deadline", commandByWord));
            } else if (commandByWord[0].equals("event")) {
                speak(tasks.addTask("event", commandByWord));
            } else if (commandByWord[0].equals("todo")) {
                speak(tasks.addTask("todo", commandByWord));
            } else {
                speak(ERR_MESSAGE);
            }
        }
    }

    static void speak(String message) {
        System.out.println(LINE_BREAK);
        System.out.println("  " + message);
        System.out.println(LINE_BREAK);
        System.out.println();
    }
}
