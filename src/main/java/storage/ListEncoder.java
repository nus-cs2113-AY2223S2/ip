package storage;

import tasks.Task;
import tasks.Tasks;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListEncoder {
	public static final String SAVE_FILE_PARTITION = " / ";
	public static final String MARK_SYMBOL = "1";
	public static final String UNMARK_SYMBOL = "0";
	
	public static void saveList(Tasks list, File saveFile) throws IOException {
		FileWriter fw = new FileWriter(saveFile);
		fw.flush();
		String fileContent = getFileContent(list);
		fw.write(fileContent);
		fw.close();
	}
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
