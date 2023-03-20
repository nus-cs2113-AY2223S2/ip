package DukeManager.Parser;


import DukeManager.Commands.*;
import DukeManager.data.DukeErrors.IllegalValueException;
import DukeManager.data.Tasks.Deadline;
import DukeManager.data.Tasks.Event;
import DukeManager.data.Tasks.Todo;

import static DukeManager.common.Messages.MSG_CMD_FORMAT_ERROR;
import static DukeManager.common.Messages.MSG_INVALID_TASK_DISPLAYED_INDEX;

public class Parser {

	/**
	 * Parses user input into command for execution.
	 *
	 * @param userInput full user input string
	 * @return the command based on the user input
	 */
	public static Cmd parse(String userInput) {
		String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments
		if (words.length == 0) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR, HelpCmd.MSG_USAGE));
		}
		final String cmdWord = words[0];

		switch (cmdWord) {
		case "help":
			return new HelpCmd();
		case "list":
			return new ListCmd();
		case "mark":
			return prepMarkCmd(words[1], true);
		case "unmark":
			return prepMarkCmd(words[1], false);
		case "todo":
			return prepAddTodo(words[1]);
		case "deadline":
			return prepAddDeadline(words[1]);
		case "event":
			return prepAddEvent(words[1]);
		case "delete":
			return prepDeleteCmd(words[1]);
		case "find":
			return prepFindCmd(words[1]);
		case "bye":
			return new ExitCmd();
		default:
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR, HelpCmd.MSG_USAGE));
		}
	}

	/**
	 * Parses arguments in the context of the add todo command.
	 *
	 * @param args full command args string
	 * @return the prepared command
	 */
	public static Cmd prepAddTodo(String args) {
		if (args == null) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR, AddCmd.MSG_USAGE_TODO));
		}
		try {
			return new AddCmd(new Todo (args));
		} catch (IllegalValueException ive) {
			return new IncorrectCmd(ive.getMessage());
		}
	}

	/**
	 * Parses arguments in the context of the add deadline command.
	 *
	 * @param args full command args string
	 * @return the prepared command
	 */
	public static Cmd prepAddDeadline(String args) {
		String[] parts = args.split("/by");
		// Validate arg string format
		if (parts.length != 2) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR, AddCmd.MSG_USAGE_DEADLINE));
		}
		try {
			return new AddCmd(new Deadline(
					parts[0].trim(),
					parts[1].trim())
			);
		} catch (IllegalValueException ive) {
			return new IncorrectCmd(ive.getMessage());
		}
	}

	/**
	 * Parses arguments in the context of the add event command.
	 *
	 * @param args full command args string
	 * @return the prepared command
	 */
	public static Cmd prepAddEvent(String args) {
		String[] parts1 = args.split("/from");
		// Validate arg string format
		if (parts1.length != 2) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR, AddCmd.MSG_USAGE_EVENT));
		}

		String[] parts2 = parts1[1].split("/to");
		if (parts2.length != 2) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR, AddCmd.MSG_USAGE_EVENT));
		}

		try {
			return new AddCmd(new Event(
					parts1[0].trim(),
					parts2[0].trim(),
					parts2[1].trim())
			);
		} catch (IllegalValueException ive) {
			return new IncorrectCmd(ive.getMessage());
		}
	}


	/**
	 * Parses arguments in the context of the delete person command.
	 *
	 * @param args full command args string
	 * @return the prepared command
	 */
	private static Cmd prepDeleteCmd(String args) {
		try {
			final int targetIndex = parseArgsAsDisplayedIndex(args);
			return new DeleteCmd(targetIndex);
		} catch (ParseException pe) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR,DeleteCmd.MSG_USAGE));
		} //catch (NumberFormatException nfe) {
			//return new IncorrectCmd(MSG_INVALID_TASK_DISPLAYED_INDEX);
		//}
	}


	/**
	 * Parses arguments in the context of the find task command
	 *
	 * @param args full command args string
	 * @return the prepared command
	 */
	private static Cmd prepFindCmd(String args) {
		try {
			final String keyword = args.trim();
			return new FindCmd(keyword);
		} catch (NumberFormatException nfe) {
			return new IncorrectCmd(MSG_INVALID_TASK_DISPLAYED_INDEX);
		}
	}

	/**
	 * Parses arguments in the context of the delete person command.
	 *
	 * @param args full command args string
	 * @return the prepared command
	 */
	private static Cmd prepMarkCmd(String args, boolean isDone) {
		try {
			final int targetIndex = parseArgsAsDisplayedIndex(args);
			return new MarkCmd(targetIndex, isDone);
		} catch (ParseException pe) {
			return new IncorrectCmd(String.format(MSG_CMD_FORMAT_ERROR,MarkCmd.MSG_USAGE));
		} catch (NumberFormatException nfe) {
			return new IncorrectCmd(MSG_INVALID_TASK_DISPLAYED_INDEX);
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
	private static int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
		if (args.isEmpty()) {
			throw new ParseException("Could not find index number to parse");
		}
		return Integer.parseInt(args);
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
