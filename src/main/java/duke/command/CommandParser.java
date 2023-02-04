package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Messages;
import duke.ui.Ui;

public abstract class CommandParser {
    private static void addTask(Task task, TaskList taskList) {
        String taskString = taskList.addTask(task);
        String taskCountString = String.format("Now you have %d task%s in the list.",
                taskList.size(),
                taskList.size() > 1 ? "s" : "");
        Ui.print(Messages.ADD.MESSAGE, taskString, taskCountString);
    }

    private static void setDone(int index, boolean isDone, TaskList taskList) {
        String taskString = taskList.setDone(index, isDone);
        Ui.print(isDone ? Messages.MARK.MESSAGE : Messages.UNMARK.MESSAGE, taskString);
    }

    public static boolean parseCommand(String str, TaskList taskList) {
        String[] command = str.trim().split(" ", 2);
        switch (command[0]) {
        case "todo":
            addTask(new ToDo(command[1]), taskList);
            return true;
        case "event": {
            String[] arguments = command[1].split(Event.DELIMITER);
            addTask(new Event(arguments[0], arguments[1], arguments[2]), taskList);
            return true;
        }
        case "deadline": {
            String[] arguments = command[1].split(Deadline.DELIMITER);
            addTask(new Deadline(arguments[0], arguments[1]), taskList);
            return true;
        }
        case "list":
            Ui.printTaskList(taskList);
            return true;
        case "mark":
        case "unmark": {
            int index = Integer.parseInt(command[1]) - 1;
            setDone(index, command[0].equals("mark"), taskList);
            return true;
        }
        case "bye":
            return false;
        default:
            return true;
        }
    }
}
