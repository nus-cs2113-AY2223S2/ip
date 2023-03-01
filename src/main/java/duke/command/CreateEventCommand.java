package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A <code>CreateEventCommand</code> object takes care of the creation of new Event tasks.
 */
public class CreateEventCommand extends Command {
    private String description;
    private String start;
    private String end;


    public CreateEventCommand(String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /from ");
            description = inputLines[0];
            inputLines = inputLines[1].split(" /to ");
            start = inputLines[0];
            end = inputLines[1];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (date)");
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
        Task item = new Event(description, start, end);
        taskList.lists.add(item);
        Ui.printAddTask(item);
        Ui.printListSize(taskList.lists.size());
    }
}
