package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.InvalidArgsException;
import duke.save.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;
import java.util.stream.Collectors;

import static duke.constants.Constants.LINEBREAK;

public class FindCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCommand(String line, TaskList taskList, Storage storage) {
        try {
            if (getArgumentNumber(line) != 2) {
                throw new InvalidArgsException();
            }
            String word = line.split(" ")[1].trim().toLowerCase();
            List<Task> filteredTasks = taskList.stream().filter(t -> t.getTaskName().toLowerCase().contains(word))
                    .collect(Collectors.toList());

            if (filteredTasks.size() == 0) {
                System.out.println("There are no tasks matching your query\n" + LINEBREAK);
            } else {
                int i = 1;
                for (Task t : filteredTasks) {
                    System.out.print(i++ + ". ");
                    System.out.println(t);
                }
                System.out.println(LINEBREAK);
            }
        } catch (InvalidArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
