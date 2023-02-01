package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.EmptyListException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import static duke.constants.Constants.LINEBREAK;

public class ListCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList) {
        try {
            int indexCount = Task.getIndexCount();
            if (indexCount == 0) {
                throw new EmptyListException();
            }

            for (int i = 0; i < indexCount; i++) {
                System.out.println(i + 1 + ". " + taskList.getTask(i));
            }
            System.out.println(LINEBREAK);
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }

    }

}
