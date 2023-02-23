package Duke;

import java.util.Scanner;

import Duke.cmd.Command;
import Duke.exception.DukeException;
import Duke.tasklist.taskList;
import Duke.ui.textUI;

public class Duke {
	public static void main (String[] args) {
		textUI.printStart ();
		Scanner scan = new Scanner (System.in);
		String input = scan.nextLine ();
		taskList tasks = new taskList ();

		while (!("bye".equalsIgnoreCase (input)) && !(input.isEmpty ())) {
			try {
				Command.checkInput (tasks, input);
			} catch (DukeException de) {
				System.out.println (de.getMessage ());
				System.out.println ("Please enter again: ");
				textUI.printLine ();
			} finally {
				input = scan.nextLine ();
			}
		}
		textUI.printEnd ();
	}
}


