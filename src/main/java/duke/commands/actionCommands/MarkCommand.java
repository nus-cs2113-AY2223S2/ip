package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.EmptyListException;
import duke.exceptions.InvalidArgsException;
import duke.save.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

import static duke.constants.Constants.LINEBREAK;

public class MarkCommand extends Command {

    /**
     * {@inheritDoc}
     * @throws EmptyListException If the list is empty.
     * @throws InvalidArgsException If the arguments are invalid.
     */
    @Override
    public void handleCommand(String line, TaskList tasks, Storage storage) {
        int indexCount = Task.getIndexCount();
        int indexSelect;
        try {
            if (getArgumentNumber(line) != 2) {
                throw new InvalidArgsException();
            }

            String done = line.split(" ")[1];
            if (indexCount == 0) {
                throw new EmptyListException();
            }
            indexSelect = Integer.parseInt(done) - 1;

            tasks.get(indexSelect).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(indexSelect));
            System.out.println(LINEBREAK);
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
