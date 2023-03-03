package duke.ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.commons.Messages.MESSAGE_ERROR_FORMAT_DATE;
import static duke.commons.Messages.MESSAGE_ERROR_FORMAT_DATE_TIME;
import static duke.commons.Messages.MESSAGE_ERROR_FORMAT_DEADLINE;
import static duke.commons.Messages.MESSAGE_ERROR_FORMAT_EVENT;
import static duke.commons.Messages.MESSAGE_ERROR_GENERAL;
import static duke.commons.Messages.MESSAGE_ERROR_LOADING;
import static duke.commons.Messages.MESSAGE_ERROR_TASK_INDEX_NOT_INTEGER;
import static duke.commons.Messages.MESSAGE_ERROR_TASK_INDEX_OUT_OF_BOUNDS;
import static duke.commons.Messages.MESSAGE_GOODBYE;
import static duke.commons.Messages.MESSAGE_TASK_LIST;
import static duke.commons.Messages.MESSAGE_TASK_LIST_EMPTY;
import static duke.commons.Messages.MESSAGE_PROMPT_EMPTY_ERROR;
import static duke.commons.Messages.MESSAGE_TASK_ADD;
import static duke.commons.Messages.MESSAGE_TASK_COUNT;
import static duke.commons.Messages.MESSAGE_TASK_MARK;
import static duke.commons.Messages.MESSAGE_TASK_REMOVE;
import static duke.commons.Messages.MESSAGE_TASK_UNMARK;
import static duke.commons.Messages.MESSAGE_WELCOME_INTRODUCTION;
import static duke.commons.Messages.MESSAGE_WELCOME_QUESTION;

/**
 * This class is responsible for handling the Text User Interface (UI) of the application.
 */

public class TextUi {
    private final InputStream in;
    private final PrintStream out;

