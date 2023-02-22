package duke.commands.actionCommands;

import duke.commands.Command;
import duke.exceptions.InvalidArgsException;
import duke.save.Storage;
import duke.tasks.TaskList;

import static duke.constants.Constants.LINEBREAK;

public class HelpCommand extends Command {

    public static final String MESSAGE_HELP = "Here are the commands you can use:\n"
            + "1. todo <task name> - Adds a todo task to the list.\n"
            + "2. deadline <task name> /by <date> - Adds a deadline task to the list.\n"
            + "3. event <task name> /from <date> /to <date> - Adds an event task to the list.\n"
            + "4. list - Lists all tasks in the list.\n"
            + "5. mark <task number> - Marks a task as done.\n"
            + "6. unmark <task number> - Marks a task as not done.\n"
            + "7. help - Shows program usage instructions.\n"
            + "8. find <keyword> - Finds tasks with the keyword.\n"
            + "9. bye - Exits the program.\n"
            + LINEBREAK;

    @Override
    public void handleCommand(String line, TaskList tasks, Storage storage) {
        try {
            if (getArgumentNumber(line) != 1) {
                throw new InvalidArgsException();
            }
            System.out.println(MESSAGE_HELP);
        } catch (InvalidArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
