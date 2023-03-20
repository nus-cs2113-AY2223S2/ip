package duke;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.Exit;
import duke.commands.MarkCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeEmptyArgumentException;
import duke.exceptions.DukeIllegalCommandException;

public class Parser {

    /**
     * Returns the corresponding command
     *
     * @param command the full command inputted by User.
     * @return Command object that is equal to the command.
     */
    public static Command parse(String command) {
        try {
            if (command.startsWith("bye")) {
                return new Exit();
            }
            if (command.startsWith("list")) {
                return new ListCommand();
            }
            if (command.startsWith("mark")) {
                return new MarkCommand(command);
            }
            if (command.startsWith("delete")) {
                return new DeleteCommand(command);
            }
            if (command.startsWith("find")) {
                return new FindCommand(command);
            } else {
                return new AddCommand(command);
            }
        } catch (DukeIllegalCommandException | DukeEmptyArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}