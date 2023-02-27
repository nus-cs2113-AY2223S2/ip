package duke.storage;

import duke.exceptions.TaskFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
	private final String filePath;
	private final File file;

	public final String FILE_NOT_FOUND = "File is not found";
	public final String NEW_FILE_CREATED = "New file is created";
	public final String CREATE_FILE_ERROR = "Failed to create file";

	public final String WRONG_TASK_FORMAT = "Task List format is wrong...please check your file...";


	private final String TODO_PATTERN = "T\\s\\|\\s[01]\\s\\|\\s.+$";
	private final String EVENT_PATTERN = "E\\s\\|\\s[01]\\s\\|\\s.+?\\s\\|\\s.+?\\s\\|\\s.+$";
	private final String DEADLINE_PATTERN = "D\\s\\|\\s[01]\\s\\|\\s.+?\\s\\|\\s.+$";


	public Storage(String filePath) {
		this.filePath = filePath;
		this.file = new File(filePath);
	}

	public void createFile() throws IOException {
		Path path = Paths.get(filePath);
		Files.createDirectories(path.getParent());
		file.createNewFile();
	}

	public boolean isCorrectFormat(String line) {
		return line.matches(TODO_PATTERN) || line.matches(EVENT_PATTERN) || line.matches(DEADLINE_PATTERN);
	}

	public void loadFile(TaskList taskList) throws FileNotFoundException, TaskFormatException {
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String taskString = sc.nextLine();
			if (isCorrectFormat(taskString)) {
				String divider = "\\s\\|\\s";
				String[] taskDetails = taskString.split(divider);

				String taskType = taskDetails[0];
				String description = taskDetails[2];
				boolean isCompleted = taskDetails[1].equals("1");

				if (taskType.equals("T")) {
					taskList.addTasks(new Todo(description, isCompleted));
				} else if (taskType.equals("D")) {
					String by = taskDetails[3];
					taskList.addTasks(new Deadline(description, by, isCompleted));
				} else {
					String from = taskDetails[3];
					String to = taskDetails[4];
					taskList.addTasks(new Event(description, from, to, isCompleted));
				}
			} else {
				throw new TaskFormatException();
			}
		}
	}

	public TaskList loadTasks() {
		TaskList taskList = new TaskList();
		try {
			loadFile(taskList);
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND);
			try {
				createFile();
			} catch (IOException error) {
				System.out.println(CREATE_FILE_ERROR);
			}
			System.out.println(NEW_FILE_CREATED);
		} catch (TaskFormatException e1) {
			System.out.println();
		} finally {
			System.out.println("Start now...");
		}
		return taskList;
	}

	public void writeToFile(TaskList tasksList) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write(tasksList.writeTaskList());
		writer.close();
	}
}
