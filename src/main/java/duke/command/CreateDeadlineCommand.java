package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * A <code>CreateDeadlineCommand</code> object takes care of the creation of new Deadline tasks.
 */
public class CreateDeadlineCommand extends Command {
    private String description;
    private String deadline;
    private boolean isInvalid = false;

    /**
     * Creates parameters for deadline task. Parses deadline as required timing format.
     *
     * @param line Input line from user containing task details.
     * @throws IndexOutOfBoundsException if user does not provide either a description or deadline.
     */
    public CreateDeadlineCommand(String line) throws IndexOutOfBoundsException {
        try {
            String[] inputLines = line.split(" ", 2);
            inputLines = inputLines[1].split(" /by ");
            description = inputLines[0];
            deadline = Parser.formatDateTime(inputLines[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Your deadline must be of the following format: deadline (deadline name) /by (dd-mm-yyyy hh:mm)");
            isInvalid = true;
        }
    }

    /**
     * Register a deadline task. Prints to the terminal to signal the
     * completion of adding the task.
     *
     * @param taskList List to store all tasks.
     */
    @Override
    public void run(TaskList taskList) {
        if (!isInvalid && !deadline.isEmpty()) {
            Task item = new Deadline(description, deadline);
            taskList.lists.add(item);
            Ui.printAddTask(item);
            Ui.printListSize(taskList.lists.size());
        }
    }
}
