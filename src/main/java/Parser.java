import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;

public class Parser {
    protected TaskList tasklist = new TaskList();
    protected UI ui = new UI();

    /**
     * Interprets the command that was given by the user input and execute the commands accordingly to its specified usage.
     *
     * @return false if "bye" executed to close the loop and exit the application
     * @param userInput Input string that is provided by user in the CLI.
     * @throws CommandNotFoundException if command is not found.
     */
    public boolean executeCommand(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            ui.printByeMessage();
            return false;
        } else if (userInput.equals("list")) {
            tasklist.showList();
        } else if (userInput.startsWith("todo")) {
            tasklist.createTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            tasklist.createDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            tasklist.createEvent(userInput);
        } else if (userInput.startsWith("mark")) {
            tasklist.markTask(userInput);
        } else if (userInput.startsWith("unmark")) {
            tasklist.unmarkTask(userInput);
        } else if (userInput.startsWith("delete")) {
            tasklist.deleteTask(userInput);
        } else if (userInput.startsWith("find")) {
            tasklist.findTask(userInput);
        } else {
            throw new CommandNotFoundException();
        }
        return true;
    }
}
