package duke.data;

import duke.error.DukeException;
import duke.task.*;
import duke.ui.ErrorMessages;
import duke.ui.Symbols;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage { // deals with loading tasks from the file and saving tasks in the file
    public static final String DIRECTORY = "data";
    public static final String FILE_PATH = "data\\duke.txt";
    public final String path;

    public Storage() {
        this(FILE_PATH);
    }

    public Storage(String filePath) {
        path = filePath;
    }

    public void makeDirectory() throws DukeException {
        File dataDirectory = new File(DIRECTORY);
        if (!dataDirectory.exists() && !dataDirectory.mkdir()) {
            // if directory does not already exist and not able to make the directory
            throw new DukeException(); // todo?
            // terminate the program?
        }
    }

    public File openDataFile() {
        File dataFile = new File(FILE_PATH);
        try {
            dataFile.createNewFile(); // todo?
        } catch (Exception e) { // does program exit? todo
            System.out.println(ErrorMessages.CREATE_NEW_FILE_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
        return dataFile;
    }

    public ArrayList<Task> importData(File dataFile) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNext()) {
                String task = reader.nextLine();
                decodeTaskData(task, taskList);
            }

        } catch (FileNotFoundException e) {
            System.out.print(ErrorMessages.FILE_NOT_FOUND_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
        return taskList;
    }

    public static void decodeTaskData(String task, ArrayList<Task> taskList) {
        String[] taskInfo = task.split(Symbols.DATA_DELIMITER); // what if user task name includes " / "
        if (taskInfo[0].equals(Symbols.TODO)) {
            taskList.add(Task.totalTasks, new Todo(taskInfo[2]));
        } else if (taskInfo[0].equals(Symbols.DEADLINE)) {
            // deadline
            taskList.add(Task.totalTasks, new Deadline(taskInfo[2], taskInfo[3]));
        } else {
            // event
            String[] timeInterval = taskInfo[3].split(Symbols.DATA_EVENT_DATE_DELIMITER);
            taskList.add(Task.totalTasks, new Event(taskInfo[2], timeInterval[0], timeInterval[1]));
        }
        if (taskInfo[1].equals(Symbols.DATA_MARK)) {
            taskList.get(Task.totalTasks).markAsDone();
        }
        Task.incrementTotalTasks();
    }

    public static void encodeAndWriteTask(TaskList taskList, FileWriter fileData) throws IOException {
        for (int i = 0; i < Task.totalTasks; i += 1) {
            String taskData = taskList.getTaskEncoding(i);
            fileData.write(taskData + Ui.NEW_LINE);
        }
    }

    public static void updateSavedData(TaskList taskList) {
        try {
            FileWriter fileData = new FileWriter(FILE_PATH);
            encodeAndWriteTask(taskList, fileData);
            fileData.close();
        } catch (IOException e) {
            System.out.println(ErrorMessages.IO_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
    }
}
