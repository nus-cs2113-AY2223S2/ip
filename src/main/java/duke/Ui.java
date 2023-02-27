package duke;

public class Ui {
    public static final String LOGO = "   _____  .__   _____                   .___\n" +
            "  /  _  \\ |  |_/ ____\\______   ____   __| _/\n" +
            " /  /_\\  \\|  |\\   __\\\\_  __ \\_/ __ \\ / __ |\n" +
            "/    |    \\  |_|  |   |  | \\/\\  ___// /_/ |\n" +
            "\\____|__  /____/__|   |__|    \\___  >____ |\n" +
            "        \\/                        \\/     \\/\n";
    public static final String WELCOME_MESSAGE = "Hello from\n";
    public static final String DIVIDER = "____________________________________________________________\n";
    public static final String GREETINGS = " Hello! I'm Alfred Pennyworth.\n What can I do for you?\n";
    public static final String ENDING = " Bye. Hope to see you again soon!\n";
    public static final String ADDED_TASK = "Got it. I've added this task:\n  ";
    public static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done: ";
    public static final String UNMARKED_THIS_TASK_AS_DONE = "Okay, I've marked this task as not done yet: ";
    public static final String DELETED_THIS_TASK = "Noted. I've removed this task:";


    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE + LOGO);
        System.out.println(DIVIDER + GREETINGS + DIVIDER);

    }

    public void showEndingMessage() {
        System.out.println(ENDING + DIVIDER);
    }

    public void printAddTaskMessage(Task t) {
        System.out.println(ADDED_TASK + t + "\nNow you have " + t.getNumberOfTasks() + " tasks in the list.");
    }

    public void printMarkedMessage(Task task) {
        System.out.println(MARKED_THIS_TASK_AS_DONE + "\n" + task);
    }

    public void printUnmarkedMessage(Task task) {
        System.out.println(UNMARKED_THIS_TASK_AS_DONE + "\n" + task);
    }

    public void printDeleteTaskMessage(String taskDescription, int taskLeft) {
        System.out.println(DELETED_THIS_TASK + "\n" + taskDescription + "\nNow you have " + taskLeft + " tasks in the list.");
    }

}
