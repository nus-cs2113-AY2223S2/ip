package duke.classes;

/**
 * The InputUi represents the user interface for displaying messages related to the task list operations
 */
public class InputUi {

    /** Task object that represents the task being operated on */
    protected Task task;

    /** Integer count that represents the number of tasks in the task list */
    protected int count;

    /**
     * Constructor that creates a new InputUi object with the given task and count.
     *
     * @param task the task object to be operated on
     * @param count the number of tasks in the task list
     */
    public InputUi(Task task, int count) {
        this.task = task;
        this.count = count;
    }

    /** Displays a message to indicate that the task has been marked as done */
    public void showMarkedTask() {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /** Displays a message to indicate that the task has been marked as not done */
    public void showUnmarkedTask() {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /** Displays a message to indicate that the task has been removed from the task list */
    public void showDeletedTask() {
        System.out.println("Noted, I've removed this task\n" + task);
    }

    /** Displays a message to indicate the current number of tasks in the task list */
    public void showRemainingTasks() {
        System.out.println("Now you have " + count + " tasks in the list");
    }

    /** Displays a message to indicate that the task has been added to the task list, as well as the current number of tasks in the task list */
    public void showTaskAdded() {
        System.out.println("Got it. I've added this task: \n" + task + "\nNow you have " + (count + 1) + " tasks in your list." );
    }
}
