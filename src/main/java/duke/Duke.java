package duke;

import duke.exceptions.TaskDoneException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.exceptions.TaskUndoneException;
import duke.ui.Greetings;
import duke.tasks.TaskList;


import java.util.Scanner;

public class Duke {

	public static void startDuke() {


		Greetings dukeGreeting = new Greetings();
		dukeGreeting.printGreetings();
		dukeGreeting.printOpeningLine();
		Scanner sc = new Scanner(System.in);
		TaskList taskList = new TaskList();

		String userMessage;

		boolean shouldExit = false;

		while (!shouldExit) {
			userMessage = sc.nextLine();
			if (userMessage.split(" ").length == 0) {
				System.out.println("Please key in proper task descriptions!!");
				dukeGreeting.printDivider();
			} else if (userMessage.startsWith("todo")) {

				String message = userMessage.substring(5);
				dukeGreeting.printDivider();
				System.out.println(taskList.addTodo(message));
				dukeGreeting.printDivider();

			} else if (userMessage.startsWith("deadline")) {

				userMessage.substring(10);

				String[] messages = userMessage.split(" /by ");
				dukeGreeting.printDivider();
				System.out.println(taskList.addDeadline(messages[0], messages[1]));
				dukeGreeting.printDivider();

			} else if (userMessage.startsWith("event")) {
				userMessage.substring(7);
				String description = userMessage.split(" /from ")[0];
				String[] dates = userMessage.split(" /from ")[1].split(" /to");
				String from = dates[0];
				String to = dates[1];
				dukeGreeting.printDivider();
				System.out.println(taskList.addEvent(description, from, to));
				dukeGreeting.printDivider();
			} else if (userMessage.startsWith("mark")) {
				dukeGreeting.printDivider();
				String[] messages = userMessage.split(" ");
				int taskNumber = Integer.parseInt(messages[1]);

				try {

					taskList.markAsDone(taskNumber);
					System.out.println("Niceeee!!I've marked this task as done: ");
					System.out.println(taskList.findTask(taskNumber).showTask());
				} catch (TaskException e) {
					System.out.println("Please key in the correct task index");
				} catch (TaskDoneException e) {
					System.out.println("You have already completed the task leh!");
				} finally {
					dukeGreeting.printDivider();
				}
			} else if (userMessage.startsWith("unmark")) {
				dukeGreeting.printDivider();
				String[] messages = userMessage.split(" ");
				int taskNumber = Integer.parseInt(messages[1]);
				try {
					taskList.markAsUndone(taskNumber);
					System.out.println("Okiee...I've unmarked this task: ");
					System.out.println(taskList.findTask(taskNumber).showTask());
				} catch (TaskException e) {
					System.out.println("Please key in the correct task index");
				} catch (TaskUndoneException e) {
					System.out.println("You have not completed the task:(");
				} finally {
					dukeGreeting.printDivider();
				}
			} else if (userMessage.equalsIgnoreCase("Hello")) {
				dukeGreeting.printDivider();
				System.out.println(userMessage);
				dukeGreeting.printDivider();
			} else if (userMessage.equalsIgnoreCase("list")) {
				dukeGreeting.printDivider();
				try {
					taskList.printList();
				} catch (TaskException e) {
					System.out.println("Sorry, there is no task in your list");
				} finally {
					dukeGreeting.printDivider();
				}
			} else if (userMessage.startsWith("delete")) {
				dukeGreeting.printDivider();
				String[] messages = userMessage.split(" ");
				int taskNumber = Integer.parseInt(messages[1]);
				try {
					System.out.println(taskList.deleteTask(taskNumber));
				} catch (TaskOutOfBoundsException e) {
					System.out.println("Please key in the correct task index");
				} finally {
					dukeGreeting.printDivider();
				}
			} else if (userMessage.equalsIgnoreCase("help")) {
				dukeGreeting.printHelp();
			} else if (userMessage.equalsIgnoreCase("exit")) {
				shouldExit = true;
				dukeGreeting.printExitLine();
			} else {
				dukeGreeting.printErrorMessage();
			}
		}
	}

	public static void main(String[] args) {
		startDuke();
	}
}
