import java.util.Scanner;

public class Duke {
	public static void main (String[] args) {
		printStart ();

		Scanner scan = new Scanner (System.in);
		String input = scan.nextLine ();
		Task tasks[] = new Task[100];
		int count = 0;
		while (!("bye".equalsIgnoreCase (input)) && !(input.isEmpty ())) {
			try {
				checkInput (input, tasks, count);
			} catch (DukeException de) {
				System.out.println (de.getMessage ());
				System.out.println ("Please enter again: ");
				printLine ();
				continue;
			}finally {
				input = scan.nextLine ();
			}
		}

		printEnd ();

	}

	public static void checkInput (String input, Task[] tasks, int count) throws DukeException {
		if (input.length () > 4) {
			if (input.startsWith ("mark") || input.startsWith ("unmark")) {
				changeStatus (input, tasks);
			} else if (input.startsWith ("todo") || input.startsWith ("deadline") || input.startsWith ("event")) {
				count = addTask (input, tasks, count);
			} else {
				printLine ();
				throw new DukeException ("☹ OOPS!!! The description of a " + input + " cannot be empty.");
			}
		} else {
			if ("list".equalsIgnoreCase (input)) {
				listTasks (tasks, count);
			} else if ("mark".equalsIgnoreCase (input) || "unmark".equalsIgnoreCase (input) || "todo".equalsIgnoreCase (input) || "event".equalsIgnoreCase (input) || "deadline".equalsIgnoreCase (input)) {
				printLine ();
				throw new DukeException ("☹ OOPS!!! The description of a " + input + " cannot be empty.");
			} else {
				printLine ();
				throw new DukeException ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
			}
		}
	}

	public static int addTask (String input, Task[] tasks, int count) {
		String[] arrInput = input.split (" ", 2);
		printLine ();
		System.out.println ("Got it. I've added this task:");

		if ("todo".equalsIgnoreCase (arrInput[0])) {
			tasks[count] = new Task (arrInput[1]);
		} else if ("deadline".equalsIgnoreCase (arrInput[0])) {
			String[] arrTask = arrInput[1].split ("/by");
			tasks[count] = new Deadline (arrTask[0], arrTask[1]);
		} else {
			String[] arrTask = arrInput[1].split ("/from");
			String[] eventTask = arrTask[1].split ("/to");
			tasks[count] = new Event (arrTask[0], eventTask[0], eventTask[1]);
		}

		System.out.println (tasks[count].getType () + tasks[count].toString ());
		count++;
		System.out.println ("Now you have " + (count > 1 ? "tasks " : "task ") + " in the list.");
		printLine ();
		return count;
	}

	public static void changeStatus (String input, Task[] tasks) {
		String[] arrInput = input.split (" ", 2);
		int index = Integer.parseInt (arrInput[1]);
		index -= 1;

		if (input.startsWith ("mark")) {
			tasks[index].isDone = true;
		} else {
			tasks[index].isDone = false;
		}

		printLine ();
		System.out.println ("Nice! I've marked this task as done:");
		System.out.println (tasks[index].getType () + tasks[index].toString ());
		printLine ();
	}

	public static void listTasks (Task[] tasks, int count) {
		printLine ();
		System.out.println ("Here are the tasks in your list:");

		for (int i = 0; i < count; i++) {
			System.out.println ((i + 1) + ". " + tasks[i].getType () + tasks[i].toString ());
		}

		printLine ();
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


