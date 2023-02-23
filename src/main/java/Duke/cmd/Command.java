package Duke.cmd;

import Duke.exception.DukeException;
import Duke.tasklist.taskList;
import Duke.ui.textUI;


import static Duke.parser.parse.parseTask;
import static Duke.ui.textUI.printLine;

public class Command {

	public static boolean checkCmd (String input) {
		if ("mark".equalsIgnoreCase (input) || "unmark".equalsIgnoreCase (input) || "todo".equalsIgnoreCase (input)
				|| "event".equalsIgnoreCase (input) || "deadline".equalsIgnoreCase (input)
				|| "delete".equalsIgnoreCase (input) || "find".equalsIgnoreCase (input)) {
			return true;
		} else {
			return false;
		}
	}

	public static void checkInput (taskList tasks, String input) throws DukeException {
		String[] arrInput = parseTask (input);
		boolean hasDescription = (arrInput.length > 1);
		String cmd = arrInput[0];
		if (hasDescription) {
			String description = arrInput[1];
			readCmd (tasks, cmd, description);
		} else if ("list".equalsIgnoreCase (cmd)) {
			textUI.listTasks (tasks);
		} else if (checkCmd (cmd)) {
			printLine ();
			throw new DukeException ("OOPS!!! The description of a " + cmd + " cannot be empty.");
		} else {
			throw new DukeException ("OOPS!!! I'm sorry, but I don't know what that means :-(");
		}

	}

	public static int checkIndex (taskList tasks, String cmd, String description) throws DukeException {
		int index;
		try {
			index = Integer.parseInt (description) - 1;
		} catch (NumberFormatException e) {
			throw new DukeException ("OOPS!!! The description of a " + cmd + " must be a number.");
		}
		if (index >= tasks.size () || index < 0) {
			throw new DukeException ("Task " + (index + 1) + " does not exist.");
		} else {
			return index;
		}
	}

	public static void readCmd (taskList tasks, String cmd, String description) throws DukeException {
		switch (cmd) {
			case "todo":
				tasks.addTodo (description);
				textUI.printTask (tasks);
				break;
			case "deadline":
				tasks.addDeadline (description);
				textUI.printTask (tasks);
				break;
			case "event":
				tasks.addEvent (description);
				textUI.printTask (tasks);
				break;
			case "mark":
				int mIndex = checkIndex (tasks, cmd, description);
				tasks.markTask (mIndex);
				textUI.printStatus (tasks, mIndex);
				break;
			case "unmark":
				int uIndex = checkIndex (tasks, cmd, description);
				tasks.unmarkTask (uIndex);
				textUI.printStatus (tasks, uIndex);
				break;
			case "delete":
				int dIndex = checkIndex (tasks, cmd, description);
				textUI.printDelete (tasks, dIndex - 1);
				tasks.deleteTask (dIndex);
				break;
			case "find":
				textUI.findTasks (tasks, description);
				break;

		}
	}
}
