package duke;

public class Ui {
    private static final String LOGO = "   _____  .__   _____                   .___\n" +
            "  /  _  \\ |  |_/ ____\\______   ____   __| _/\n" +
            " /  /_\\  \\|  |\\   __\\\\_  __ \\_/ __ \\ / __ |\n" +
            "/    |    \\  |_|  |   |  | \\/\\  ___// /_/ |\n" +
            "\\____|__  /____/__|   |__|    \\___  >____ |\n" +
            "        \\/                        \\/     \\/\n";
    private static final String WELCOME_MESSAGE = "Hello from\n";
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = " Hello! I'm Alfred Pennyworth.\n What can I do for you?\n";
    private static final String ENDING = " Bye. Hope to see you again soon!\n";
    private static final String ADDED_TASK = "Got it. I've added this task:\n  ";
    private static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done: ";
    private static final String UNMARKED_THIS_TASK_AS_DONE = "Okay, I've marked this task as not done yet: ";
    private static final String DELETED_THIS_TASK = "Noted. I've removed this task:";


    /**
     * Prints welcome message and logo
     */
    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE + LOGO);
        System.out.println(DIVIDER + GREETINGS + DIVIDER);

    }

    /**
     * Prints ending message with divider
     */
    public void showEndingMessage() {
        System.out.println(ENDING + DIVIDER);
    }

    /**
     * Prints the details of the new task and the new number of tasks in the list
     *
     * @param t The task to add
     */
    public void printAddTaskMessage(Task t) {
        System.out.println(ADDED_TASK + t + "\nNow you have " + t.getNumberOfTasks() + " tasks in the list.");
    }

    public void printMarkedMessage(Task task) {
        System.out.println(MARKED_THIS_TASK_AS_DONE + "\n" + task);
    }

    public void printUnmarkedMessage(Task task) {
        System.out.println(UNMARKED_THIS_TASK_AS_DONE + "\n" + task);
    }

    /**
     * Prints details of the deleted task and update how many task is left
     *
     * @param taskDescription Description of the deleted task
     * @param taskLeft Count of the number of tasks left
     */
    public void printDeleteTaskMessage(String taskDescription, int taskLeft) {
        System.out.println(DELETED_THIS_TASK + "\n" + taskDescription + "\nNow you have " + taskLeft + " tasks in the list.");
    }

}
