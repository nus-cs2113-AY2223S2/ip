package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.AddCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.CommandFormatException;

public class Parser {

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
		} else {
			throw new CommandFormatException();
		}
	}
}

//	} else if (userMessage.equalsIgnoreCase("exit")) {
//		try {
//			writeToFile(taskList);
//		} catch (IOException e){
//			System.out.println("Unable to write to file");
//		}
//		shouldExit = true;
//		dukeGreeting.printExitLine();
//	} else {
//		dukeGreeting.printErrorMessage();
//	}
