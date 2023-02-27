package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    public static final int COMMAND_INDEX = 0;
    public static final int TASK_DESCRIPTION_INDEX = 2;
    public static final int TASK_DEADLINE_INDEX = 3;
    public static final int TASK_FROM_INDEX = 3;
    public static final int TASK_TO_INDEX = 4;
    public static final int TASK_MARK_INDEX = 1;
    public static final String DIRECTORY_NAME = "data";
    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Arraylist of tasks that stores the loading of tasks from txt file and updates whenever there is
     * changes to TaskList
     */
    protected ArrayList<Task> tasks;

    /**
     * ArrayList of Strings that stores the database String format of the txt file when loading data
     */
    protected ArrayList<String> databaseString;

    /**
     * Constructor of Storage Class that creates a new ArrayList of tasks and ArrayList of Strings to be loaded
     * with data
     */
    public Storage() {
        this.tasks = new ArrayList<>();
        this.databaseString = new ArrayList<>();
        try {
            initialise();
        } catch (IOException e) {
            System.out.println("failed to initialise database");
        }
    }

    /**
     * Saves newly added task to the database txt file
     *
     * @param saveTaskString the string of the task that is being saved through a fixed format
     * @throws IOException when FileWriter fails to find the file path but writing is done to it
     */
    public void saveAddTask(String saveTaskString) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(writeTask(saveTaskString));
        fw.close();
    }

    /**
     * Does updating of the database whenever a new change such as delete or mark/unmark command is being called
     * This updating changes the txt file directly
     *
     * @throws IOException when the file path does not exist even though there is a writing of a new task
     */
    public void updateDatabaseTask() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.size(); ++i) {
            fw.write(writeTask(tasks.get(i).getTaskString()));
        }
        fw.close();
    }

    /**
     * Returns a formatted string by adding a line separator to it so that when saved, it will go to the next line
     *
     * @param saveString the string that is to be saved into the database
     * @return the original string but with a line separator
     */
    public static String writeTask(String saveString) {
        saveString += System.lineSeparator();
        return saveString;
    }

    /**
     * Does initialisation of the entire Database
     * It creates a new directory and file being data/duke.txt when there is no file found
     * If there is already an existing file, it loads the data into the ArrayList of task and string
     *
     * @throws IOException when the file path does not exist but commands are being given to load from the file path
     */
    private void initialise() throws IOException {
        File savedData = new File(FILE_PATH);
        if (!savedData.exists()) {
            File directory = new File(DIRECTORY_NAME);
            directory.mkdirs();
            savedData.createNewFile();
            return;
        }
        Scanner contents = new Scanner(savedData);
        while (contents.hasNext()) {
            databaseString.add(contents.nextLine());
        }
        dataConversionFromDatabaseToArrayList();
    }

    /**
     * Does data conversion of the saved data from the ArrayList of Strings into a ready ArrayList of tasks
     * to store data correctly and be used by TaskList in the whole program
     */
    private void dataConversionFromDatabaseToArrayList() {
        for (String data : databaseString) {
            String[] SplitTaskConstituents = data.split(Task.COMMA_TASK_SEPARATOR);
            Task task = null;
            boolean isCorrupted = false;
            switch (SplitTaskConstituents[COMMAND_INDEX]) {
            case "T":
                task = new Todo(SplitTaskConstituents[TASK_DESCRIPTION_INDEX]);
                break;
            case "D":
                task = new Deadline(SplitTaskConstituents[TASK_DESCRIPTION_INDEX],
                        SplitTaskConstituents[TASK_DEADLINE_INDEX]);
                break;
            case "E":
                task = new Event(SplitTaskConstituents[TASK_DESCRIPTION_INDEX], SplitTaskConstituents[TASK_FROM_INDEX],
                        SplitTaskConstituents[TASK_TO_INDEX]);
                break;
            default:
                isCorrupted = true;
                break;
            }
            updateMarkings(SplitTaskConstituents, task, isCorrupted);
        }
    }

    /**
     * While loading the tasks, update whether the tasks have been marked before
     *
     * @param savedData   the String that has been saved previously in the data
     * @param task        the task to be updated whether it has been marked before
     * @param isCorrupted to be referenced from where only non-corrupted files are updated
     */
    private void updateMarkings(String[] savedData, Task task, boolean isCorrupted) {
        if (isCorrupted) {
            return;
        }
        if (savedData[TASK_MARK_INDEX].equals("true")) {
            task.setDone("mark");
        } else if (savedData[TASK_MARK_INDEX].equals("false")) {
            task.setDone("unmark");
        }
        tasks.add(task);
    }
}
