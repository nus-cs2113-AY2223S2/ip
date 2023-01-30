package duke.commands.taskCommands;

import duke.commands.Command;
import duke.commands.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import static duke.constants.Constants.LINEBREAK;

public class ToDoCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        int markIndex;
        int indexCount = Task.getIndexCount();
        String description;
        try {
            markIndex = line.indexOf(' ');
            if (markIndex == -1) {
                DukeException.printError();
                System.out.println(LINEBREAK);
                return;
            }

            description = line.substring(markIndex + 1).trim();
            ToDo newTask = new ToDo(description);
            taskList.addTask(indexCount, newTask);
        } catch (Exception e) {
            DukeException.printError();
            System.out.println(LINEBREAK);
        }
    }
}
