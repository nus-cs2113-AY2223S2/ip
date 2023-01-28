package duke.commands;

import duke.commands.taskCommands.DeadlineCommand;
import duke.commands.taskCommands.EventCommand;
import duke.commands.taskCommands.ToDoCommand;
import duke.task.TaskList;

public class CommandHandler {

    private String line;

    public CommandHandler(){

    }

    public void updateLine(String line){
        this.line = line;
    }

    private String parseCommand(){
        return line.contains(" ") ? line.split(" ")[0] : line;
    }

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
