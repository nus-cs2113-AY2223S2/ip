package Duke;

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

            switch (commandByWord[0]) {
            case ("bye"):
                speak(EXITING);
                running = false;
                break;
            case ("list"):
                speak(tasks.listTasks());
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
            default:
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
