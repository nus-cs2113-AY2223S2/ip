package duke.command;

import duke.exceptions.CommandFormatException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represent an Add command and add the task in the list
 */
public class AddCommand extends Command {
    /**
     * Constructor
     *
     * @param userInput user input
     */
    public AddCommand(String userInput) {
        super(userInput);
    }

    /**
     * Add the task according to the command line
     * Check the starting word of the command and parser the command accordingly to obtain the details
     * <p>
     * If the command starts with to do, add to do task
     * If the command starts with deadline, add deadline task
     * If the command starts with event, add event task
     * If the command does not match either of the key word, it will throw CommandFormat Exception
     *
     * @param taskList TaskList
     * @param ui       Ui
     * @throws CommandFormatException if the command line is incorrect
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws CommandFormatException {

        if (userInput.trim().split(" ").length == 1) {
            throw new CommandFormatException();
        }
        if (userInput.startsWith("todo")) {
            String taskDescription = userInput.trim().split(" ", 2)[1];
            ui.printAddingLine();
            taskList.addTodo(taskDescription, false);
        } else if (userInput.startsWith("deadline")) {
            String thisInput = userInput.replace("deadline ", "");
            if (!thisInput.contains("/by")) {
                throw new CommandFormatException();
            } else if (thisInput.split(" /by ").length < 2) {
                throw new CommandFormatException();
            } else {
                String[] messages = thisInput.split(" /by ");
                String taskDescription = messages[0];
                String date = messages[1];
                ui.printAddingLine();
                taskList.addDeadline(taskDescription, date, false);
            }
        } else if (userInput.startsWith("event")) {
            String thisInput = userInput.replace("event ", "");
            if (!thisInput.contains("/from") || !thisInput.contains("/to")) {
                throw new CommandFormatException();
            } else if (userInput.indexOf("/from") >= userInput.indexOf("/to")) {
                throw new CommandFormatException();
            } else {
                String[] messages = thisInput.split(" /from ");
                String taskDescription = messages[0];
                String from = messages[1].split(" /to ")[0];
                String to = messages[1].split(" /to ")[1];
                ui.printAddingLine();
                taskList.addEvent(taskDescription, from, to, false);
            }
        } else {
            throw new CommandFormatException();
        }
    }
}
