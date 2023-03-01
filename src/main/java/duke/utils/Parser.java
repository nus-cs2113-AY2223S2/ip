package duke.utils;

import static duke.utils.Ui.FAREWELL_MESSAGE;
import static duke.utils.Ui.GENERAL_ERROR_MESSAGE;
import duke.utils.TaskList;
public class Parser {
    public TaskList taskList;
    public Ui ui;
    public Parser (TaskList taskList, Ui ui) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public void doCommand(String userInput) throws DukeException {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];
        String content = splitInput[splitInput.length - 1];
/*        String[] phrases = new String[10];*/
        System.out.println(content);
        System.out.println(command);
        switch (command) {
        case "mark":
            taskList.toggleMark(content, true);
            break;
        case "unmark":
            taskList.toggleMark(content, false);
            break;
        case "list":
            ui.printListTasksMessage();
            taskList.printList(TaskList.currentTaskNum);
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
            taskList.deleteTask(content);
            taskList.printDeleteTaskMessage(Integer.parseInt(content) - 1);
            break;
        case "find":
        case "guide":
            break;
        default:
            ui.printErrorMessage(GENERAL_ERROR_MESSAGE);
        }
    }
}
