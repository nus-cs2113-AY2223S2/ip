package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void doCommandMark(int taskNum) {
        Duke.isFileEdited = true;
        try {
            tasks.get(--taskNum).setStatus(true);
            if (Duke.toPrint) {
                System.out.println(Ui.LINE);
                System.out.println("\tNoted. Task " + (taskNum + 1) + " has been marked as \"complete\":");
                System.out.println("\t  " + tasks.get(taskNum).getTaskNameAndStatus());
                System.out.println(Ui.LINE);
            }
        } catch (IndexOutOfBoundsException | NullPointerException out_mark_b) {
            if (Duke.toPrint) {
                Ui.printInvalidNumber("mark");
            }
        }
    }

    public static void doCommandUnmark(int taskNum) {
        Duke.isFileEdited = true;
        try {
            tasks.get(--taskNum).setStatus(false);
            if (Duke.toPrint) {
                System.out.println(Ui.LINE);
                System.out.println("\tOh, ok. Task " + (taskNum + 1) + " has been marked as \"incomplete\":");
                System.out.println("\t  " + tasks.get(taskNum).getTaskNameAndStatus());
                System.out.println(Ui.LINE);
            }
        } catch (IndexOutOfBoundsException | NullPointerException out_unmark_b) {
            if (Duke.toPrint) {
                Ui.printInvalidNumber("unmark");
            }
        }
    }

    public static void doCommandList() {
        System.out.println(Ui.LINE);
        int count = 1;
        if (Duke.taskCount == 0) {
            System.out.println("\tYou have no pending tasks! ☺");
        } else {
            System.out.println("\tHere are your tasks:");
            for (int index = 0; index < Duke.taskCount; index++) {
                System.out.print("\t" + count + ".");
                System.out.println(tasks.get(index));
                count++;
            }
        }
        System.out.println(Ui.LINE);
    }

    public static void doCommandTodo(String taskName) {
        Duke.isFileEdited = true;
        tasks.add(new Todo(taskName));
        Duke.taskCount++;
        if (Duke.toPrint) {
            System.out.println(Ui.LINE);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + tasks.get(Duke.taskCount - 1));
            System.out.println("\t" + "Now you have " + Duke.taskCount + " pending tasks.");
            System.out.println(Ui.LINE);
        }
    }

    public static void doCommandDeadline(String taskName, String taskDeadline) {
        Duke.isFileEdited = true;
        tasks.add(new Deadline(taskName, taskDeadline));
        Duke.taskCount++;
        if (Duke.toPrint) {
            System.out.println(Ui.LINE);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + tasks.get(Duke.taskCount - 1));
            System.out.println("\t" + "Now you have " + Duke.taskCount + " pending tasks.");
            System.out.println(Ui.LINE);
        }
    }

    public static void doCommandEvent(String eventName, String eventDetailsPartOne, String eventDetailsPartTwo) {
        Duke.isFileEdited = true;
        tasks.add(new Event(eventName, eventDetailsPartOne, eventDetailsPartTwo));
        Duke.taskCount++;
        if (Duke.toPrint) {
            System.out.println(Ui.LINE);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + tasks.get(Duke.taskCount - 1));
            System.out.println("\t" + "Now you have " + Duke.taskCount + " pending tasks.");
            System.out.println(Ui.LINE);
        }
    }

    public static void doCommandDelete(int taskNum) {
        Duke.isFileEdited = true;
        System.out.println(Ui.LINE);
        System.out.println("\t  " + tasks.get(taskNum - 1));
        System.out.println("\t" + "Task removed!");
        System.out.println("\t" + "Now you have " + (Duke.taskCount - 1) + " pending tasks.");
        tasks.remove(taskNum - 1);
        System.out.println(Ui.LINE);
        Duke.taskCount--;
    }
}
