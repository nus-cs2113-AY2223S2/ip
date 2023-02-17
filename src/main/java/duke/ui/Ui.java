package duke.ui;

import duke.command.*;
import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui { // deals with interactions with the user
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() { // todo
        String input = in.nextLine();
        return input;
    }

    private static final String SEGMENT_LINE = "_".repeat(80);
    public static final String NEW_LINE = System.lineSeparator();
    private static final String START_MESSAGE = " Hello! I'm Duke" + NEW_LINE + " What can I do for you?";
    public static void endLine() {
        System.out.println(SEGMENT_LINE + NEW_LINE);
    }

    public static void showGreeting() {
        System.out.println(SEGMENT_LINE);
        System.out.println(START_MESSAGE);
        endLine();
    }

    public static void showBye() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ExitCommand.BYE_MESSAGE);
        endLine();
    }

    public static void showTaskList(TaskList taskList) {
        System.out.println(SEGMENT_LINE);
        boolean isTaskCountZero = (Task.totalTasks == 0);
        if (isTaskCountZero) {
            System.out.println(ListCommand.EMPTY_MESSAGE);
        } else {
            System.out.println(ListCommand.MESSAGE);
            for (int i = 0; i < Task.totalTasks; i += 1) {
                System.out.println(" " + (i + 1) + "." + taskList.getTaskFullDetails(i));
            }
        }
        endLine();
    }

    public static void showInvalidMark() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_MARK_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showExceedTask() {
        System.out.println(SEGMENT_LINE);
        System.out.printf(ErrorMessages.OVER_TASK_COUNT_MESSAGE.STANDARD_OUTPUT, Task.getTotalTasks()
                + NEW_LINE);
    }

    public static void showInvalidUnmark() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_UNMARK_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInvalidInput() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_INPUT_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInvalidTodo() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_TODO_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInvalidDeadline() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.MISSING_DEADLINE_KEYWORD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInsufficientDeadline() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INSUFFICIENT_DEADLINE_FIELD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInvalidEvent() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INVALID_EVENT_FORMAT_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInsufficientEvent() {
        System.out.println(SEGMENT_LINE);
        System.out.println(ErrorMessages.INSUFFICIENT_EVENT_FIELD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void showInvalidDelete() {
        System.out.println(SEGMENT_LINE);
        System.out.println(DeleteCommand.INVALID_COMMAND_MESSAGE);
    }

    public static void showTaskStatus(TaskList taskList, int taskIndex) {
        System.out.println(SEGMENT_LINE);
        if (taskList.getIsDone(taskIndex)) {
            System.out.println(MarkCommand.MESSAGE);
        } else {
            System.out.println(UnmarkCommand.MESSAGE);
        }
        System.out.println("   " + taskList.getTaskFullDetails(taskIndex));
        endLine();
    }

    public static void showAddTask(TaskList taskList) {
        int numberOfTasks = Task.totalTasks + 1;
        System.out.println(SEGMENT_LINE);
        System.out.printf(AddCommand.ADD_MESSAGE, taskList.getTaskFullDetails(Task.totalTasks), numberOfTasks);
        endLine();
    }

    public static void showDeleteTask(TaskList taskList, int taskIndex) {
        int numberOfTasks = Task.totalTasks - 1;
        System.out.println(SEGMENT_LINE);
        System.out.println(DeleteCommand.OUTPUT_MESSAGE);
        System.out.println("   " + taskList.getTaskFullDetails(taskIndex));
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
