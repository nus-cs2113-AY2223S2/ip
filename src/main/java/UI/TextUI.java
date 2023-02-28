
package UI;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import Task.Deadline;
import Task.Event;
import Task.Task;
import TaskManager.TaskManager;
import static Constants.Messages.MESSAGE_ERROR_FORMAT_DATE;
import static Constants.Messages.MESSAGE_ERROR_FORMAT_DATE_TIME;
import static Constants.Messages.MESSAGE_ERROR_FORMAT_DEADLINE;
import static Constants.Messages.MESSAGE_ERROR_FORMAT_EVENT;
import static Constants.Messages.MESSAGE_ERROR_GENERAL;
import static Constants.Messages.MESSAGE_ERROR_LOADING;
import static Constants.Messages.MESSAGE_ERROR_TASK_INDEX_NOT_INTEGER;
import static Constants.Messages.MESSAGE_ERROR_TASK_INDEX_OUT_OF_BOUNDS;
import static Constants.Messages.MESSAGE_GOODBYE;
import static Constants.Messages.MESSAGE_TASK_LIST;
import static Constants.Messages.MESSAGE_TASK_LIST_EMPTY;
import static Constants.Messages.MESSAGE_PROMPT_EMPTY_ERROR;
import static Constants.Messages.MESSAGE_TASK_ADD;
import static Constants.Messages.MESSAGE_TASK_COUNT;
import static Constants.Messages.MESSAGE_TASK_MARK;
import static Constants.Messages.MESSAGE_TASK_REMOVE;
import static Constants.Messages.MESSAGE_TASK_UNMARK;
import static Constants.Messages.MESSAGE_WELCOME_INTRODUCTION;
import static Constants.Messages.MESSAGE_WELCOME_QUESTION;

public class TextUI {
    private final InputStream in;
    private final PrintStream out;

    private static final String DIVIDER =
            "____________________________________________________________";
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    public TextUI() {
        this(System.in, System.out);
    }

    public TextUI(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    

    public void printTaskContainingKeyword(int indexTask, Task task, String keyword) {
        if (task.getTaskDescription().contains(keyword)) {
            showMessage(indexTask + ". " + task.toString());
        }
    }

    public void showCommandNotFoundError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_GENERAL,
                DIVIDER);
    }

    public void showDeleteTaskMessage(TaskManager tasks, Task task) {
        String numTask = ((Integer) tasks.getNumTask()).toString();
        showMessage(
                DIVIDER,
                MESSAGE_TASK_REMOVE,
                task.toString(),
                MESSAGE_TASK_COUNT.replace("{number}", numTask),
                DIVIDER);

    }

    public void showGoodbye() {
        showMessage(
                DIVIDER,
                MESSAGE_GOODBYE,
                DIVIDER);
    }

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

    public void showIntroduction() {
        showMessage(
                DIVIDER,
                MESSAGE_WELCOME_INTRODUCTION,
                MESSAGE_WELCOME_QUESTION,
                DIVIDER);
    }

    public void showLoadingError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_LOADING,
                DIVIDER);

    }

    public void showMessage(String... message) {
        for (String m : message) {
            out.println("\t" + m);
        }
    }

    public void showNewTaskMessage(TaskManager tasks, Task task) {
        String numTask = ((Integer) tasks.getNumTask()).toString();
        showMessage(
                DIVIDER,
                MESSAGE_TASK_ADD,
                task.toString(),
                MESSAGE_TASK_COUNT.replace("{number}", numTask),
                DIVIDER);
    }

    public void showPromptEmptyErrorMessage(String taskType) {
        String messageError = MESSAGE_PROMPT_EMPTY_ERROR.replace("{taskType}", taskType);
        showMessage(
                DIVIDER,
                messageError,
                DIVIDER);
    }

    public void showTaskIndexNotIntegerError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_TASK_INDEX_NOT_INTEGER,
                DIVIDER);
    }

    public void showTaskIndexOutOfBoundsError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_TASK_INDEX_OUT_OF_BOUNDS,
                DIVIDER);
    }

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

    public void showWrongDateFormatError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_FORMAT_DATE,
                DIVIDER);
    }

    public void showWrongDateTimeFormatError() {
        showMessage(
                DIVIDER,
                MESSAGE_ERROR_FORMAT_DATE_TIME,
                DIVIDER);

    }

    public String scanLine() {
        Scanner sc = new Scanner(in);
        String line = sc.nextLine();
        return line;
    }


}