package Duke;

import java.util.Scanner;

/**
 * Main class that represents the UI of the chatbot.
 * Responsible for communicating with the user via the command line.
 * This class is responsible for the IO of the chatbot.
 */
public class Duke {
    private final static String LINE_BREAK = " ____________________________________________________________";
    private final static String GREETING = "Hello! I'm Duke\n  What can I do for you?";
    private final static String EXITING = "Bye. Hope to see you again soon!";
    private final static String ERR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private final static Tasks tasks = Tasks.loadTasks();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        speak(GREETING);
        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            String[] commandByWord = command.split(" ");

            switch (commandByWord[0]) {
            case ("bye"):
                speak(EXITING);
                running = false;
                break;
            case ("list"):
                speak(tasks.listTasks("list"));
                break;
            case ("mark"):
                speak(tasks.mark(Integer.parseInt(commandByWord[1])));
                break;
            case ("unmark"):
                speak(tasks.unmark(Integer.parseInt(commandByWord[1])));
                break;
            case ("deadline"):
            case ("event"):
            case ("todo"):
                speak(tasks.addTask(commandByWord));
                break;
            case ("delete"):
                speak(tasks.deleteTask(commandByWord));
                break;
            case ("find"):
                speak(tasks.findTask(commandByWord));
                break;
            default:
                speak(ERR_MESSAGE);
            }
        }
    }

    /**
     * Void method responsible for generating System.out messages to the command line.
     *
     * @param message Message that has to be communicated to the user.
     */
    private static void speak(String message) {
        System.out.println(LINE_BREAK);
        System.out.println("  " + message);
        System.out.println(LINE_BREAK);
        System.out.println();
    }
}
