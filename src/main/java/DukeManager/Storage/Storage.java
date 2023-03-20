package DukeManager.Storage;

import DukeManager.Commands.AddCmd;
import DukeManager.Commands.Cmd;
import DukeManager.data.TaskList;
import DukeManager.data.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DukeManager.Parser.Parser.*;

public class Storage {

	private static String FILE_PATH = null;
	private static ArrayList<Task> taskList = new ArrayList<>();

	public Storage(String file_path) {
		FILE_PATH = file_path;
	}

	public static ArrayList<Task> load() throws FileNotFoundException, TaskList.DuplicateTaskException {
		Scanner reader = new Scanner(new File(FILE_PATH));
		String line;
		while (reader.hasNext()) {
			line = reader.nextLine();
			String[] parts = line.split(" ", 2);
			String taskType = parts[0];
			boolean isDone = parts[1].charAt(1) == 'X';
			String taskDesc = parts[1].substring(4);
			Task task = null;
			switch (taskType) {
			case ("[D]"):
				task = prepAddDeadline(taskDesc).getTask();
				taskList.add(task);
				break;
			case ("[E]"):
				task = prepAddEvent(taskDesc).getTask();
				taskList.add(task);
				break;
			case ("[T]"):
				task = prepAddTodo(taskDesc).getTask();
				taskList.add(task);
				break;
			}
			TaskList.markTask(task, isDone);

		}
		return taskList;
	}

	public static void save(TaskList taskArrayList) {
		try {
			writeToFile(taskArrayList);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	private static void writeToFile(TaskList taskArrayList) throws IOException {
		FileWriter fw = new FileWriter(FILE_PATH);
		for (int i = 0; i < taskArrayList.size(); i++) {
			fw.write(String.valueOf(taskArrayList.getTask(i) + "\n"));
		}
		fw.close();
	}
}
