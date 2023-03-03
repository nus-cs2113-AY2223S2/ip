package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.Parser;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * The Storage class deals with reading from and writing to a file to save and retrieve Task objects.
 */
public class Storage {
    private String filePath;
    private Parser parser = new Parser();

    /**
     * Constructs a Storage object that stores and retrieves Tasks from a given file path.
     *
     * @param filePath the file path where the Tasks are stored and retrieved from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the Tasks in a TaskList to the file specified by the file path.
     *
     * @param taskList the TaskList containing the Tasks to be written to file
     * @throws IOException if there is an error writing to the file
     */
    public void writeToFile(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTasks();
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter info = new BufferedWriter(fw);
        for (int i = 0; i < tasks.size(); i++) {
            String lineToAdd = parser.parseTaskToWrite(tasks.get(i));
            info.write(lineToAdd);
            info.newLine();
        }
        info.close();
    }

    /**
     * Reads the contents of the file specified by the file path and returns an ArrayList of Task objects.
     *
     * @return an ArrayList of Task objects stored in the file
     * @throws IOException if there is an error reading the file
     */
    public ArrayList<Task> readFileContents() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task;
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                task = parser.parseFileContent(sc.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            File newFile = new File(filePath);
            newFile.createNewFile();
        }
        return tasks;

    }
}
