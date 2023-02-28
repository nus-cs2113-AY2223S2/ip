import java.util.Scanner;

/**
 * Represents a collection of messages used by the Duke ChatBot to interact with
 * the user.
 */
public class Ui {
    public static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    /**
     * Prints a greetings message for the user when they first start the Duke ChatBot.
     */
    public void showGreetings() {
        String greetMsg = HORIZONTAL_LINE
                + System.lineSeparator()
                + "\tHello! I'm Duke"
                + System.lineSeparator()
                + "\tWhat can I do for you?"
                + System.lineSeparator()
                + HORIZONTAL_LINE;
        System.out.println(greetMsg);
    }

    /**
     * Prints a farewell message for the user when they quit the Duke ChatBot.
     */
    public void showFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Reads one line from stdin.
     * @return The line read from stdin
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the current number of tasks being tracked in the list.
     * @param taskList The TaskList object containing the array of tracked tasks
     */
    public void showCurrNumOfTask(TaskList taskList) {
        if (taskList.numOfTasks == 1) {
            System.out.println("\tNow you have " + taskList.numOfTasks + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskList.numOfTasks + " tasks in the list.");
        }
    }

    /**
     * Prints the current task that is chosen by the user for certain operations.
     * @param taskList The TaskList object containing the array of tracked tasks
     * @param taskIndex Index of the current task in the array of tasks
     */
    public void showCurrTask(TaskList taskList, int taskIndex) {
        System.out.println("\t\t" + taskList.tasks.get(taskIndex));
    }

    /**
     * Returns the current task that is chosen by the user for certain operations.
     * @param taskList The TaskList object containing the array of tracked tasks
     * @param taskIndex Index of the current task in the array of tasks
     * @return A string containing description of the current task
     */
    public String getCurrTask(TaskList taskList, int taskIndex) {
        return "\t\t" + taskList.tasks.get(taskIndex);
    }

    /**
     * Prints a message showing a task was added.
     */
    public void showTaskIsAdded() {
        System.out.println("\tGot it. I've added this task:");
    }

    /**
     * Prints a message showing a task was marked.
     */
    public void showTaskIsMarked() {
        System.out.println("\tNice! I've marked this task as done:");
    }

    /**
     * Prints a message showing a task was unmarked.
     */
    public void showTaskIsUnmarked() {
        System.out.println("\tOK, I've marked this task as not done yet:");
    }

    /**
     * Returns a message that a task was deleted.
     * @return The string containing the message
     */
    public String getTaskIsDeleted() {
        return "\tNoted. I've removed this task." + System.lineSeparator();
    }

    /**
     * Prints a message showing that tasks were saved successfully.
     */
    public void showSaveIsSuccessful() {
        System.out.println("\tTasks were saved successfully.");
    }

    /**
     * Prints a message showing that there are no tasks in the list.
     */
    public void showEmptyList() {
        System.out.println("\tThere are no tasks in your list currently!");
    }

    /**
     * Prints a message showing that no tasks matched the given keyword.
     */
    public void showNoMatches() {
        System.out.println("\tNo task in your list matches the keyword.");
    }

    /**
     * Prints an error message due to no keyword entered by user in command.
     */
    public void showFindError() {
        System.out.println("\tKeyword for find cannot be empty.");
    }
    
    /**
     * Prints an error message due to empty description of Todo task in user command.
     */
    public void showInvalidTodoError() {
        System.out.println("\tDescription of todo cannot be empty.");
    }

    /**
     * Prints an error message due to invalid command format for Deadline task.
     */
    public void showInvalidDeadlineError() {
        System.out.println("\tInvalid Command. There may be some missing deadline fields.");
    }

    /**
     * Prints an error message due to invalid command format for Event task.
     */
    public void showInvalidEventError() {
        System.out.println("\tInvalid Command. There may be some missing event fields.");
    }

    /**
     * Prints an error message due to invalid command format for marking a task.
     */
    public void showMarkError() {
        System.out.println("\tSorry, you need to state a task number after 'mark'.");
    }

    /**
     * Prints an error message due to invalid command format for unmarking a task.
     */
    public void showUnmarkError() {
        System.out.println("\tSorry, you need to state a task number after 'unmark'.");
    }

    /**
     * Prints an error message due to invalid command format for deleting a task.
     */
    public void showDeleteError() {
        System.out.println("\tSorry, you need to state a task number after 'delete'.");
    }

    /**
     * Prints an error message due to invalid task index quoted by user.
     */
    public void showInvalidTaskNumberError() {
        System.out.println("\tSorry, the task does not exist.");
    }

    /**
     * Prints an error message due to problems with creating the tasks.txt file.
     */
    public void showCreateFileError() {
        System.out.println("An error occurred when creating the data file.");
    }

    /**
     * Prints an error message due to problems with locating the tasks.txt file.
     */
    public void showFileLoadingError() {
        System.out.println("\tData file was not found.");
    }

    /**
     * Prints an error message due to problems with saving tasks to tasks.txt.
     */
    public void showSavingError() {
        System.out.println("\tData was not saved to file.");
    }

    /**
     * Prints an error message due to unrecognised command format by user.
     */
    public void showCommandError() {
        System.out.println("\tInvalid Command.");
    }
}
