package duke.ui;

import duke.task.TaskList;

public abstract class Ui {
    private static String getTaskCountString(int taskListSize) {
        return String.format(Messages.TASK_COUNT.MESSAGE, taskListSize, taskListSize > 1 ? "s" : "");
    }

    public static void print(String... strings) {
        System.out.println(Messages.LINE.MESSAGE);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(Messages.LINE.MESSAGE);
    }

    public static void printStartMessage() {
        print(Messages.START.MESSAGE);
    }

    public static void printExitMessage() {
        print(Messages.EXIT.MESSAGE);
    }

    public static void printAddTaskMessage(String taskString, TaskList taskList) {
        Ui.print(Messages.ADD_TASK.MESSAGE, taskString, getTaskCountString(taskList.size()));
    }

    public static void printDeleteTaskMessage(String taskString, TaskList taskList) {
        Ui.print(Messages.DELETE_TASK.MESSAGE, taskString, getTaskCountString(taskList.size()));
    }

    public static void printMarkTaskMessage(String taskString, boolean isDone) {
        Ui.print(isDone ? Messages.MARK_TASK.MESSAGE : Messages.UNMARK_TASK.MESSAGE, taskString);
    }

    public static void printTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            print(Messages.EMPTY_LIST.MESSAGE);
        } else {
            print(Messages.LIST_TASKS.MESSAGE, taskList.toString());
        }
    }

    public static void printError(String errorMessage, String command) {
        switch (command) {
        case "todo":
            print(errorMessage, Messages.TODO_HELP.MESSAGE);
            return;
        case "deadline":
            print(errorMessage, Messages.DEADLINE_HELP.MESSAGE);
            return;
        case "event":
            print(errorMessage, Messages.EVENT_HELP.MESSAGE);
            return;
        case "mark":
            print(errorMessage, Messages.MARK_HELP.MESSAGE);
            return;
        case "unmark":
            print(errorMessage, Messages.UNMARK_HELP.MESSAGE);
            return;
        case "delete":
            print(errorMessage, Messages.DELETE_HELP.MESSAGE);
            return;
        case "save":
            print(errorMessage, Messages.SAVE_HELP.MESSAGE);
            return;
        default:
            print(errorMessage, Messages.GENERIC_HELP.MESSAGE);
        }
    }
}
