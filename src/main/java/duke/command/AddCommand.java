package duke.command;

import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.List;

public class AddCommand extends Command {

    protected String taskField;

    public AddCommand(String commandType, String taskField) {
        super(commandType);
        this.taskField = taskField;
    }

    @Override
    public void execute(TaskList tasks) {
        switch (commandType) {
            case "todo":
                UI.printTodo(taskField);
                Todo todo = new Todo(taskField, "T");
                tasks.addToList(todo);
                UI.printTaskListLength(tasks.sizeOfList());
                break;
            case "deadline":
                List<String> parsedDeadline = Parser.parseDeadline(taskField);
                UI.printDeadline(parsedDeadline.get(0), parsedDeadline.get(1));
                Deadline deadline = new Deadline(parsedDeadline.get(0), "D", parsedDeadline.get(1));
                tasks.addToList(deadline);
                UI.printTaskListLength(tasks.sizeOfList());
                break;
            case "event":
                List<String> parsedEvent = Parser.parseEvent(taskField);
                UI.printEvent(parsedEvent.get(0), parsedEvent.get(1), parsedEvent.get(2));
                Event event = new Event(parsedEvent.get(0), "E", parsedEvent.get(1), parsedEvent.get(2));
                tasks.addToList(event);
                UI.printTaskListLength(tasks.sizeOfList());
                break;
        }
        Storage.updateFile(tasks);
    }
}
