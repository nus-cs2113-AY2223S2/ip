package storage;

import common.Messages;
import exceptions.CreateDirectoryException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskParser;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The storage class.
 * To manage file and directory related operations.
 */
public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static final String FILENAME = "tasklist.txt";
    private static final String FILEPATH = FILE_DIRECTORY + "/" + FILENAME;

    private Path dataDirectory;

    /**
     * Initialize the storage with the path to data directory.
     *
     * @param dataDirectory path to the data directory.
     */
    public Storage(Path dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    /**
     * Create a directory in the root project structure named "data" if directory is not found.
     */
    public void createDirectory() throws CreateDirectoryException {
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
        } catch (IOException e) {
            throw new CreateDirectoryException(String.format(Messages.ERROR_CREATE_DIRECTORY, dataDirectory));
        }
    }

    /**
     * Write the user tasks into a file.
     *
     * @param listOfTasks An arraylist storing the list of tasks the user created.
     * @throws IOException Some IO Exception has occured.
     */
    public void writeToFile(ArrayList<Task> listOfTasks) throws IOException {
        FileWriter filewriter = new FileWriter(FILEPATH);
        for (Task task : listOfTasks) {
            filewriter.write(task.savableString() + System.lineSeparator());
        }
        filewriter.close();
    }

    /**
     * Read and Load data from a file if it exists.
     *
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public TaskParser readAndLoadFromFile() throws FileNotFoundException {
        File file = new File(FILEPATH);
        Scanner in = new Scanner(file);
        ArrayList<Task> listOfTasks = new ArrayList<>();
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
        return new TaskParser(listOfTasks);
    }
}
