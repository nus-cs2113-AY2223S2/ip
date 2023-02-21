package Duke;

import java.util.Scanner;
import java.util.ArrayList;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.exception.DukeException;

public class Duke {
	public static void main (String[] args) {
		printStart ();
		Scanner scan = new Scanner (System.in);
		String input = scan.nextLine ();
		ArrayList<Task> tasks = fileIO.readFile ();
		int count = fileIO.getCount ();

		while (!("bye".equalsIgnoreCase (input)) && !(input.isEmpty ())) {
			try {
				count = checkInput (input, tasks, count);
			} catch (DukeException de) {
				System.out.println (de.getMessage ());
				System.out.println ("Please enter again: ");
				printLine ();
				continue;
			} finally {
				input = scan.nextLine ();
			}
		}
		printEnd ();
	}

	public static int checkInput (String input, ArrayList<Task> tasks, int count) throws DukeException {
		if (input.length () > 4) {
			if (input.startsWith ("mark") || input.startsWith ("unmark")) {
				changeStatus (input, tasks);
				fileIO.writeFile (tasks);
			} else if (input.startsWith ("todo") || input.startsWith ("deadline") || input.startsWith ("event")) {
				count = addTask (input, tasks, count);
				fileIO.writeFile (tasks);
			} else if (input.startsWith ("delete") && !("delete".equalsIgnoreCase (input))) {
				count = deleteTask (input, tasks, count);
				fileIO.writeFile (tasks);
			} else {
				printLine ();
				throw new DukeException ("OOPS!!! I'm sorry, but I don't know what that means :-(");
			}
		} else {
			if ("list".equalsIgnoreCase (input)) {
				listTasks (tasks);
			} else if ("mark".equalsIgnoreCase (input) || "unmark".equalsIgnoreCase (input) || "todo".equalsIgnoreCase (input) || "event".equalsIgnoreCase (input) || "deadline".equalsIgnoreCase (input) || "delete".equalsIgnoreCase (input)) {
				printLine ();
				throw new DukeException ("OOPS!!! The description of a " + input + " cannot be empty.");
			} else {
				printLine ();
				throw new DukeException ("OOPS!!! I'm sorry, but I don't know what that means :-(");
			}
		}
		return count;
	}

	public static int addTask (String input, ArrayList<Task> tasks, int count) {
		String[] arrInput = input.split (" ", 2);
		printLine ();
		System.out.println ("Got it. I've added this task:");

		if ("todo".equalsIgnoreCase (arrInput[0])) {
			tasks.add (new Task (arrInput[1]));
		} else if ("deadline".equalsIgnoreCase (arrInput[0])) {
			String[] arrTask = arrInput[1].split ("/by");
			tasks.add (new Deadline (arrTask[0], arrTask[1]));
		} else {
			String[] arrTask = arrInput[1].split ("/from");
			String[] eventTask = arrTask[1].split ("/to");
			tasks.add (new Event (arrTask[0], eventTask[0], eventTask[1]));
		}
		System.out.println ("[" + tasks.get (count).getType () + "]" + tasks.get (count).toString ());
		count++;
		System.out.println ("Now you have " + count + (count > 1 ? " tasks " : " task ") + "in the list.");
		printLine ();
		return count;
	}

	public static void changeStatus (String input, ArrayList<Task> tasks) {
		String[] arrInput = input.split (" ", 2);
		int index = Integer.parseInt (arrInput[1]);
		index -= 1;

		if (input.startsWith ("mark")) {
			tasks.get (index).setIsDone (true);
		} else {
			tasks.get (index).setIsDone (false);
		}
		printLine ();
		System.out.println ("Nice! I've marked this task as " + (tasks.get (index).getIsDone () ? "done" : "undone") + ":");
		System.out.println ("[" + tasks.get (index).getType () + "]" + tasks.get (index).toString ());
		printLine ();
	}

	public static void listTasks (ArrayList<Task> tasks) {
		printLine ();
		System.out.println ("Here are the tasks in your list:");
		for (int i = 0; i < tasks.size (); i++) {
			System.out.println ((i + 1) + ". " + "[" + tasks.get (i).getType () + "]" + tasks.get (i).toString ());
		}
		printLine ();
	}

	public static int deleteTask (String input, ArrayList<Task> tasks, int count) {
		String[] arrInput = input.split (" ", 2);
		int index = Integer.parseInt (arrInput[1]);
		index -= 1;
		printLine ();
		System.out.println ("Noted. I've removed this task:");
		System.out.println ("	" + tasks.get (index).getType () + tasks.get (index).toString ());
		tasks.remove (index);
		count--;
		System.out.println ("Now you have " + count + " tasks in the list.");
		printLine ();
		return count;
	}

	public static void printStart () {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println ("Hello from\n" + logo);
		printLine ();
		System.out.println ("Hello! I'm Duke");
		System.out.println ("What can I do for you?");
		printLine ();
	}

	public static void printEnd () {
		printLine ();
		System.out.println ("Bye. Hope to see you again soon!");
		printLine ();
	}

	public static void printLine () {
		System.out.println ("____________________________________________________________");
	}

}


