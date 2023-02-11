package duke.output;

import duke.task.Task;

import java.util.ArrayList;

public class Printer {
    public static void endLine() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
    }

    public static void greeting() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.START_MESSAGE.STANDARD_OUTPUT);
        endLine();
    }

    public static void bye() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.BYE_MESSAGE.STANDARD_OUTPUT);
        endLine();
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        boolean isTaskCountZero = (Task.totalTasks == 0);
        if (isTaskCountZero) {
            System.out.println(StandardOutput.EMPTY_LIST_MESSAGE.STANDARD_OUTPUT);
        } else {
            System.out.println(StandardOutput.LIST_MESSAGE.STANDARD_OUTPUT);
            for (int i = 0; i < Task.totalTasks; i += 1) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).getFullTaskDetail());
            }
        }
        endLine();
    }

    public static void invalidMarkCommand() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INVALID_MARK_MESSAGE.STANDARD_OUTPUT);
    }

    public static void exceedTaskCount() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.printf(StandardOutput.OVER_TASK_COUNT_MESSAGE.STANDARD_OUTPUT, Task.getTotalTasks()
                + System.lineSeparator());
    }

    public static void invalidUnmarkCommand() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INVALID_UNMARK_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidInput() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INVALID_INPUT_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidTodo() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INVALID_TODO_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidDeadline() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.MISSING_DEADLINE_KEYWORD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void insufficientDeadline() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INSUFFICIENT_DEADLINE_FIELD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidEvent() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INVALID_EVENT_FORMAT_MESSAGE.STANDARD_OUTPUT);
    }

    public static void insufficientEvent() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INSUFFICIENT_EVENT_FIELD_MESSAGE.STANDARD_OUTPUT);
    }

    public static void invalidDeleteCommand() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.INVALID_DELETE_COMMAND_MESSAGE.STANDARD_OUTPUT);
    }

    public static void markOrUnmark(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        if (tasks.get(taskIndex).isDone) {
            System.out.println(StandardOutput.MARK_MESSAGE.STANDARD_OUTPUT);
        } else {
            System.out.println(StandardOutput.UNMARK_MESSAGE.STANDARD_OUTPUT);
        }
        System.out.println("   " + tasks.get(taskIndex).getFullTaskDetail());
        endLine();
    }

    public static void echoAddTasks(ArrayList<Task> tasks) {
        int numberOfTasks = Task.totalTasks + 1;
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.printf(StandardOutput.ADD_MESSAGE.STANDARD_OUTPUT,
                tasks.get(Task.totalTasks).getFullTaskDetail(), numberOfTasks);
        endLine();
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        int numberOfTasks = Task.totalTasks - 1;
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.DELETE_MESSAGE.STANDARD_OUTPUT);
        System.out.println("   " + tasks.get(taskIndex).getFullTaskDetail());
        System.out.printf(StandardOutput.REMAINING_TASK_COUNT.STANDARD_OUTPUT, numberOfTasks);
        endLine();
    }
}
