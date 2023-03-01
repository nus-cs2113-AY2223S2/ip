package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    protected static final String TOP_DIVIDER = "============================================================\n";
    protected static final String BOTTOM_DIVIDER
            = "\n============================================================\n\n";
    protected static final String LIST_TASK_BOTTOM_DIVIDER
            = "============================================================\n\n";
    protected static final String WELCOME_MESSAGE = " Hello! I'm Chatty\n How can I help you?";
    protected static final String ADD_TASK_DESC = " Roger. I've added this task:\n  ";
    protected static final String CUR_NO_OF_TASK_START_DESC = "\n You currently have ";
    protected static final String CUR_NO_OF_TASK_END_DESC = " task(s) in the list.";
    protected static final String DELETE_TASK_DESC = " Roger. I've removed this task:\n  ";
    protected static final String MARK_TASK_DESC = " Good Job! I've marked this task as completed:\n  ";
    protected static final String UNMARK_TASK_DESC = " Noted, I have marked this task as incomplete:\n  ";
    protected static final String LIST_TASK_DESC = " Here are the tasks in your list:\n";
    protected static final String UNKNOWN_CMD_ERR = " WHOOPS! I'm sorry, but I don't know what that means :(";
    protected static final String EMPTY_TODO_DESC_ERR = " WHOOPS! The description of a todo cannot be empty.";
    protected static final String EMPTY_DEADLINE_DESC_ERR =
            " WHOOPS! The description/date/time of a deadline cannot be empty.";
    protected static final String EMPTY_EVENT_DESC_ERR =
            " WHOOPS! The description/dates/times of an event cannot be empty.";
    protected static final String EMPTY_TASK_NO_ERR = " WHOOPS! Task number cannot be empty.";
    protected static final String WRONG_TASK_NO_FORMAT_ERR = " WHOOPS! Task number must be an integer.";
    protected static final String TASK_NO_OUT_OF_RANGE_ERR = " WHOOPS! There is no such task number.";
    protected static final String EXIT_MESSAGE = " Goodbye. Hope to see you again soon!";

    public Ui() {

    }

    private void showToUser(String message) {
        System.out.print(TOP_DIVIDER + message + BOTTOM_DIVIDER);
    }

    public void showWelcomeMessage() {
        showToUser(WELCOME_MESSAGE);
    }

    public void showExitMessage() {
        showToUser(EXIT_MESSAGE);
    }

    public void showTaskAdded(ArrayList<Task> taskItems) {
        String taskAdded = ADD_TASK_DESC + taskItems.get(taskItems.size() - 1)
                + CUR_NO_OF_TASK_START_DESC + taskItems.size() + CUR_NO_OF_TASK_END_DESC;
        showToUser(taskAdded);
    }

    public void showTaskDeleted(ArrayList<Task> taskItems, int taskItemNo) {
        String taskDeleted = DELETE_TASK_DESC + taskItems.get(taskItemNo)
                + CUR_NO_OF_TASK_START_DESC + (taskItems.size() - 1) + CUR_NO_OF_TASK_END_DESC;
        showToUser(taskDeleted);
    }

    public void showTaskCompleted(ArrayList<Task> taskItems, int taskItemNo) {
        String taskCompleted = MARK_TASK_DESC + taskItems.get(taskItemNo);
        showToUser(taskCompleted);
    }

    public void showTaskIncomplete(ArrayList<Task> taskItems, int taskItemNo) {
        String taskIncomplete = TOP_DIVIDER + UNMARK_TASK_DESC + taskItems.get(taskItemNo) + BOTTOM_DIVIDER;
        showToUser(taskIncomplete);
    }

    public void listTask(ArrayList<Task> taskItems) {
        System.out.print(TOP_DIVIDER + LIST_TASK_DESC);

        for (int itemNo = 0; itemNo < taskItems.size(); itemNo++) {
            String printItem = " " + (itemNo + 1) + "." + taskItems.get(itemNo) + System.lineSeparator();
            System.out.print(printItem);
        }

        System.out.print(LIST_TASK_BOTTOM_DIVIDER);
    }

    public void showUnknownCmdErr() {
        showToUser(UNKNOWN_CMD_ERR);
    }

    public void showEmptyTodoDescErr() {
        showToUser(EMPTY_TODO_DESC_ERR);
    }

    public void showEmptyDeadlineDescErr() {
        showToUser(EMPTY_DEADLINE_DESC_ERR);
    }

    public void showEmptyEventDescErr() {
        showToUser(EMPTY_EVENT_DESC_ERR);
    }

    public void showEmptyTaskNoErr() {
        showToUser(EMPTY_TASK_NO_ERR);
    }

    public void showWrongTaskNoFormatErr() {
        showToUser(WRONG_TASK_NO_FORMAT_ERR);
    }

    public void showTaskNoOutOfRangeErr() {
        showToUser(TASK_NO_OUT_OF_RANGE_ERR);
    }
}
