package DukeManager.Storage;

import DukeManager.data.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

	private static String FILE_PATH = null;
	private static ArrayList<Task> taskList;

	public Storage(String file_path) {
		FILE_PATH = file_path;
	}

	public static ArrayList<Task> load() throws FileNotFoundException {
		Scanner reader = new Scanner(new File(FILE_PATH))
		String line;
		while (reader.hasNext()) {
			line = reader.nextLine();
			String[] parts = line.split(" ", 2);
			String taskType = parts[0];
			boolean status = parts[1].charAt(1) == 'X';
			String taskDesc = parts[1].substring(4);
			switch (taskType) {
			case ("[D]"):
				deadlineAdd(taskDesc);
				break;
			case ("[E]"):
				eventAdd(taskDesc);
				break;
			case ("[T]"):
				todoAdd(taskDesc);
				break;
			default:
			}
			Task task = taskList.get(taskList.size() - 1);
			task.setDone(status);
		}
		return taskList;
	}

	private static void save(ArrayList<Task> taskArrayList) {
		try {
			writeToFile(taskArrayList);
		} catch (IOException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}

	private static void writeToFile(ArrayList<Task> taskArrayList) throws IOException {
		FileWriter fw = new FileWriter(FILE_PATH);
		for (Task task : taskArrayList) {
			fw.write(task + "\n");
		}
		fw.close();
	}
}
