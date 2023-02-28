package duke;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.List;

public class AddCommand extends Command {

    protected String taskFields;

    public AddCommand(String commandType, String taskFields) {
        super(commandType);
        this.taskFields = taskFields;
    }

    @Override
    public void execute(TaskList tasks) {
        switch (commandType) {
            case "todo":
                UI.printTodo(taskFields);
                Todo todo = new Todo(taskFields, "T");
                tasks.addToList(todo);
                UI.printTaskList(tasks.sizeOfList());
                break;
            case "deadline":
                List<String> parsedDeadline = Parser.parseDeadline(taskFields);
                UI.printDeadline(parsedDeadline.get(0), parsedDeadline.get(1));
                Deadline deadline = new Deadline(parsedDeadline.get(0), "D", parsedDeadline.get(1));
                tasks.addToList(deadline);
                UI.printTaskList(tasks.sizeOfList());
                break;
            case "event":
                List<String> parsedEvent = Parser.parseEvent(taskFields);
                UI.printEvent(parsedEvent.get(0), parsedEvent.get(1), parsedEvent.get(2));
                Event event = new Event(parsedEvent.get(0), "E", parsedEvent.get(1), parsedEvent.get(2));
                tasks.addToList(event);
                UI.printTaskList(tasks.sizeOfList());
                break;
        }
        Storage.updateFile(tasks);

    }
}
