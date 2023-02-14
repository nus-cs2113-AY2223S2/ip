package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class fileIO {
	private static final String FILEPATH = "data/duke.txt";
	protected static Task[] tasks = new Task[100];
	protected static int  count =0;
	public static int getCount(){
		return count;
	}
	public static Task[] readFile () {
		try {
			File dukeFile = new File (FILEPATH);
			BufferedReader reader = new BufferedReader (new FileReader (dukeFile));
			String data = reader.readLine();
			System.out.println ("File found. Reading file content:");
			while (data != null) {
				String[] arrData = data.split("\\|");
				if(data.startsWith ("T")){
					tasks[count] = new Task (arrData[2]);
				}else if(data.startsWith ("D")){
					tasks[count] = new Deadline (arrData[2],arrData[3]);
				}else{
					String[] arrEvent = arrData[3].split("-");
					tasks[count]= new Event(arrData[2],arrEvent[0],arrEvent[1]);
				}
				tasks[count].setIsDone ("1".equals (arrData[1]));
				count++;
				data = reader.readLine();
			}
			reader.close ();
		} catch (FileNotFoundException e) {
			System.out.println ("File not found.");
		} catch (IOException e) {
			System.out.println ("Failed to read file.");
		}
		return tasks;
	}

	public static void writeFile (Task[] tasks, int count) {
		try {
			FileWriter fileWrite = new FileWriter (FILEPATH, false);
			for (int i = 0; i < count; i++) {
				fileWrite.write (tasks[i].toFile () + System.lineSeparator ());
			}
			fileWrite.close ();
			System.out.println ("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println ("Failed to write file.");
		}
	}

}
