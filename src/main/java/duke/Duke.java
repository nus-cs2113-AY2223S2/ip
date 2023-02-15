package duke;

import duke.exceptions.TaskDoneException;
import duke.exceptions.TaskException;
import duke.exceptions.TaskOutOfBoundsException;
import duke.exceptions.TaskUndoneException;
import duke.ui.Greetings;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Duke {

	public static final String DUKE_PATH = "data/duke.txt";
	public static TaskList taskList = new TaskList();
	public static File file = new File(DUKE_PATH);

	public static void createFile() throws IOException {
		//file.getParentFile().mkdirs();
		Path path = Paths.get(DUKE_PATH);
		Files.createDirectories(path.getParent());
		file.createNewFile();
	}

	public static void loadFile() throws FileNotFoundException {

		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String taskString = sc.nextLine();
			String divider = "\\s\\|\\s";
			String[] taskDetails = taskString.split(divider);

			String taskType = taskDetails[0];
			String description = taskDetails[2];
			boolean isCompleted = taskDetails[1].equals("1");


			if (taskType.equals("T")) {
				taskList.addTodo(description, isCompleted);
			} else if (taskType.equals("D")) {
				String by = taskDetails[3];
				taskList.addDeadline(description, by, isCompleted);
			} else {
				String[] fromAndTo = taskDetails[3].split(" to: ");
				String from = fromAndTo[0].replace("from: ","");
				String to = fromAndTo[1];
				taskList.addEvent(description,from,to,isCompleted);
			}
		}
	}

	public static void writeToFile(TaskList tasksList) throws IOException{
		FileWriter writer = new FileWriter(DUKE_PATH);
		writer.write(tasksList.writeTaskList());
		writer.close();
	}

	public static void loadTask(){
		try {
			loadFile();
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			try {
				createFile();
			} catch (IOException error){
				System.out.println("Failed to create file");
			}
			System.out.println("New file created");
		} finally {
			System.out.println("File found");
		}
	}


	public static void startDuke() {

		Greetings dukeGreeting = new Greetings();
		dukeGreeting.printGreetings();
		dukeGreeting.printOpeningLine();
		Scanner sc = new Scanner(System.in);

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
				System.out.println(taskList.addTodo(message, false));
				dukeGreeting.printDivider();

			} else if (userMessage.startsWith("deadline")) {

				userMessage.substring(10);

				String[] messages = userMessage.split(" /by ");
				dukeGreeting.printDivider();
				System.out.println(taskList.addDeadline(messages[0], messages[1], false));
				dukeGreeting.printDivider();

			} else if (userMessage.startsWith("event")) {
				userMessage.substring(7);
				String description = userMessage.split(" /from ")[0];
				String[] dates = userMessage.split(" /from ")[1].split(" /to");
				String from = dates[0];
				String to = dates[1];
				dukeGreeting.printDivider();
				System.out.println(taskList.addEvent(description, from, to, false));
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
				try {
					writeToFile(taskList);
				} catch (IOException e){
					System.out.println("Unable to write to file");
				}
				shouldExit = true;
				dukeGreeting.printExitLine();
			} else {
				dukeGreeting.printErrorMessage();
			}
		}
	}

	public static void main(String[] args) {

		loadTask();
		startDuke();
	}
}
