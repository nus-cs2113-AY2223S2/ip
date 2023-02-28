package storage;

import tasks.Tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * To create save file, and saves the list to be used upon future use of the program
 */
public class Storage {
	public static final String DEFAULT_SAVE_FILE_PATH = "data/save.txt";
	public static final String DEFAULT_SAVE_DIR_PATH = "./data";
	public static final String ERROR_DIRECTORY_CANNOT_BE_CREATED_MESSAGE = "Error: Data directory cannot be created";
	public static final String ERROR_FILE_CANNOT_BE_CREATED_MESSAGE = "Error: Save file cannot be created";
	public static final String ERROR_SAVE_FILE_NOT_FOUND_MESSAGE = "Error: Save file not found";
	
	protected static File saveDir = new File(DEFAULT_SAVE_DIR_PATH);
	protected static File saveFile = new File(DEFAULT_SAVE_FILE_PATH);
	
	public static Tasks loadSave() {
		final Tasks list = new Tasks();
		checkSaveDir(saveDir);
		checkSaveFile(saveFile);
		
		try {
			readSaveFile(saveFile, list);
		} catch (FileNotFoundException e) {
			System.out.println(ERROR_SAVE_FILE_NOT_FOUND_MESSAGE);
		}
		return list;
	}
	
	/**
	 * Saves list of tasks into the saveFile.
	 * @param list List of tasks of the user.
	 * @throws IOException
	 */
	public static void saveTasks(Tasks list) throws IOException {
		ListEncoder.saveList(list, saveFile);
	}
	
	/**
	 * checks if save directory is already present
	 * if save directory is missing, creates new one
	 * @param dir location of save directory to be checked
	 */
	private static void checkSaveDir(File dir) {
		if (!dir.isDirectory()) {
			if (!dir.mkdir()) { // creates directory named dir
				System.out.println(ERROR_DIRECTORY_CANNOT_BE_CREATED_MESSAGE);
			}
		}
	}
	
	/**
	 * checks if save file is already present
	 * if save file is missing, creates new save file
	 * @param file location of save file to be checked
	 */
	private static void checkSaveFile(File file) {
		try {
			file.createNewFile(); // creates new file named file if it is not there
 		} catch (IOException e) {
			System.out.println(ERROR_FILE_CANNOT_BE_CREATED_MESSAGE);
		}
	}
	
	/**
	 * looks through the save file and loads it onto the program's task list
	 * @param file path of the save file
	 * @throws FileNotFoundException
	 */
	private static void readSaveFile(File file, Tasks list) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		while (s.hasNext()) {
			String taskString = s.nextLine();
			ListDecoder.readTask(taskString, list);
		}
	}
}
