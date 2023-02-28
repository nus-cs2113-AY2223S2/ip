/**
 * Represents a command to add a task to the current list of tracked tasks.
 */
public class AddCommand extends Command {
    public AddCommand(String firstWord, String restOfCommand) {
        super(firstWord, restOfCommand);
    }

    /**
     * Determine which type of task to add before adding the right task to
     * the array of tracked tasks.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     * @param storage Storage object for dealing with saving tasks to file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (firstWord) {
        case "todo":
            try {
                createTodoTask(taskList, restOfCommand);
            } catch (DukeException e) {
                ui.showInvalidTodoError();
                return;
            }
            break;
        case "deadline":
            try {
                createDeadlineTask(taskList, restOfCommand);
            } catch (DukeException e) {
                ui.showInvalidDeadlineError();
                return;
            }
            break;
        case "event":
            try {
                createEventTask(taskList, restOfCommand);
            } catch (DukeException e) {
                ui.showInvalidEventError();
                return;
            }
            break;
        }
        ui.showTaskIsAdded();
        ui.showCurrTask(taskList, taskList.numOfTasks);
        taskList.incrementNumOfTasks();
        ui.showCurrNumOfTask(taskList);
    }

    /**
     * Add a new Todo task to the array of tracked tasks.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param input A string containing data regarding the Todo task to be added
     * @throws DukeException If input has missing information
     */
    public static void createTodoTask(TaskList taskList, String input) throws DukeException {
        if ((input.trim()).length() == 0) { // Task has no description
            throw new DukeException();
        }
        taskList.addTask(new Todo(input));
    }

    /**
     * Add a new Deadline task to the array of tracked tasks.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param input A string containing data regarding the Deadline task to be added
     * @throws DukeException If input has missing information
     */
    public static void createDeadlineTask(TaskList taskList, String input) throws DukeException {
        // Extract description and by parts from input
        int byIndex = input.indexOf("/by");
        if (byIndex == -1 || byIndex == 0) { // /by does not exist or task does not have description
            throw new DukeException();
        }
        String description = (input.substring(0, byIndex)).trim();
        String restOfInput = input.substring(byIndex);

        int startingIndex = (restOfInput).indexOf(" ");
        if (startingIndex == -1) { // /by has no description
            throw new DukeException();
        }
        String by = (restOfInput.substring(startingIndex)).trim();
        if (by.length() == 0) { // /by's description only has spaces
            throw new DukeException();
        }
        taskList.addTask(new Deadline(description, by));
    }

    /**
     * Add a new Event task to the array of tracked tasks.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param input A string containing data regarding the Event task to be added
     * @throws DukeException If input has missing information
     */
    public static void createEventTask(TaskList taskList, String input) throws DukeException {
        // Extract description, from and to parts from input
        int descriptionIndex = input.indexOf("/from");
        if (descriptionIndex == -1 || descriptionIndex == 0) { // /from does not exist or task has no description
            throw new DukeException();
        }
        String description = (input.substring(0, descriptionIndex)).trim();
        String restOfInput = input.substring(descriptionIndex);

        int fromIndex = restOfInput.indexOf(" ");
        int toIndex = restOfInput.indexOf("/to");
        if (toIndex == -1) { // /to does not exist
            throw new DukeException();
        }
        String from;
        try {
            from = (restOfInput.substring(fromIndex, toIndex)).trim();
        } catch (StringIndexOutOfBoundsException e) { // No space between /from and /to in command
            throw new DukeException();
        }
        if (from.length() == 0) { // /from's description only has spaces
            throw new DukeException();
        }

        String toPartOfInput = restOfInput.substring(toIndex);
        int toDescriptionIndex = toPartOfInput.indexOf(" ");
        if (toDescriptionIndex == -1) { // /to has no description
            throw new DukeException();
        }
        String to = (toPartOfInput.substring(toDescriptionIndex)).trim();
        if (to.length() == 0) { // /to's description only has spaces
            throw new DukeException();
        }
        taskList.addTask(new Event(description, from, to));
    }
}
