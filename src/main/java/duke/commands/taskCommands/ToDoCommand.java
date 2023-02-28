package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.InvalidTaskException;
import duke.save.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.IOException;

import static duke.constants.Constants.LINEBREAK;

public class ToDoCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCommand(String line, TaskList taskList, Storage storage) {
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
            storage.updateFile(taskList);
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Oops, something went wrong!\n" + LINEBREAK);
        }
    }
}
