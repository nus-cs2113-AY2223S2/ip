package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static final String FILENAME = "tasklist.txt";
    private static final String FILEPATH = FILE_DIRECTORY + "/" + FILENAME;

    /**
     * Create a directory in the root project structure named "data" if directory is not found.
     */
    public void createDirectory() {
        File directoryName = new File(FILE_DIRECTORY);
        if (!directoryName.exists()) {
            directoryName.mkdir();
        }
    }

    /**
     * @param listOfTasks An arraylist storing the list of tasks the user created.
     * @throws IOException Some IO Exception has occured.
     */
    public void writeToFile(ArrayList<Task> listOfTasks) throws IOException {
        FileWriter filewriter = new FileWriter(FILEPATH);
        for (Task task : listOfTasks) {
            filewriter.write(task.saveStringToFile() + System.lineSeparator());
        }
        filewriter.close();
    }

    /**
     * @param listOfTasks An arraylist storing the list of tasks the user created.
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public void readAndLoadFromFile(ArrayList<Task> listOfTasks) throws FileNotFoundException {
        File file = new File(FILEPATH);
        Scanner in = new Scanner(file);
        Task task;

        while (in.hasNext()) {
            String text = in.nextLine();
            String[] taskSplit = text.split("\\|");
            boolean isDone = taskSplit[1].equals("1");
            switch (taskSplit[0]) {
            case "T":
                task = new Todo(taskSplit[2]);
                if (isDone) {
                    task.markAsDone();
                }
                listOfTasks.add(task);
                break;
            case "D":
                task = new Deadline(taskSplit[2], taskSplit[3]);
                if (isDone) {
                    task.markAsDone();
                }
                listOfTasks.add(task);
                break;
            case "E":
                task = new Event(taskSplit[2], taskSplit[3]);
                if (isDone) {
                    task.markAsDone();
                }
                listOfTasks.add(task);
                break;
            }

        }
    }
}
