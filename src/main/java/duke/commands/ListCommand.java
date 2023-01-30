package duke.commands;

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
            System.out.println("The list is empty. Please add a task first.");
            System.out.println(LINEBREAK);
        }

    }

}
