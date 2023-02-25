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

public class TextUi {
    private final InputStream in;
    private final PrintStream out;

    private static final String DIVIDER =
            "____________________________________________________________";
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public String getShowDateFormat(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

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

    public void showDeleteTaskMessage(TaskList tasks, Task task) {
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

    public void showNewTaskMessage(TaskList tasks, Task task) {
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
