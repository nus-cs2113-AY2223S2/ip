/**
 * Memory is a class that saves the tasks in the hard disk automatically whenever the task list changes.
 * And loads the data from the hard disk when Duke starts up.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Memory {
	private String filePath;
	private ArrayList<String> fileContents = new ArrayList<>();
	private ArrayList<String> commandInput = new ArrayList<>();
	public Memory(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * Loads the contents of the file into an ArrayList of Strings.
	 *
	 * @return an ArrayList of Strings containing the contents of the file
	 * @throws IOException if there is an error reading from the file
	 */
	public ArrayList<String> loadFile() throws IOException {
		File F = new File(filePath);
		if (!F.exists()) {
			F = new File("duke.txt");
			this.filePath = F.getPath();
		}
		Scanner s = new Scanner(F);
		while (s.hasNext()) {
			fileContents.add(s.nextLine());
		}
		return fileContents;
	}
	/**
	 * Writes the contents of the commandInput ArrayList to the file.
	 *
	 * @throws IOException if there is an error writing to the file
	 */
	public void writeToFile() throws IOException {
		FileWriter fw = new FileWriter(this.filePath);
		for (String s : commandInput) {
			fw.write(s);
			fw.write('\n');
		}
		fw.close();
	}
	/**
	 * Appends the specified userInput String to the file.
	 *
	 * @param userInput the String to append to the file
	 * @throws IOException if there is an error writing to the file
	 */
	public void appendToFile(String userInput) throws IOException {
		FileWriter fw = new FileWriter(this.filePath, true);
		fw.write(userInput);
		fw.write('\n');
		fw.close();
	}
}