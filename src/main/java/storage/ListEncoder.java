package storage;

import tasks.Task;
import tasks.Tasks;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encodes list of tasks for the user and saves it in the desired save file with .txt format.
 */
public class ListEncoder {
	public static final String SAVE_FILE_PARTITION = " / ";
	public static final String MARK_SYMBOL = "1";
	public static final String UNMARK_SYMBOL = "0";
	
	/**
	 * Overwrites saveFile to input updated list of tasks.
	 * @param list List of tasks of the user.
	 * @param saveFile File to write the encoded list into.
	 * @throws IOException when file cannot be written into.
	 */
	public static void saveList(Tasks list, File saveFile) throws IOException {
		FileWriter fw = new FileWriter(saveFile);
		fw.flush();
		String fileContent = getFileContent(list);
		fw.write(fileContent);
		fw.close();
	}
	
	/**
	 * Generates the contents of the encoded list of task to write into the save file.
	 * @param list List ofo tasks of the user.
	 * @return output String to be written into the save file, containing the encoding of the task list.
	 */
	private static String getFileContent(Tasks list) {
		String output = "";
		int listSize = list.getTasksCount();
		for (int taskCount = 1; taskCount <= listSize; taskCount++) {
			Task task = list.getTask(taskCount);
			String taskType = task.getTaskType();
			output = output.concat(taskType + SAVE_FILE_PARTITION);
			if (task.getIsDone()) {
				output = output.concat(MARK_SYMBOL + SAVE_FILE_PARTITION);
			} else {
				output = output.concat(UNMARK_SYMBOL + SAVE_FILE_PARTITION);
			}
			String taskContent = task.getTaskContent();
			output = output.concat(taskContent + '\n');
		}
		return output;
	}
}
