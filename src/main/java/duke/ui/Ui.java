package duke.ui;

import duke.command.*;
import duke.task.Task;

import java.util.ArrayList;

public class Ui { // deals with interactions with the user
    private static final String SEGMENT_LINE = "_".repeat(80);
    public static final String NEW_LINE = System.lineSeparator();
    private static final String START_MESSAGE = " Hello! I'm Duke" + NEW_LINE + " What can I do for you?";
    public static void endLine() {
        System.out.println(SEGMENT_LINE + NEW_LINE);
    }

    public static void greeting() {
        System.out.println(SEGMENT_LINE);
        System.out.println(START_MESSAGE);
        endLine();
    }

    public static void bye() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ExitCommand.BYE_MESSAGE);
        endLine();
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println(SEGMENT_LINE);
        boolean isTaskCountZero = (Task.totalTasks == 0);
        if (isTaskCountZero) {
            System.out.println(ListCommand.EMPTY_MESSAGE);
        } else {
            System.out.println(ListCommand.MESSAGE);
            for (int i = 0; i < Task.totalTasks; i += 1) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).getFullTaskDetail());
            }
        }
        endLine();
    }

    public static void invalidMarkCommand() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_MARK_MESSAGE.STANDARD_OUTPUT);
    }

    public static void exceedTaskCount() {
        System.out.println(SEGMENT_LINE);
        System.out.printf(ErrorMessages.OVER_TASK_COUNT_MESSAGE.STANDARD_OUTPUT, Task.getTotalTasks()
                + NEW_LINE);
    }

    public static void invalidUnmarkCommand() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_UNMARK_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidInput() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_INPUT_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidTodo() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_TODO_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidDeadline() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.MISSING_DEADLINE_KEYWORD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void insufficientDeadline() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INSUFFICIENT_DEADLINE_FIELD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidEvent() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_EVENT_FORMAT_MESSAGE.STANDARD_OUTPUT);
    }

    public static void insufficientEvent() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INSUFFICIENT_EVENT_FIELD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidDeleteCommand() {
        System.out.println(SEGMENT_LINE);
        System.out.println(DeleteCommand.INVALID_COMMAND_MESSAGE);
    }

    public static void markOrUnmark(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(SEGMENT_LINE);
        if (tasks.get(taskIndex).isDone) {
            System.out.println(MarkCommand.MESSAGE);
        } else {
            System.out.println(UnmarkCommand.MESSAGE);
        }
        System.out.println("   " + tasks.get(taskIndex).getFullTaskDetail());
        endLine();
    }

    public static void echoAddTasks(ArrayList<Task> tasks) {
        int numberOfTasks = Task.totalTasks + 1;
        System.out.println(SEGMENT_LINE);
        System.out.printf(AddCommand.ADD_MESSAGE, tasks.get(Task.totalTasks).getFullTaskDetail(), numberOfTasks);
        endLine();
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        int numberOfTasks = Task.totalTasks - 1;
        System.out.println(SEGMENT_LINE);
        System.out.println(DeleteCommand.OUTPUT_MESSAGE);
        System.out.println("   " + tasks.get(taskIndex).getFullTaskDetail());
        System.out.printf(DeleteCommand.REMAINING_TASK_COUNT, numberOfTasks);
        endLine();
    }

    public static void help() {
        System.out.println(SEGMENT_LINE);
        System.out.println(AddCommand.TODO_MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + AddCommand.DEADLINE_MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + AddCommand.EVENT_MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + ListCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + MarkCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + UnmarkCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + DeleteCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + HelpCommand.MESSAGE_USAGE
                + Ui.NEW_LINE + Ui.NEW_LINE + ExitCommand.MESSAGE_USAGE);
        endLine();
    }
}
