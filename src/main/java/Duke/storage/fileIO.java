package Duke.storage;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class fileIO {
	private static final String FILEPATH = "data/duke.txt";
	protected static ArrayList<Task> tasks = new ArrayList<> ();
	protected static int count = 0;

	public static int getCount () {
		return count;
	}

	public static ArrayList<Task> readFile () {
		try {
			File dukeFile = new File (FILEPATH);
			BufferedReader reader = new BufferedReader (new FileReader (dukeFile));
			String data = reader.readLine ();
			System.out.println ("File found. Reading file content:");
			while (data != null) {
				String[] arrData = data.split ("\\|");
				if (data.startsWith ("T")) {
					tasks.add (new Task (arrData[2]));
				} else if (data.startsWith ("D")) {
					tasks.add (new Deadline (arrData[2], arrData[3]));
				} else {
					String[] arrEvent = arrData[3].split ("-");
					tasks.add (new Event (arrData[2], arrEvent[0], arrEvent[1]));
				}
				tasks.get (count).setIsDone (arrData[1].equals (" 1 "));
				count++;
				data = reader.readLine ();
			}
			reader.close ();
		} catch (FileNotFoundException e) {
			File fold = new File ("data");
			fold.mkdir ();
			File data = new File ("data/duke.txt");
			try {
				data.createNewFile ();
			} catch (IOException i) {
				System.out.println ("Failed to create file");
			}
		} catch (IOException e) {
			System.out.println ("Failed to read file.");
		}
		return tasks;
	}

	public static void writeFile (ArrayList<Task> tasks) {
		try {
			FileWriter fileWrite = new FileWriter (FILEPATH, false);
			for (int i = 0; i < tasks.size (); i++) {
				fileWrite.write (tasks.get (i).toFile () + System.lineSeparator ());
			}
			fileWrite.close ();
			System.out.println ("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println ("Failed to write file.");
		}
	}

}
