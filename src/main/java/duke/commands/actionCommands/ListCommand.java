package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.EmptyListException;
import duke.exceptions.InvalidArgsException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import static duke.constants.Constants.LINEBREAK;

public class ListCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList tasks) {
        try {

            if (getArgumentNumber(line) != 1) {
                throw new InvalidArgsException();
            }
            int indexCount = Task.getIndexCount();
            if (indexCount == 0) {
                throw new EmptyListException();
            }

            for (int i = 0; i < indexCount; i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println(LINEBREAK);
        } catch (EmptyListException | InvalidArgsException e) {
            System.out.println(e.getMessage());
        }

    }

}
