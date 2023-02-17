package duke.command;

import duke.error.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.ui.Ui;

public class AddCommand extends Command {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String ADD_MESSAGE = " The following task has been added:"
            + Ui.NEW_LINE + "   %s"
            + Ui.NEW_LINE + " There is now %d task[s] in total." + Ui.NEW_LINE;
    public static final String TODO_MESSAGE_USAGE = " " + COMMAND_TODO + ": adds a todo task to the task list. "
            + Ui.NEW_LINE + "  Parameters: task name"
            + Ui.NEW_LINE + "  Example: " + COMMAND_TODO + " buy bread";
    public static final String DEADLINE_MESSAGE_USAGE = " " + COMMAND_DEADLINE + ": adds a deadline task to the task list. "
            + Ui.NEW_LINE + "  Parameters: task name, deadline"
            + Ui.NEW_LINE + "  Example: " + COMMAND_DEADLINE + " eat bread /by today";
    public static final String EVENT_MESSAGE_USAGE = " " + COMMAND_EVENT + ": adds an event task to the task list. "
            + Ui.NEW_LINE + "  Parameters: task name, start date, end date"
            + Ui.NEW_LINE + "  Example: " + COMMAND_EVENT + " make bread /from today 3pm /to 5pm";

    public static void manageTodoInput(String input, TaskList taskList) throws DukeException {
        String taskName = Parser.parseTodoCommand(input);
        taskList.addTask(new Todo(taskName));
        Ui.showAddTask(taskList);
        Task.incrementTotalTasks();
    }

    public static void manageDeadlineInput(String input, TaskList taskList) throws DukeException {
        String[] taskDetails = Parser.parseDeadlineCommand(input);
        taskList.addTask(new Deadline(taskDetails[0], taskDetails[1]));
        Ui.showAddTask(taskList);
        Task.incrementTotalTasks();
    }

    public static void manageEventInput(String input, TaskList taskList) throws DukeException {
        String[] taskDetails = Parser.parseEventCommand(input);
        taskList.addTask(new Event(taskDetails[0], taskDetails[1], taskDetails[2]));
        Ui.showAddTask(taskList);
        Task.incrementTotalTasks();
    }
}
