import java.util.Scanner;

public class Duke {
	public static void main (String[] args) {
		printStart ();

		Scanner scan = new Scanner (System.in);
		String input = scan.nextLine ();
		String exit = "bye";
		String list = "list";
		Task tasks[] = new Task[100];
		int count = 0;

		while (!(input.equalsIgnoreCase (exit)) && !(input.isEmpty ())) {
			if (input.equalsIgnoreCase (list)) {
				listTasks (tasks, count);
			} else if (input.startsWith ("mark") || input.startsWith ("unmark")) {
				changeStatus (input, tasks);
			} else if (input.startsWith ("todo") || input.startsWith ("deadline") || input.startsWith ("event")) {
				count = addTask (input, tasks, count);
			} else {
				break;
			}
			input = scan.nextLine ();
		}
		printEnd ();

	}

	public static int addTask (String input, Task[] tasks, int count) {
		String task = "Todo";
		String deadline = "deadline";
		String event = "event";
		String[] arrInput = input.split (" ", 2);
		printLine ();
		System.out.println ("Got it. I've added this task:");
		if (arrInput[0].equalsIgnoreCase (task)) {
			tasks[count] = new Task (arrInput[1]);
		} else if (arrInput[0].equalsIgnoreCase (deadline)) {
			String[] arrTask = arrInput[1].split ("/by");
			tasks[count] = new Deadline (arrTask[0], arrTask[1]);
		} else {
			String[] arrTask = arrInput[1].split ("/from");
			String[] eventTask = arrTask[1].split ("/to");
			tasks[count] = new Event (arrTask[0], eventTask[0], eventTask[1]);
		}
		System.out.println (tasks[count].getType () + tasks[count].toString ());
		count++;
		System.out.println ("Now you have " + count + " tasks in the list.");
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


