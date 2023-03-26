package Duke;

import Duke.command.DukeCommand;
import Duke.command.DukeDeadlineCommand;
import Duke.command.DukeDeleteCommand;
import Duke.command.DukeEventCommand;
import Duke.command.DukeExitCommand;
import Duke.command.DukeFindCommand;
import Duke.command.DukeListCommand;
import Duke.command.DukeMarkCommand;
import Duke.command.DukeToDoCommand;
import Duke.command.DukeUnmarkCommand;

public class DukeParser {

    public static DukeCommand parse(String fullCommand) throws DukeException {
        String[] command = fullCommand.split(" ", 2);
        switch (command[0]) {
        case "bye":
            return new DukeExitCommand();
        case "list":
            return new DukeListCommand();
        case "mark":
            if (command.length > 1) {
                return new DukeMarkCommand(command[1]);
            } else {
                throw new DukeException("Please enter a valid value.");
            }
        case "unmark":
            if (command.length > 1) {
                return new DukeUnmarkCommand(command[1]);
            } else {
                throw new DukeException("Please enter a valid value.");
            }
        case "delete":
            if (command.length > 1) {
                return new DukeDeleteCommand(command[1]);
            } else {
                throw new DukeException("Please enter a valid value.");
            }
        case "todo":
            if (command.length > 1) {
                return new DukeToDoCommand(command[1]);
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        case "deadline":
            if (command.length > 1) {
                    return new DukeDeadlineCommand(command[1]);
                } else {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
        case "event":
            if (command.length > 1) {
                return new DukeEventCommand(command[1]);
            } else {
                throw new DukeException("The description of a event cannot be empty.");
            }
        case "find":
            if (command.length > 1) {
                return new DukeFindCommand(command[1]);
            } else {
                throw new DukeException("Please enter a keyword to search for.");
            }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

}
