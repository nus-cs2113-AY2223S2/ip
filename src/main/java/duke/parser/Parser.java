package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.CommandFormatException;

public class Parser {
	/**
	 * This part of code is inspired by
	 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
	 *
	 * Parser the user input and create a Command object
	 *
	 * @param command full user input string
	 * @return Command object based on the user input
	 * @throws CommandFormatException if the command format is wrong
	 */

	public static Command parse(String command) throws CommandFormatException {
		String fullCommand = command.trim();
		if (fullCommand.split(" ").length == 0) {
			throw new CommandFormatException();
		} else if (fullCommand.startsWith("todo") ||
				fullCommand.startsWith("deadline") || fullCommand.startsWith("event")) {
			return new AddCommand(fullCommand);
		} else if (fullCommand.startsWith("delete")) {
			return new DeleteCommand(fullCommand);
		} else if (fullCommand.matches("list")) {
			return new ListCommand(fullCommand);
		} else if (fullCommand.startsWith("mark")) {
			return new MarkCommand(fullCommand);
		} else if (fullCommand.startsWith("unmark")) {
			return new UnmarkCommand(fullCommand);
		} else if (fullCommand.matches("help")) {
			return new HelpCommand(fullCommand);
		} else if (fullCommand.matches("exit")) {
			return new ExitCommand(fullCommand);
		} else if (fullCommand.startsWith("find")) {
			return new FindCommand(fullCommand);
		} else {
			throw new CommandFormatException();
		}
	}
}