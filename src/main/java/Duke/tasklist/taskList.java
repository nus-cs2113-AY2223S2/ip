package Duke.tasklist;

import Duke.parser.parse;
import Duke.storage.fileIO;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;

import java.util.ArrayList;

public class taskList {
	private static ArrayList<Task> tasks;

	public taskList () {

		tasks = fileIO.readFile ();
	}

	public static Task get (int index) {

		return tasks.get (index);
	}

	public static int size () {

		return tasks.size ();
	}

	public static void addTodo (String description) {
		tasks.add (new Task (description));
		fileIO.writeFile (tasks);
	}

	public static void addDeadline (String description) {
		String[] arrInput = parse.parseDeadline (description);
		tasks.add (new Deadline (arrInput[0], arrInput[1]));
		fileIO.writeFile (tasks);
	}

	public static void addEvent (String description) {
		String[] arrInput = parse.parseEventFrom (description);
		String[] eventInput = parse.parseEventTo (arrInput[1]);
		tasks.add (new Event (arrInput[0], eventInput[0], eventInput[1]));
		fileIO.writeFile (tasks);
	}

	public static void markTask (int index) {
		tasks.get (index).setIsDone (true);
		fileIO.writeFile (tasks);
	}

	public static void unmarkTask (int index) {
		tasks.get (index).setIsDone (false);
		fileIO.writeFile (tasks);
	}

	public static void deleteTask (int index) {
		tasks.remove (index);
		fileIO.writeFile (tasks);
	}


}
