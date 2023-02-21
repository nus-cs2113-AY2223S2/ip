import task.Deadline;
import task.Event;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a file data handler that is able to have features such as saving the current task list
 * and loading the saved task list from the previous session.
 */
public class FileDataHandler {
    protected String filePath;
    protected String directoryName;

    /**
     * Constructs a FileDataHandler object that takes in the filePath and directoryName.
     *
     * @param filePath the relative path to where the saved data will be loaded from and stored to.
     * @param directoryName the name of the folder in the saved data file is located.
     */
    public FileDataHandler(String filePath, String directoryName){
        this.filePath = filePath;
        this.directoryName = directoryName;
    }

    /**
     * Checks if the needed directory and save file already exists, else creates a new file and directory for the user.
     *
     * @return boolean that states if the file has been successfully created, or already exists.
     * @throws IOException if there is an error in creation of file or directory for the user.
     */
    public boolean createFile () throws IOException {
        File dataFile = new File(this.filePath);
        File dataDirectory = new File (this.directoryName);
        boolean isDirectoryCreated;
        boolean isFileCreated = dataFile.exists();
        if (dataDirectory.exists()) {
            isFileCreated = dataFile.createNewFile();
        } else {
            isDirectoryCreated = dataDirectory.mkdir();
            if (isDirectoryCreated) {
                isFileCreated = dataFile.createNewFile();
            }
        }
        return isFileCreated;
    }

    /**
     * Attempts to load the save file from the path "data/duke.txt".
     * If successful and file exists, read the file and calls the respective methods to add the different types of Tasks
     * into the arraylist initialized for the current session of Duke chatbot.
     *
     * @param taskList the arraylist that is to be populated with Task objects.
     * @throws FileNotFoundException if the save file cannot be found.
     */
    public void loadFile(TaskList taskList) throws FileNotFoundException {
        File dataFile = new File(filePath);
        Scanner input = new Scanner(dataFile);
        while (input.hasNextLine()) {
            String[] nextInput = input.nextLine().split(" \\| ", 2);
            if (nextInput[0].equals("T")) {
                loadTodoTask(nextInput[1], taskList);
            }
            if (nextInput[0].equals("D")) {
                loadDeadlineTask(nextInput[1], taskList);
            }
            if (nextInput[0].equals("E")) {
                loadEventTask(nextInput[1], taskList);
            }
        }
    }

    /**
     * Attempts to save the current contents of the arraylist into a save file called duke.txt by writing into the file.
     * Saves task according to their respective task type by calling their corresponding methods.
     *
     * @param taskList the arraylist that is now populated with Task objects to be written to the save file.
     * @throws IOException if writing to the save file fails.
     */
    public void saveFile (TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) instanceof Todo) {
                saveTodoTask((Todo) taskList.getTask(i), fileWriter);
            }
            if (taskList.getTask(i) instanceof Deadline) {
                saveDeadlineTask((Deadline) taskList.getTask(i), fileWriter);
            }
            if (taskList.getTask(i) instanceof Event) {
                saveEventTask((Event) taskList.getTask(i), fileWriter);
            }
            if (i == taskList.getSize() - 1) {
                break;
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Adds a task of the Todo type into the arraylist after converting from the save file format.
     *
     * @param description the details of the Todo task that is to be added to the arraylist.
     * @param taskList the arraylist that contains the Task objects.
     */
    public void loadTodoTask (String description, TaskList taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        Todo savedTodoTask = new Todo(details);
        if (inputs[0].equals("1")) {
            savedTodoTask.markAsDone();
        }
        taskList.addTask(savedTodoTask);
    }

    /**
     * Adds a task of the Deadline type into the arraylist after converting from the save file format.
     *
     * @param description the details of the Deadline task that is to be added to the arraylist.
     * @param taskList the arraylist that contains the Task objects.
     */
    public static void loadDeadlineTask (String description, TaskList taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        String by = inputs[2];
        Deadline savedDeadlineTask = new Deadline(details, by);
        if (inputs[0].equals("1")) {
            savedDeadlineTask.markAsDone();
        }
        taskList.addTask(savedDeadlineTask);
    }

    /**
     * Adds a task of the Event type into the arraylist after converting from the save file format.
     *
     * @param description the details of the Event task that is to be added to the arraylist.
     * @param taskList the arraylist that contains the Task objects.
     */
    public static void loadEventTask (String description, TaskList taskList) {
        String[] inputs = description.split(" \\| ");
        String details = inputs[1];
        String range = inputs[2];
        String[] ranges = range.split("-");
        String from = ranges[0];
        String to = ranges[1];
        Event savedEventTask = new Event(details, from, to);
        if (inputs[0].equals("1")) {
            savedEventTask.markAsDone();
        }
        taskList.addTask(savedEventTask);
    }

    /**
     * Saves a task of the Todo type into the save file duke.txt by writing to the file
     * with reference from the arraylist.
     * Writes in specific format for the save file.
     * @param newTodoTask the Todo task to be written to the save file.
     * @param todoWriter the FileWriter object that refers to the original FileWriter object created during saveFile().
     * @throws IOException if writing to the save file fails.
     */
    public static void saveTodoTask (Todo newTodoTask, FileWriter todoWriter) throws IOException {
        String details = newTodoTask.getDetails();
        String mark = "0";
        if (newTodoTask.getIsDone()) {
            mark = "1";
        }
        String combined = "T | " + mark + " | " + details;
        todoWriter.write(combined);
    }

    /**
     * Saves a task of the Deadline type into the save file duke.txt by writing to the file
     * with reference from the arraylist.
     * Writes in specific format for the save file.
     * @param newDeadlineTask the Deadline task to be written to the save file.
     * @param deadlineWriter the FileWriter object that refers to the original FileWriter object created during saveFile().
     * @throws IOException if writing to the save file fails.
     */
    public static void saveDeadlineTask (Deadline newDeadlineTask, FileWriter deadlineWriter) throws IOException {
        String details = newDeadlineTask.getDetails();
        String mark = "0";
        if (newDeadlineTask.getIsDone()) {
            mark = "1";
        }
        String by = newDeadlineTask.getBy();
        String combined = "D | " + mark + " | " + details + " | " + by;
        deadlineWriter.write(combined);
    }

    /**
     * Saves a task of the Event type into the save file duke.txt by writing to the file
     * with reference from the arraylist.
     * Writes in specific format for the save file.
     * @param newEventTask the Event task to be written to the save file.
     * @param eventWriter the FileWriter object that refers to the original FileWriter object created during saveFile().
     * @throws IOException if writing to the save file fails.
     */
    public static void saveEventTask (Event newEventTask, FileWriter eventWriter) throws IOException {
        String details = newEventTask.getDetails();
        String mark = "0";
        if (newEventTask.getIsDone()) {
            mark = "1";
        }
        String from = newEventTask.getFrom();
        String to = newEventTask.getTo();
        String combined = "E | " + mark + " | " + details + " | " + from + "-" + to;
        eventWriter.write(combined);
    }
}

