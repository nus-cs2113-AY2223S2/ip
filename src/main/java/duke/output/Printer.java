package duke.output;

import duke.task.Task;

public class Printer {
    public static void greeting() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.START_MESSAGE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
    }

    public static void bye() {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        System.out.println(StandardOutput.BYE_MESSAGE.STANDARD_OUTPUT);
        System.out.print(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
    }

    public static void listTasks(Task[] tasks) {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        boolean isTaskCountZero = (Task.totalTasks == 0);
        if (isTaskCountZero) {
            System.out.println(StandardOutput.EMPTY_LIST_MESSAGE.STANDARD_OUTPUT);
        } else {
            System.out.println(StandardOutput.LIST_MESSAGE.STANDARD_OUTPUT);
            for (int i = 0; i < Task.totalTasks; i += 1) {
                System.out.println(" " + (i + 1) + "." + tasks[i].getFullTaskDetail());
            }
        }
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
    }

    public static void markOrUnmark(Task[] tasks, int taskIndex) {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        if (tasks[taskIndex].isDone) {
            System.out.println(StandardOutput.MARK_MESSAGE.STANDARD_OUTPUT);
        } else {
            System.out.println(StandardOutput.UNMARK_MESSAGE.STANDARD_OUTPUT);
        }
        System.out.println("   " + tasks[taskIndex].getFullTaskDetail());
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
    }

    public static void echoAddTasks(Task[] tasks) {
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT);
        int numberOfTasks = Task.totalTasks + 1;
        System.out.printf(StandardOutput.ADD_MESSAGE.STANDARD_OUTPUT,
                tasks[Task.totalTasks].getFullTaskDetail(), numberOfTasks);
        System.out.println(StandardOutput.SEGMENT_LINE.STANDARD_OUTPUT + System.lineSeparator());
    }
}
