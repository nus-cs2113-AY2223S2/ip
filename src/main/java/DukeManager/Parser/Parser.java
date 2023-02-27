package DukeManager.Parser;


import DukeManager.Commands.*;
import DukeManager.data.DukeErrors.BlankListException;

import static DukeManager.common.Messages.CMD_FORMAT_ERROR;
import static DukeManager.common.Messages.EMPTY_LIST_ERROR_MSG;

public class Parser {
	/**
	 * Parses user input into command for execution.
	 *
	 * @param userInput full user input string
	 * @return the command based on the user input
	 */
	public Cmd parseCommand(String userInput) {
		String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
		if (words.length == 0) {
			return new IncorrectCmd(String.format(CMD_FORMAT_ERROR, HelpCommand.MESSAGE_USAGE));
		}

		final String cmdWord = words[0];
		final String args = words[1];

		switch (cmdWord) {
		case "help":
			return new HelpCmd();
		case "list":
			return new ListCmd();
			break;
		case "mark":
			return prepMarkCmd(args, true);
		case "unmark":
			return prepMarkCmd(args, false);
		case "todo":
			return prepAdd(args, "todo");
		case "deadline":
			return prepAdd(args, "deadline");
		case "event":
			return prepAdd(args, "event");
		case "delete":
			return prepDeleteCmd(args);
		case "bye":
			return new ExitCmd();
		default:
			return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
		}

	}

	private Cmd prepAdd(String args, String taskType) {
		switch (taskType) {
		case "todo":

		String[] parts = args.split("p/");
		// Validate arg string format
		if (parts.length != 2) {
			return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
		}
		try {
			return new AddCommand(
					parts[0].trim(),
					parts[1].trim()
			);
		} catch (IllegalValueException ive) {
			return new IncorrectCommand(ive.getMessage());
		}
	}
	}
	/**
	 * Parses the given arguments string as a single index number.
	 *
	 * @param args arguments string to parse as index number
	 * @return the parsed index number
	 * @throws ParseException if no region of the args string could be found for the index
	 * @throws NumberFormatException the args string region is not a valid number
	 */
	private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
		if (args.isEmpty()) {
			throw new ParseException("Could not find index number to parse");
		}
		return Integer.parseInt(args.split(" ")[0]);
	}


	/**
	 * Signals that the user input could not be parsed.
	 */
	public static class ParseException extends Exception {
		ParseException(String message) {
			super(message);
		}
	}
}
