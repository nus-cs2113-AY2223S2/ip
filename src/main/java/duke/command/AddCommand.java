package duke.command;

import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.List;

/**
 * Command when adding a new task to tasklist
 */
public class AddCommand extends Command {

    protected String taskField;

    public AddCommand(String commandType, String taskField) {
        super(commandType);
        this.taskField = taskField;
    }

    /**
     * Adds a new task into the tasklist according to task type
     *
     * @param tasks tasklist which contains all the tasks
     */
    @Override
    public void execute(TaskList tasks) {
        switch (commandType) {
            case "todo":
                Todo todo = new Todo(taskField, "T");
                tasks.addToList(todo);
                UI.printAddedTask(todo, tasks);
                break;
            case "deadline":
                List<String> parsedDeadline = Parser.parseDeadline(taskField);
                Deadline deadline = new Deadline(parsedDeadline.get(0), "D", parsedDeadline.get(1));
                tasks.addToList(deadline);
                UI.printAddedTask(deadline, tasks);
                break;
            case "event":
                List<String> parsedEvent = Parser.parseEvent(taskField);
                Event event = new Event(parsedEvent.get(0), "E", parsedEvent.get(1), parsedEvent.get(2));
                tasks.addToList(event);
                UI.printAddedTask(event, tasks);
                break;
        }
        Storage.updateFile(tasks);
    }
}
