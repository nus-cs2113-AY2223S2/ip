package duke.data;

import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.error.Error;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.ui.ErrorMessages;
import duke.ui.Symbols;
import duke.ui.Ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from file and saving tasks in file
 */
public class Storage {

    public static final String DIRECTORY = "data";

    public static final String FILE_PATH = "data\\duke.txt";

    public final String path;

    public Storage(String filePath) {
        path = filePath;
    }

    /**
     * Checks whether the specified directory is present and creates one if absent
     *
     * @throws DukeException when directory does not exist and failed to create a directory
     */
    public void makeDirectory() throws DukeException {
        File dataDirectory = new File(DIRECTORY);
        if (!dataDirectory.exists() && !dataDirectory.mkdir()) {
            Error.throwError(ErrorTypes.ERROR_WITH_DIRECTORY);
        }
    }

    /**
     * Tries to open the file specified by FILE_PATH by creating the file if it does not exist
     *
     * @return the file opened
     */
    public File openDataFile() {
        File dataFile = new File(FILE_PATH);
        try {
            dataFile.createNewFile();
        } catch (Exception e) {
            System.out.println(ErrorMessages.CREATE_NEW_FILE_EXCEPTION_MESSAGE.MESSAGE);
        }
        return dataFile;
    }

    /**
     * Imports the task data from the file provided into an ArrayList
     *
     * @param dataFile file storing the data
     * @return array list of task representing the task list
     */
    public ArrayList<Task> importData(File dataFile) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNext()) {
                String task = reader.nextLine();
                decodeTaskData(task, taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.print(ErrorMessages.FILE_NOT_FOUND_EXCEPTION_MESSAGE.MESSAGE);
        }
        return taskList;
    }

    /**
     * Decodes the data from the file to be saved into Duke
     *
     * @param task     the line input from the data file containing the information of a task
     * @param taskList the task list to be used when Duke is running
     */
    public void decodeTaskData(String task, ArrayList<Task> taskList) {
        String[] taskInfo = task.split(Symbols.DECODE_DATA_DELIMITER);
        if (taskInfo[0].equals(Symbols.TODO)) {
            taskList.add(new Todo(taskInfo[2]));
        } else if (taskInfo[0].equals(Symbols.DEADLINE)) {
            taskList.add(new Deadline(taskInfo[2], taskInfo[3]));
        } else {
            String[] timeInterval = taskInfo[3].split(Symbols.DATA_EVENT_DATE_DELIMITER);
            taskList.add(new Event(taskInfo[2], timeInterval[0], timeInterval[1]));
        }
        if (taskInfo[1].equals(Symbols.DATA_MARK)) {
            int taskPosition = taskList.size() - 1;
            taskList.get(taskPosition).markAsDone();
        }
    }

    /**
     * Encodes the data from the task list to be saved into file
     *
     * @param taskList task list containing the tasks to be saved into file
     * @param fileData FileWriter opened with the FILE_PATH
     * @throws IOException when I/O error occurs
     */
    public void encodeAndWriteTask(TaskList taskList, FileWriter fileData) throws IOException {
        int taskCount = taskList.getTaskCount();
        for (int i = 0; i < taskCount; i += 1) {
            String taskData = taskList.getTaskEncoding(i);
            fileData.write(taskData + Ui.NEW_LINE);
        }
    }

    /**
     * Saves the data in task list into file
     *
     * @param taskList task list containing the tasks to be saved into file
     */
    public void updateSavedData(TaskList taskList) {
        try {
            FileWriter fileData = new FileWriter(FILE_PATH);
            encodeAndWriteTask(taskList, fileData);
            fileData.close();
        } catch (IOException e) {
            System.out.println(ErrorMessages.IO_EXCEPTION_MESSAGE.MESSAGE);
        }
    }
}
