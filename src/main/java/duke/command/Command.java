package duke.command;

import duke.Duke;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * command class contains all the commands used to add tasks
 * Ex, commands to add respective tasks, todo, event, deadline
 */
public class Command {

    public static void doCommandTodo(String taskName) {
        Duke.isFileEdited = true;
        TaskList.tasks.add(new Todo(taskName));
        Duke.taskCount++;
        if (Duke.toPrint) {
            System.out.println(Ui.LINE);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + TaskList.tasks.get(Duke.taskCount - 1));
            System.out.println("\t" + "Now you have " + Duke.taskCount + " pending tasks.");
            System.out.println(Ui.LINE);
        }
    }

    public static void doCommandDeadline(String taskName, String taskDeadline) {
        Duke.isFileEdited = true;
        TaskList.tasks.add(new Deadline(taskName, taskDeadline));
        Duke.taskCount++;
        if (Duke.toPrint) {
            System.out.println(Ui.LINE);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + TaskList.tasks.get(Duke.taskCount - 1));
            System.out.println("\t" + "Now you have " + Duke.taskCount + " pending tasks.");
            System.out.println(Ui.LINE);
        }
    }

    public static void doCommandEvent(String eventName, String eventDetailsPartOne, String eventDetailsPartTwo) {
        Duke.isFileEdited = true;
        TaskList.tasks.add(new Event(eventName, eventDetailsPartOne, eventDetailsPartTwo));
        Duke.taskCount++;
        if (Duke.toPrint) {
            System.out.println(Ui.LINE);
            System.out.println("\t" + "Task added!");
            System.out.println("\t  " + TaskList.tasks.get(Duke.taskCount - 1));
            System.out.println("\t" + "Now you have " + Duke.taskCount + " pending tasks.");
            System.out.println(Ui.LINE);
        }
    }
}
