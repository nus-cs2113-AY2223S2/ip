package duke.storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Loads the stored task list.
 * If the local file is not found, creates a new one.
 * Some methods are adapted from the class demonstration project "TaskS" created by
 * Professor Akshay Narayan.
 * The original code can be found at :
 * <a href="https://github.com/nus-cs2113-AY2223S2/TaskS/blob/master/src/se/edu/inclass/data/DataManager.java">...</a>.
 */
public class Storage {
    private File dataFile;
    protected final String filePath;
    protected ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(this.filePath);
        this.tasks = new ArrayList<>();
    }

    public File getDataFile() {
        return dataFile;
    }

    /**
     * Creates a new file according to the file path.
     * If the directory in the file path does not exist, creates one.
     *
     * @throws IOException If I/O errors occur when creating file.
     */
    public void createFile() throws IOException {
        File file = new File(this.filePath);
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        file.createNewFile();
    }

    /**
     * Reads the local file which stores the task data.
     * Reuses the code written by Professor Akshay Narayan in "TaskS".
     *
     * @return Task list stored in the local file.
     * @throws IOException If the file cannot be created.
     */
    private ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException();
        }
        if (dataFile.length() == 0) {
            throw new IOException();
        }
        ArrayList<String> dataItems = (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    /**
     * Loads tasks in the local file to an arraylist by parsing them.
     * If the file is not found, creates one.
     * Adapted from the code written by Professor Akshay Narayan in "TaskS".
     *
     * @return Task list stored in the local file.
     * @throws IOException If the file cannot be created.
     */
    public ArrayList<Task> loadData() throws IOException {
        try {
            ArrayList<String> dataItems = readFile();
            tasks = parse(dataItems);
        } catch (FileNotFoundException e) {
            System.out.println(Ui.LINE + e);
            try {
                createFile();
            } catch (IOException error) {
                System.out.println("Meow! Failed to create a file: " + e.getMessage()
                        + System.lineSeparator() + Ui.LINE);
            }
            System.out.println("A new local file created to store your tasks!"
                    + System.lineSeparator() + Ui.LINE);
        } catch (IOException e) {
            System.out.println("Empty local file! Time to add some tasks!"
                    + System.lineSeparator() + Ui.LINE);
        }
        return tasks;
    }

    /**
     * Parse the data items into tasks and stores them in a task list.
     * Adapted from the code written by Professor Akshay Narayan in "TaskS".
     *
     * @param dataItems A list of data items that are read from the local file.
     * @return Updated task list as an arraylist of tasks.
     */
    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (String line : dataItems) {
            String taskType = getTaskType(line);
            String taskDescription = getTaskDescription(line);
            String taskStatus = getTaskStatus(line);
            switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDescription);
                if (taskStatus.equals("X")) {
                    todo.setDone();
                }
                allTasks.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(getTrimmedDescription(taskDescription),
                        getDeadline(taskDescription));
                if (taskStatus.equals("X")) {
                    deadline.setDone();
                }
                allTasks.add(deadline);
                break;
            case "E":
                Event event = new Event(getTrimmedDescription(taskDescription),
                        getEventDuration(taskDescription)[0], getEventDuration(taskDescription)[1]);
                if (taskStatus.equals("X")) {
                    event.setDone();
                }
                allTasks.add(event);
                break;
            default:
                System.out.println("Unknown task encountered. Skipping");
                break;
            }
        }
        return allTasks;
    }

    /**
     * Returns the task description from a line in data items.
     * Adapted from the code written by Professor Akshay Narayan in "TaskS".
     *
     * @param line A line from data items read from the local file.
     * @return Task description, possibly with deadlines or event durations that have not been parsed.
     */
    private String getTaskDescription(String line) {
        String taskDescription = line.substring(7);
        return taskDescription;
    }

    /**
     * Returns the task type from a line in data items.
     * Adapted from the code written by Professor Akshay Narayan in "TaskS".
     *
     * @param line A line from data items read from the local file.
     * @return Task type as a string, which can be T, D or E, representing task, deadline or event respectively.
     */
    private String getTaskType(String line) {
        String taskType = line.substring(0, 2);
        taskType = taskType.replace("[", "");
        taskType = taskType.replace("]", "");
        return taskType;
    }

    /**
     * Returns the task status from a line in data items.
     * Adapted from the code written by Professor Akshay Narayan in "TaskS".
     *
     * @param line A line from data items read from the local file.
     * @return Task status as a string, which can be X or null, representing done or not done respectively.
     */
    private String getTaskStatus(String line) {
        String taskStatus = line.substring(3, 5);
        taskStatus = taskStatus.replace("[", "");
        taskStatus = taskStatus.replace("]", "");
        return taskStatus;
    }

    /**
     * Returns the parsed deadline of a task.
     *
     * @param taskDescription The description of a task with deadline not parsed.
     * @return Deadline as a string.
     */
    private String getDeadline(String taskDescription) {
        String deadline = taskDescription.split("\\(by: ", 2)[1].replace(")", "");
        return deadline;
    }

    private String[] getEventDuration(String taskDescription) {
        String[] temp = taskDescription.split("to: ", 2);
        String to = temp[1];
        String from = temp[0].split("from: ", 2)[1];
        temp[1] = "/to" + to;
        temp[0] = "/from" + from;
        return temp;
    }

    /**
     * Returns the completely parsed description of a task.
     *
     * @param taskDescription The description of a task with deadline or event duration not parsed.
     * @return Trimmed task description as a string.
     */
    private String getTrimmedDescription(String taskDescription) {
        String trimmedDescription = taskDescription.split(" \\(", 2)[0];
        return trimmedDescription;
    }
}