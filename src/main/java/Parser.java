import exception.DukeException;

/**
 * Represents a parser that is able to identify user input and pass on commands to the program to
 * execute different methods to modify/search the task list.
 */
public class Parser {
    public static final String INVALID_COMMAND_ERROR = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
    protected Ui ui;

    /**
     * Constructs a Parser object that takes in a Duke type object to get reference to the task list and file data.
     * Also instantiates an Ui object to print certain responses
     *
     * @param duke the instance of the Duke chatbot that is currently in the running session.
     */
    public Parser (Duke duke) {
        this.taskList = duke.taskList;
        this.fileDataHandler = duke.fileDataHandler;
        ui = new Ui();
    }

    /**
     * Method that parses the input given by the user.
     * If the input is a valid command, proceeds to execute the command given.
     *
     * @param inputs the String array containing the first word and the remaining of the string keyed in by the user.
     * @return a boolean is set to true if the command is "bye", to signal the exit of the chatbot, else set to false.
     * @throws DukeException when the command is invalid.
     */
    public boolean parse (String[] inputs) throws DukeException {

        switch (inputs[0]) {
        case "bye":
            return true;
        case "":
            break;
        case "list":
            ui.printTaskList(taskList);
            break;
        case "mark":
            taskList.markTask(inputs[1]);
            break;
        case "unmark":
            taskList.unmarkTask(inputs[1]);
            break;
        case "todo":
            taskList.addTodoTask(inputs[1]);
            break;
        case "deadline":
            taskList.addDeadlineTask(inputs[1]);
            break;
        case "event":
            taskList.addEventTask(inputs[1]);
            break;
        case "delete":
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.deleteTask(taskNumber);
            break;
        case "find":
            taskList.findTask(inputs[1]);
            break;
        default:
            throw new DukeException(INVALID_COMMAND_ERROR);
        }
        return false;
    }
}
