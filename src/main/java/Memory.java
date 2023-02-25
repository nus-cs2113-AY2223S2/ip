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
	public void writeToFile() throws IOException {
		FileWriter fw = new FileWriter(this.filePath);
		for (String s : commandInput) {
			fw.write(s);
			fw.write('\n');
		}
		fw.close();
	}
	public void appendToFile(String userInput) throws IOException {
		FileWriter fw = new FileWriter(this.filePath, true);
		fw.write(userInput);
		fw.write('\n');
		fw.close();
	}
}