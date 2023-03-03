package buddy.commands.addTaskCommands;

import buddy.exception.InvalidCommandException;
import buddy.commands.Command;
import buddy.tasks.TaskList;
import buddy.tasks.Todo;

public class AddTodoCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        try{
            String[] todoSplit = input.split(" ", 2);
            if (todoSplit[1].equals("")){
                throw new InvalidCommandException();
            }
            Todo todoBeingAdded = new Todo(todoSplit[1]);
            taskList.addTask(todoBeingAdded);

        }catch (InvalidCommandException e){
            InvalidCommandException.printMessage();
        }

    }
}
