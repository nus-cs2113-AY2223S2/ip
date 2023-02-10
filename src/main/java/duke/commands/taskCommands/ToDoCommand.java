package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.InvalidTaskException;
import duke.save.FileOperation;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.IOException;

public class ToDoCommand extends Command {


    @Override
    public void handleCommand(String line, TaskList taskList){
        int markIndex;
        int indexCount = Task.getIndexCount();
        String description;
        try {
            markIndex = line.indexOf(' ');
            if (markIndex == -1) {
                throw new InvalidTaskException();
            }

            description = line.substring(markIndex + 1).trim();
            ToDo newTask = new ToDo(description);
            taskList.addTask(newTask);
            FileOperation.updateFile(taskList);
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
