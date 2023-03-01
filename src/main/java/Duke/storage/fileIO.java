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

/**
 * Storage: deals with loading tasks from the file and saving tasks in the file
 */
public class fileIO {
    private static final String FILEPATH = "data/duke.txt";
    protected static ArrayList<Task> tasks = new ArrayList<> ();
    protected static int count = 0;

    public static int getCount () {

        return count;
    }

    /**
     * Read the tasks from file and add them to task list
     *
     * @return updated task list
     */
    public static ArrayList<Task> readFile () {
        try {
            File dukeFile = new File (FILEPATH);
            BufferedReader reader = new BufferedReader (new FileReader (dukeFile));
            String data = reader.readLine ();
            try {
                while (data != null) {
                    String[] arrData = data.split ("\\|");
                    if (data.startsWith ("T")) {
                        tasks.add (new Task (arrData[2].trim ()));
                    } else if (data.startsWith ("D")) {
                        tasks.add (new Deadline (arrData[2].trim (), arrData[3].trim ()));
                    } else {
                        String[] arrEvent = arrData[3].split ("-");
                        tasks.add (new Event (arrData[2].trim (), arrEvent[0].trim (), arrEvent[1].trim ()));
                    }
                    tasks.get (count).setIsDone ((arrData[1].trim ()).equals ("1"));
                    count++;
                    data = reader.readLine ();
                }
                reader.close ();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println ("File is empty");
            }
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

    /**
     * Write tasks from task list to file
     *
     * @param tasks task list
     */
    public static void writeFile (ArrayList<Task> tasks) {
        try {
            FileWriter fileWrite = new FileWriter (FILEPATH, false);
            for (int i = 0; i < tasks.size (); i++) {
                fileWrite.write (tasks.get (i).toFile () + System.lineSeparator ());
            }
            fileWrite.close ();
        } catch (IOException e) {
            System.out.println ("Failed to write file.");
        }
    }

}
