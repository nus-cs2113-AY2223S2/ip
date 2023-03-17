package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.EmptyListException;
import duke.exceptions.InvalidArgsException;
import duke.save.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

import static duke.constants.Constants.LINEBREAK;

public class DeleteCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCommand(String line, TaskList tasks, Storage storage) {
        int indexSelect;
        int indexCount = Task.getIndexCount();
        try {
            if (getArgumentNumber(line) != 2) {
                throw new InvalidArgsException();
            }
            String indexSelectString = line.split(" ")[1];

            if (indexCount == 0) {
                throw new EmptyListException();
            }

            indexSelect = Integer.parseInt(indexSelectString) - 1;
            tasks.deleteTask(indexSelect);
            storage.updateFile(tasks);

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Invalid task number. Please try again.");
            System.out.println(LINEBREAK);

        } catch (EmptyListException | InvalidArgsException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Invalid command. Please try again.");
            System.out.println(LINEBREAK);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
