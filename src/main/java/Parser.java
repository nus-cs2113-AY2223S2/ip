import duke.DukeException;

import java.io.IOException;

/**
 * Represents a parser that is able to identify user input and pass on commands to the program to
 * execute different methods to modify/search the task list.
 */
public class Parser {
    protected TaskList taskList;
    protected FileDataHandler fileDataHandler;
    protected Ui ui;
    public Parser (Duke duke) {
        this.taskList = duke.taskList;
        this.fileDataHandler = duke.fileDataHandler;
        ui = new Ui();
    }

    public boolean parse (String[] inputs) throws IOException, DukeException {

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