    private static final String DIVIDER =
            "____________________________________________________________";
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    /**
     * Constructs a new TextUi object.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new TextUi object with the given input and output streams.
     *
     * @param in  The input stream.
     * @param out The output stream.
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Returns a formatted string of the given date.
     *
     * @param date The date to format.
     * @return The formatted date string.
     */
    public String getShowDateFormat(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    /**
     * Prints the given task if it contains the specified date.
     *
     * @param indexTask The index of the task to print.
     * @param task      The task to print.
     * @param date      The date to check against the task.
     */
    public void printTaskContainingDate(int indexTask, Task task, LocalDateTime date) {
        LocalDate startTime;
        LocalDate endTime;
        LocalDate currentDate = date.toLocalDate();
        switch (task.getTaskType()) {
        case "D":
            Deadline deadline = (Deadline) task;
            endTime = deadline.getEndTime().toLocalDate();
            if (endTime.equals(currentDate)) {
                showMessage(indexTask + ". " + task.toString());
            }
            break;
        case "E":
            Event event = (Event) task;
            startTime = event.getStartTime().toLocalDate();
            endTime = event.getEndTime().toLocalDate();
            if (startTime.equals(currentDate) || endTime.equals(currentDate)) {
                showMessage(indexTask + ". " + task.toString());
            }
            break;
        }
    }

    /**
     * Prints the given task if it contains the specified keyword.
     *
     * @param indexTask The index of the task to print.
     * @param task      The task to print.
     */
    public void printTaskContainingKeyword(int indexTask, Task task, String keyword) {
        if (task.getTaskDescription().contains(keyword)) {
            showMessage(indexTask + ". " + task.toString());
        }
    }

    /**
     * Displays an error message for an unrecognized command.
     */
    public void showCommandNotFoundError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_GENERAL,
                DIVIDER);
    }

    /**
     * Displays a message for a deleted task.
     *
     * @param tasks The task list that the task was deleted from.
     * @param task  The task that was deleted.
     */
    public void showDeleteTaskMessage(TaskList tasks, Task task) {
        String numTask = ((Integer) tasks.getNumTask()).toString();
        showMessage(
                DIVIDER,
                MESSAGE_TASK_REMOVE,
                task.toString(),
                MESSAGE_TASK_COUNT.replace("{number}", numTask),
                DIVIDER);

    }

    /**
     * Prints a goodbye message to the user.
     */
    public void showGoodbye() {
        showMessage(
                DIVIDER,
                MESSAGE_GOODBYE,
                DIVIDER);
    }

    /**
     * Shows an error message for an incorrect task format.
     *
     * @param taskType The type of task that was incorrectly formatted.
     */
    public void showIncorrectFormatMessage(String taskType) {
        String messageError;
        switch (taskType) {
        case "deadline":
            messageError = MESSAGE_ERROR_FORMAT_DEADLINE;
            break;
        case "event":
            messageError = MESSAGE_ERROR_FORMAT_EVENT;
            break;
        default:
            messageError = MESSAGE_ERROR_GENERAL;
            break;
        }
        showMessage(
                DIVIDER,
                messageError,
                DIVIDER);
    }

    /**
     * Shows a welcome message to the user.
     */
    public void showIntroduction() {
        showMessage(
                DIVIDER,
                MESSAGE_WELCOME_INTRODUCTION,
                MESSAGE_WELCOME_QUESTION,
                DIVIDER);
    }

    /**
     * Shows an error message when there is a problem loading the task list.
     */
    public void showLoadingError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_LOADING,
                DIVIDER);

    }

    /**
     * Displays one or more messages on the console.
     *
     * @param message one or more messages to be displayed
     */
    public void showMessage(String... message) {
        for (String m : message) {
            out.println("\t" + m);
        }
    }

    /**
     * Displays a message indicating that a new task has been added to the task list.
     *
     * @param tasks the current task list
     * @param task  the task that was added
     */

    public void showNewTaskMessage(TaskList tasks, Task task) {
        String numTask = ((Integer) tasks.getNumTask()).toString();
        showMessage(
                DIVIDER,
                MESSAGE_TASK_ADD,
                task.toString(),
                MESSAGE_TASK_COUNT.replace("{number}", numTask),
                DIVIDER);
    }

    /**
     * Displays an error message indicating that the user did not enter a proper (prompt) for a given command.
     *
     * @param taskType the type of task for which the user failed to provide a value
     */
    public void showPromptEmptyErrorMessage(String taskType) {
        String messageError = MESSAGE_PROMPT_EMPTY_ERROR.replace("{taskType}", taskType);
        showMessage(
                DIVIDER,
                messageError,
                DIVIDER);
    }

    /**
     * Displays an error message indicating that the user entered a non-integer value for a task index.
     */
    public void showTaskIndexNotIntegerError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_TASK_INDEX_NOT_INTEGER,
                DIVIDER);
    }

    /**
     * Displays an error message indicating that the user entered an out-of-bounds value for a task index.
     */
    public void showTaskIndexOutOfBoundsError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_TASK_INDEX_OUT_OF_BOUNDS,
                DIVIDER);
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @param tasks the task list to be displayed
     */
    public void showTaskList(ArrayList<Task> tasks) {
        showMessage(DIVIDER, MESSAGE_TASK_LIST);
        if (tasks.size() == 0) {
            showMessage(MESSAGE_TASK_LIST_EMPTY);
        } else {
            int indexTask = DISPLAYED_INDEX_OFFSET;
            for (Task task : tasks) {
                showMessage(indexTask + ". " + task.toString());
                indexTask++;
            }
        }
        showMessage(DIVIDER);
    }

    /**
     * Displays the list of tasks in the task list that contain the specified date.
     *
     * @param tasks the task list to be searched
     * @param date  the date to be searched for
     */
    public void showTaskListByDate(ArrayList<Task> tasks, LocalDateTime date) {
        showMessage(DIVIDER, MESSAGE_TASK_LIST);
        if (tasks.size() == 0) {
            showMessage(MESSAGE_TASK_LIST_EMPTY);
        } else {
            int indexTask = DISPLAYED_INDEX_OFFSET;
            for (Task task : tasks) {
                printTaskContainingDate(indexTask, task, date);
                indexTask++;
            }
        }
        showMessage(DIVIDER);
    }

    /**
     * Displays the list of tasks in the task list that contain the specified keyword.
     *
     * @param tasks   the task list to be searched
     * @param keyword the keyword to be searched for
     */
    public void showTaskListByKeyword(ArrayList<Task> tasks, String keyword) {
        showMessage(DIVIDER, MESSAGE_TASK_LIST);
        if (tasks.size() == 0) {
            showMessage(MESSAGE_TASK_LIST_EMPTY);
        } else {
            int indexTask = DISPLAYED_INDEX_OFFSET;
            for (Task task : tasks) {
                printTaskContainingKeyword(indexTask, task, keyword);
                indexTask++;
            }
        }
        showMessage(DIVIDER);
    }

    /**
     * Displays a message indicating the status of a given task.
     *
     * @param task The task to display the status message for.
     */
    public void showTaskStatusMessage(Task task) {
        String message;
        if (task.getStatus()) {
            message = MESSAGE_TASK_MARK;
        } else {
            message = MESSAGE_TASK_UNMARK;
        }
        showMessage(
                DIVIDER,
                message,
                task.toString(),
                DIVIDER);
    }

    /**
     * Displays an error message indicating that the date format used is incorrect.
     */
    public void showWrongDateFormatError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_FORMAT_DATE,
                DIVIDER);
    }

    /**
     * Displays an error message indicating that the date and time format used is incorrect.
     */
    public void showWrongDateTimeFormatError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_FORMAT_DATE_TIME,
                DIVIDER);

    }

    /**
     * Scans the input from the user and returns the scanned line as a string.
     *
     * @return The scanned line as a string.
     */
    public String scanLine() {
        Scanner sc = new Scanner(in);
        String line = sc.nextLine();
        return line;
    }


}
