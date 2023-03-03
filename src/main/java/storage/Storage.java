package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import taskList.TaskList;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.createDirectories;

/**
 * Represent a data storage for the tasks of Duke.
 */
public class Storage {
    private Ui ui;
    private final String DIR_PATH = "." + File.separator + "data";
    private final String FILE_PATH = DIR_PATH + File.separator + "duke.txt";
    private final String DATA_SEPARATOR = " \\| ";
    private final int TASK_TYPE = 0;
    private final int STATUS = 1;
    private final int DESCRIPTION = 2;
    private final int FIRST_PARAMETER = 3;
    private final int SECOND_PARAMETER = 4;
    private final String STATUS_DONE = "1";

    /**
     * Repeatedly read in data from storage, parse the data and append a corresponding task to Duke's TaskList.
     *
     * @param taskList The TaskList of Duke
     */
    private void loadTasks(TaskList taskList) throws DukeException {
        while (this.ui.hasNextLineInput()) {
            String data = this.ui.getNextLineInput();
            // skip empty lines
            if (data.equals("")) {
                continue;
            }
            String[] parameters = data.split(DATA_SEPARATOR);
            Task task = createTask(parameters);
            taskList.add(task);
        }
    }

    /**
     * Create a directory named "./data" create a file in that directory.
     *
     * @param file The file object to be created in "./data".
     * @throws IOException if I/O error occurs in File.
     */
    private void createFile(File file) throws IOException {
        Path dirPath = Paths.get(DIR_PATH);
        createDirectories(dirPath);
        file.createNewFile();
    }

    /**
     * Load data from duke.txt into Duke's TaskList.
     *
     * @param dukeUi   The UI of Duke.
     * @param taskList The TaskList of Duke.
     */
    public void loadData(Ui dukeUi, TaskList taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                createFile(file);
                dukeUi.printFileCreated();
                return;
            }
            this.ui = new Ui(file);
            loadTasks(taskList);
            dukeUi.printDataLoadSuccess();
            this.ui.closeScanner();
        } catch (DukeException | IOException e) {
            String errorMessage = e.getMessage();
            dukeUi.printErrorMessage(errorMessage);
        }
    }

    /**
     * Create a Task Object from a given set of arguments.
     *
     * @param parameters The arguments used to create the Task Object.
     * @return A Task Object create using the set of arguments given.
     * @throws DukeException if an argument to create Task object is invalid.
     */
    private Task createTask(String[] parameters) throws DukeException {
        Task newTask;
        switch (parameters[TASK_TYPE]) {
        case "T":
            newTask = new Todo(parameters[DESCRIPTION]);
            break;
        case "D":
            newTask = new Deadline(parameters[DESCRIPTION], parameters[FIRST_PARAMETER]);
            break;
        case "E":
            newTask = new Event(parameters[DESCRIPTION], parameters[FIRST_PARAMETER], parameters[SECOND_PARAMETER]);
            break;
        default:
            throw new DukeException("Unrecognized data!");
        }
        if (parameters[STATUS].equals(STATUS_DONE)) {
            newTask.markDone();
        }
        return newTask;
    }

    /**
     * Update the data in duke.txt to the current TaskList of Duke.
     *
     * @param taskList The TaskList of Duke.
     * @throws IOException if an I/O error occurs in FileWriter.
     */
    public void updateData(TaskList taskList) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            content.append(task.getDataSummary());
            content.append("\n");
        }
        content.append("\n"); // end file content with newline
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        fileWriter.write(content.toString());
        fileWriter.close();
    }
}
