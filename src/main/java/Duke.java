import java.util.ArrayList;

public class Duke {

    public static void printWelcomeMessage() {
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");
    }

    private static void printEndingMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        TaskList taskList = Storage.initialisation();
        DukeManager dukeManager = new DukeManager(taskList);

        //Duke starts here
        Duke.printWelcomeMessage();
        dukeManager.run();
        Duke.printEndingMessage();
    }
}
