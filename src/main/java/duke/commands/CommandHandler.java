package duke.commands;

import duke.commands.taskCommands.DeadlineCommand;
import duke.commands.taskCommands.EventCommand;
import duke.commands.taskCommands.ToDoCommand;
import duke.task.TaskList;

public class CommandHandler {

    private String line;

    /**
     * Constructor for CommandHandler
     *
     * @param line User input represented as a String
     */
    public void updateLine(String line){
        this.line = line;
    }

    /**
     * Parses the command from user input
     *
     * @return String containing the command
     */
    private String parseCommand(){
        return line.contains(" ") ? line.split(" ")[0] : line;
    }

    /**
     * Handles the command based on user input
     *
     * @param taskList List containing the tasks input by user
     */
    public void handleCommand(TaskList taskList){
        String lineBreak = UI.getLineBreak();
        String command = parseCommand();
        switch (command) {

        case "todo":
            ToDoCommand newTodo = new ToDoCommand();
            newTodo.handleCommand(line, taskList);
            break;

        case "deadline":
            DeadlineCommand newDeadline = new DeadlineCommand();
            newDeadline.handleCommand(line, taskList);
            break;

        case "event":
            EventCommand newEvent = new EventCommand();
            newEvent.handleCommand(line, taskList);
            break;

        case "":
            Command newCommand = new Command();
            newCommand.handleCommand(line, taskList);
            break;

        case "list":
            ListCommand newList = new ListCommand();
            newList.handleCommand(line, taskList);
            break;

        case "mark":
            MarkCommand newMark = new MarkCommand();
            newMark.handleCommand(line, taskList);
            break;

        case "unmark":
            UnmarkCommand newUnmark = new UnmarkCommand();
            newUnmark.handleCommand(line, taskList);
            break;

        case "help":


        default:
            DukeException.printError();
            System.out.println(lineBreak);
        }
    }
}
