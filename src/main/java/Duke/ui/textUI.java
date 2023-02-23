package Duke.ui;


import Duke.tasklist.taskList;

public class textUI {

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

	public static void printTask (taskList tasks) {
		printLine ();
		System.out.println ("Got it. I've added this task:");
		System.out.println ("[" + tasks.get (tasks.size () - 1).getType () + "]" + tasks.get (tasks.size () - 1).toString ());
		System.out.println ("Now you have " + tasks.size () + (tasks.size () > 1 ? " tasks " : " task ") + "in the list.");
		printLine ();
	}

	public static void printStatus (taskList tasks, int index) {
		printLine ();
		System.out.println ("Nice! I've marked this task as " + (tasks.get (index).getIsDone () ? "done" : "undone") + ":");
		System.out.println ("[" + tasks.get (index).getType () + "]" + tasks.get (index).toString ());
		printLine ();
	}

	public static void printDelete (taskList tasks, int index) {
		printLine ();
		System.out.println ("Noted. I've removed this task:");
		System.out.println ("	" + tasks.get (index).getType () + tasks.get (index).toString ());
		System.out.println ("Now you have " + tasks.size () + " tasks in the list.");
		printLine ();
	}

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

	public static void printEnd () {
		printLine ();
		System.out.println ("Bye. Hope to see you again soon!");
		printLine ();
	}

	public static void printLine () {
		System.out.println ("____________________________________________________________");
	}

}


