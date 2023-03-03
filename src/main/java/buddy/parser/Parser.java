package buddy.parser;

import java.io.IOException;
import buddy.commands.Command;
import buddy.commands.actionCommands.DeleteTaskCommand;
import buddy.commands.actionCommands.HelpCommand;
import buddy.commands.actionCommands.ListCommand;
import buddy.commands.actionCommands.MarkTaskCommand;
import buddy.commands.actionCommands.UnmarkTaskCommand;
import buddy.commands.actionCommands.FindTaskCommand;
import buddy.commands.addTaskCommands.AddTodoCommand;
import buddy.commands.addTaskCommands.AddDeadlineCommand;
import buddy.commands.addTaskCommands.AddEventCommand;
import buddy.exception.InvalidCommandException;
import buddy.storage.Storage;
import buddy.tasks.TaskList;

public class Parser {

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public void executeInput(TaskList taskList, String input, Storage storage) {
        String[] inputSplit = input.split(" ", 2);
        String commandName = inputSplit[0].trim();
        try {
            switch (commandName) {
                case "todo":
                    Command addTodo = new AddTodoCommand();
                    addTodo.executeCommand(taskList, input);
                    break;

                case "deadline":
                    Command addDeadline = new AddDeadlineCommand();
                    addDeadline.executeCommand(taskList, input);
                    break;

                case "event":
                    Command addEvent = new AddEventCommand();
                    addEvent.executeCommand(taskList, input);
                    break;

                case "list":
                    Command list = new ListCommand();
                    list.executeCommand(taskList, input);
                    break;

                case "mark":
                    Command mark = new MarkTaskCommand();
                    mark.executeCommand(taskList, input);
                    break;

                case "unmark":
                    Command unmark = new UnmarkTaskCommand();
                    unmark.executeCommand(taskList, input);
                    break;

                case "delete":
                    Command delete = new DeleteTaskCommand();
                    delete.executeCommand(taskList, input);

                    break;

                case "find":
                    Command find = new FindTaskCommand();
                    find.executeCommand(taskList, input);
                    break;

                case "help":
                    Command help = new HelpCommand();
                    help.executeCommand(taskList, input);
                    break;

                default:
                    throw new InvalidCommandException();
            }

        } catch (InvalidCommandException e) {
            InvalidCommandException.printMessage();
        }

        try{
            storage.updateFile(taskList);
        }catch (IOException e){
            System.out.println("IO Error");
        }





    }

}
