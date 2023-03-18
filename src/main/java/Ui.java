import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {
	// prints logo and welcome message
	public Ui() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
		System.out.println("Hello! I'm Duke");
		System.out.println("What can I do for you?");
	}

	// prints prompt for user to enter description
	public static void printEnterDescription() {
		System.out.println("enter description: ");
	}

	// prints when user enters invalid date format
	public static void printInvalidDate() {
		System.out.println("Invalid date format");
	}

	// prints prompt for user to enter deadline
	public static void printDeadlineDue() {
		System.out.println("enter deadline: " + ("eg: '2023-05-02T06:30' is equivalent May 02 2023 6.30am"));
	}

	// prints prompt for user to enter event start time
	public static void printEventFrom() {
		System.out.println("enter event start time: "
				+ ("eg: '2023-05-02T06:30' is equivalent May 02 2023 6.30am"));
	}

	// prints prompt for user to enter event end time
	public static void printEventTo() {
		System.out.println("enter event end time: "
				+ ("eg: '2023-05-02T06:30' is equivalent May 02 2023 6.30am"));
	}

	// prints the most recent task
	public static void printAddTask(int size, String dateOut, String item) { // print out item added after add command

		String type = "[" + Task.getType(size).toString().charAt(0) + "]";
		String checkbox = Task.getMark(size) ? "[X] " : "[ ] ";
		System.out.println("Got it. I've added this task: \n" + type + checkbox + item + dateOut + "\n"
				+ "Now you have " + Task.getSize() + " tasks in the list.");
	}

	// print item that is marked as done
	public static void printMarkDone(int num, String dateOut) {
		System.out.println("Nice! I've marked this as done:\n" + "["
				+ Task.getType(num - 1).toString().charAt(0) + "]" + "[X] " + Task.getDesc(num - 1)
				+ dateOut);
	}

	// print item that is marked as not done
	public static void printMarkNotDone(int num, String dateOut) {
		System.out.println("Ok, I've marked this as not done:\n" + "["
				+ Task.getType(num - 1).toString().charAt(0) + "]" + "[ ] " + Task.getDesc(num - 1)
				+ dateOut);
	}

	// print when user enters invalid index
	public static void printInvalidIndex(int num) {
		System.out.println("There is no task " + num + "!");
	}

	// prints when there are no tasks in the list
	public static void printNoTask() {
		System.out.println("There are no tasks yet!");
	}

	// prints index(num) and item in ArrayLists identified by index(index)
	public static void printItem(int num, String dateOut, int index) {
		System.out.println((num) + ". " + "[" + Task.getType(index).toString().charAt(0) + "]" + "["
				+ (Task.getMark(index) ? "X" : " ") + "] " + Task.getDesc(index) + dateOut);
	}

	// prints task due before date
	public static void printDueBeforeText(LocalDateTime dateTime) {
		System.out.println("Here are the tasks due before "
				+ dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm" + ":")));
	}

	// prints when there are no tasks due before date
	public static void printNoDueBefore(LocalDateTime dateTime) {
		System.out.println("There are no tasks due before "
				+ dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm" + ":")));
	}

	// prints task due after date
	public static void printDueAfterText(LocalDateTime dateTime) {
		System.out.println("Here are the tasks due after "
				+ dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm" + ":")));
	}

	// prints when there are no tasks due after date
	public static void printNoDueAfter(LocalDateTime dateTime) {
		System.out.println("There are no tasks due after "
				+ dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm" + ":")));
	}

	// print when task is not found
	public static void printNotFound() {
		System.out.println("No matching tasks found!");
	}

	// print when task is deleted
	public static void printDelete(int num, String dateOut) {
		System.out.println("Noted. I've removed this task:\n" + "[" + Task.getType(num - 1).toString().charAt(0) + "]"
				+ "[" + (Task.getMark(num - 1) ? "X" : "") + "] " + Task.getDesc(num - 1) + dateOut);
		System.out.println("Now you have " + (Task.getSize()) + " tasks in the list.");
	}

	// print goodbye message
	public static void printBye() {
		System.out.println("Bye. Hope to see you again soon!");
		printseparator();
	}

	// print guide for user to use the program
	public static void printHelp() {
		System.out.println("Here is a list of commands you can use:");
		System.out.println("1. todo <description>");
		System.out.println("	eg. todo read book");
		System.out.println("2. deadline <description> -> <deadline date time>");
		System.out.println("	eg. deadline return book -> 2023-05-02T06:30");
		System.out.println("3. event <description> -> <start date time> -> <end date time>");
		System.out.println("	eg. event project meeting -> 2023-05-02T06:30 -> 2023-05-02T07:30");
		System.out.println("4. mark <index>");
		System.out.println("	eg. mark 1");
		System.out.println("5. unmark <index>");
		System.out.println("	eg. unmark 1");
		System.out.println("6. delete <index>");
		System.out.println("	eg. delete 1");
		System.out.println("7. find <keyword>");
		System.out.println("	eg. find book");
		System.out.println("8. due <date>");
		System.out.println("	eg. due 2023-05-02T06:30");
		System.out.println("9. list");
		System.out.println("10. bye");
		printseparator();
	}

	// print a separator
	public static void printseparator() {
		System.out.println("____________________________________________________________");
	}

}
