package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Parser;

/**
 * A <code>CreateEventCommand</code> object takes care of the creation of new Event tasks.
 */
public class CreateEventCommand extends Command {
    private String description;
    private String start;
    private String end;
    private boolean isInvalid = false;


    /**
     * Creates parameters for event task. Parses start and end date as required timing format.
     *
     * @param line Input line from user containing task details.
     * @throws IndexOutOfBoundsException if user does not provide either a description, start or end date.
     */
    public CreateEventCommand(String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /from ");
            description = inputLines[0];
            inputLines = inputLines[1].split(" /to ");
            start = Parser.formatDateTime(inputLines[0]);
            end = Parser.formatDateTime(inputLines[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your event must be of the following format: event (event name) /from (dd-mm-yyyy hh:mm) /to (dd-mm-yyyy hh:mm)");
            isInvalid = true;
        }
    }

    /**
     * Register an event task. Prints to the terminal to signal the
     * completion of adding the task.
     *
     * @param taskList List to store all tasks.
     */
    @Override
    public void run(TaskList taskList) {
        if (!isInvalid && !(start.isEmpty() || end.isEmpty())) {
            Task item = new Event(description, start, end);
            taskList.lists.add(item);
            Ui.printAddTask(item);
            Ui.printListSize(taskList.lists.size());
        }
    }
}
