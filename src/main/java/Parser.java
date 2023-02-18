import exception.DukeException;

/**
 * Represents a parser that is able to identify user input and pass on commands to the program to
 * execute different methods to modify/search the task list.
 */
public class Parser {
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
    protected Ui ui;

    /**
     * Constructs a Parser object that takes in a Duke type object to get reference to the task list and file data.
     * Also instantiates a Ui object to print certain responses
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

        if (inputs[0].equals("bye")) {
            return true;
        }
        if (inputs[0].isEmpty()) { //settle for the case of empty inputs
            return false;
        }
        if (inputs[0].equals("list")) { //want to print out the task list
            ui.printTaskList(taskList);
            return false;
        }
        if (inputs[0].equals("mark")) {
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.markTask(taskNumber);
            return false;
        }
        if (inputs[0].equals("unmark")) {
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.unmarkTask(taskNumber);
            return false;
        }
        if (inputs[0].equals("todo")) {
            taskList.addTodoTask(inputs[1]);
            return false;
        }
        if (inputs[0].equals("deadline")) {
            taskList.addDeadlineTask(inputs[1]);
            return false;
        }
        if (inputs[0].equals("event")) {
            taskList.addEventTask(inputs[1]);
            return false;
        }
        if (inputs[0].equals("delete")) {
            int taskNumber = Integer.parseInt(inputs[1]);
            taskList.deleteTask(taskNumber);
            return false;
        }
        if (inputs[0].equals("find")) {
            taskList.findTask(inputs[1]);
            return false;
        }
        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
