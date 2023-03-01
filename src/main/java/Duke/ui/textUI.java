package Duke.ui;


import Duke.tasklist.taskList;
/**
 * deals with interaction with user
 */
public class textUI {
	/**
	 * print welcome message
	 */
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

	/**
	 * Print the newly added task
	 * @param tasks stores the taskList
	 */
	public static void printTask (taskList tasks) {
		printLine ();
		System.out.println ("Got it. I've added this task:");
		System.out.println ("[" + tasks.get (tasks.size () - 1).getType () + "]" + tasks.get (tasks.size () - 1).toString ());
		System.out.println ("Now you have " + tasks.size () + (tasks.size () > 1 ? " tasks " : " task ") + "in the list.");
		printLine ();
	}

	/**
	 * Print updated status of a specific task
	 * @param tasks stores the taskList
	 * @param index user specified task number
	 */
	public static void printStatus (taskList tasks, int index) {
		printLine ();
		System.out.println ("Nice! I've marked this task as " + (tasks.get (index).getIsDone () ? "done" : "undone") + ":");
		System.out.println ("[" + tasks.get (index).getType () + "]" + tasks.get (index).toString ());
		printLine ();
	}

	/**
	 * Print the deleted task
	 * @param tasks stores the taskList
	 * @param index user specified task number
	 */
	public static void printDelete (taskList tasks, int index) {
		printLine ();
		System.out.println ("Noted. I've removed this task:");
		System.out.println ("	" + tasks.get (index).getType () + tasks.get (index).toString ());
		System.out.println ("Now you have " + (tasks.size ()-1) + " tasks in the list.");
		printLine ();
	}

	/**
	 * Print all the stored tasks in the taskList
	 * @param tasks stores the taskList
	 */
	public static void listTasks (taskList tasks) {
		printLine ();
		if (tasks.size () > 0) {
			System.out.println ("Here are the tasks in your list:");
			for (int i = 0; i < tasks.size (); i++) {
				System.out.println ((i + 1) + ". " + "[" + tasks.get (i).getType () + "]" + tasks.get (i).toString ());
			}
		} else {
			System.out.println ("Your list is empty!");
		}
		printLine ();
	}

	/**
	 * Print all the tasks that contains the search keyword
	 * @param tasks store the taskList
	 * @param description user specified keyword
	 */
	public static void findTasks (taskList tasks, String description) {
		printLine ();
		boolean isFound = false;
		for (int i = 0; i < tasks.size (); i++) {
			if (tasks.get (i).getDescription ().contains (description)) {
				isFound = true;
				break;
			}
		}
		if (isFound) {
			System.out.println ("Here are the matching tasks in your list:");
			for (int i = 0; i < tasks.size (); i++) {
				if (tasks.get (i).getDescription ().contains (description)) {
					System.out.println ((i + 1) + ". " + "[" + tasks.get (i).getType () + "]" + tasks.get (i).toString ());
				}
			}
		} else {
			System.out.println ("There is no matching task.");
		}
		printLine ();
	}

	/**
	 * Print before exit program
	 */
	public static void printEnd () {
		printLine ();
		System.out.println ("Bye. Hope to see you again soon!");
		printLine ();
	}

	/**
	 * Print the line separator
	 */
	public static void printLine () {
		System.out.println ("____________________________________________________________");
	}

}


