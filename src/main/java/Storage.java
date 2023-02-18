import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage in charge of retrieving previously saved tasks, modifying and
 * saving tasks to the tasks.txt.
 */
public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the tasks which were previously stored in tasks.txt and returns these tasks
     * in a string array, with each task represented by a string.
     * @param ui Ui object containing methods for user interaction
     * @return An array of strings, with each string representing a previously saved task
     * @throws FileNotFoundException If the tasks.txt cannot be located and read
     */
    public ArrayList<String> getSavedTasks(Ui ui) throws FileNotFoundException {
        File dir = new File("data");
        File f = new File(filePath);

        // Creates directory for data file if it does not exist
        if (!dir.exists()){
            dir.mkdirs();
        }

        // Create data file if it does not exist
        try {
            f.createNewFile();
        } catch (IOException e) {
            ui.showCreateFileError();
        }

        // Reading from text file to get stored data
        Scanner s = new Scanner(f);
        ArrayList<String> savedTasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            savedTasks.add(line);
        }
        return savedTasks;
    }

    /**
     * Save all the tasks in the tracked array to tasks.txt file.
     * @param taskList TaskList object containing the array of tracked tasks
     * @param ui Ui object containing methods for user interaction
     */
    public void saveTasks(TaskList taskList, Ui ui) {
        try {
            writeToFile(taskList);
            ui.showSaveIsSuccessful();
            ui.showFarewell();
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    /**
     * For each task in the array of tracked tasks, write each task on a separate lines
     * to tasks.txt file. The data of each task is written in the following formats:
     * Todo Task: task_type|is_done|description|
     * Deadline Task: task_type|is_done|description|by
     * Event task: task_type|is_done|description|from|to
     * @param taskList TaskList object containing the array of tracked tasks
     * @throws IOException If there is an error writing the tasks.txt file
     */
    private void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.numOfTasks; i++) {
            // determine task type
            String taskType = taskList.tasks.get(i).getTaskType();

            // Add task type and isDone for each task
            String lineToWrite = taskType;
            if (taskList.tasks.get(i).isDone) {
                lineToWrite += "|1";
            } else {
                lineToWrite += "|0";
            }

            // Add description for each task
            lineToWrite += "|" + taskList.tasks.get(i).description;

            // Add by or (from and to) fields depending on task type
            if (taskType.equals("D")) {
                lineToWrite += "|" + taskList.tasks.get(i).getBy();
            } else if (taskType.equals("E")) {
                lineToWrite += "|" + taskList.tasks.get(i).getFrom() + "|" + taskList.tasks.get(i).getTo();
            }
            lineToWrite += System.lineSeparator();

            fw.write(lineToWrite);
        }
        fw.close();
    }
}
