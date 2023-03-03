package duke.utils;
import static duke.utils.Ui.GENERAL_ERROR_MESSAGE;

/**
 * Parses user input and determines which set of commands to be called.
 */
public class Parser {
    public TaskList taskList;
    public Ui ui;
    public Parser (TaskList taskList, Ui ui) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses user input and executes commands to the task list accordingly.
     * Prints message to show user what was executed.
     *
     * @param userInput The line of input user enters.
     * @throws DukeException If the input is invalid.
     */
    public void doCommand(String userInput) throws DukeException {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];
        String content = splitInput[splitInput.length - 1];
        switch (command) {
        case "mark":
            taskList.toggleMark(content, true);
            break;
        case "unmark":
            taskList.toggleMark(content, false);
            break;
        case "list":
            ui.printListTasksMessage();
            taskList.printAllTasks(taskList.matchingTasksNum);
            break;
        case "todo":
            taskList.addTodo(content);
            taskList.printAddTaskMessage();
            break;
        case "event":
            taskList.addEvent(content);
            taskList.printAddTaskMessage();
            break;
        case "deadline":
            taskList.addDeadline(content);
            taskList.printAddTaskMessage();
            break;
        case "delete":
            taskList.printDeleteTaskMessage(Integer.parseInt(content) - 1);
            taskList.deleteTask(content);
            break;
        case "find":
            taskList.findMatchingTasks(content);
            taskList.printMatchingTasks();
            break;
        case "guide":
            ui.printGuide();
            break;
        default:
            ui.printErrorMessage(GENERAL_ERROR_MESSAGE);
        }
    }
}