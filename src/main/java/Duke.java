public class Duke {

    /**
     * Prints welcome message when user starts the Duke service
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints ending message when user terminates Duke service
     */
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
