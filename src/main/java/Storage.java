import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final static String LOAD_PATH = "save/tasks.txt";
    File file;

    /**
     * Constructor for Storage
     *
     * @param filePath Path to locate file relative to project root.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Writes current TaskList onto file on hard drive after
     * checking if the directory the file is in and the file exists.
     * If they do not exist, create them
     *
     * @param tasks TaskList with tasks in Duke.
     * @throws IOException Thrown when file system encounters an error.
     */
    public static void save(TaskList tasks) throws IOException {
        File file = new File(LOAD_PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        for (Task task : tasks.asList()) {
            fileWriter.write(task.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Retrieves data from a previous session of Duke, if it exists,
     * and then format the data into an appropriate format to add into
     * the task list
     *
     * @return An array of Tasks.
     * @throws DukeException Thrown when unexpected behaviour occurs with Duke.
     */

    public Task[] load() throws DukeException {
        try {
            if (file.exists()) {
                Scanner readFile = new Scanner(file);
                ArrayList<Task> tasks = new ArrayList<>();
                while (readFile.hasNextLine()) {
                    Task task;
                    String currTask = readFile.nextLine();
                    String taskType = currTask.substring(0, 3);
                    String taskDetails = currTask.substring(7);
                    switch (taskType) {
                    case "[T]":
                        task = new Todo(taskDetails);
                        if (currTask.substring(3, 7).contains("X")) {
                            task.setDone(true);
                        }
                        break;
                    case "[D]":
                        String deadlineName = taskDetails.split("\\(by:")[0];
                        String by = taskDetails.split(" \\(by: ")[1].split("\\)")[0];
                        task = new Deadline(deadlineName, by);
                        if (currTask.substring(3, 7).contains("X")) {
                            task.setDone(true);
                        }
                        break;
                    case "[E]":
                        String eventName = taskDetails.split(" \\(from:")[0];
                        String eventTime = taskDetails.split("\\(from: ")[1];
                        String startTime = eventTime.split(" to: ")[0];
                        String endTime = eventTime.split(" to: ")[1].split("\\)")[0];
                        task = new Event(eventName, startTime, endTime);
                        if (currTask.substring(3, 7).contains("X")) {
                            task.setDone(true);
                        }
                        break;
                    default:
                        throw new DukeException("task type not saved properly");
                    }
                    tasks.add(task);
                }
                if (tasks.size() > 0) {
                    Ui.showWelcomeBackMessage();
                }
                for (int i = 0; i < tasks.size(); i += 1) {
                    System.out.println(i + 1 + ": " + tasks.get(i));
                }
                return tasks.toArray(new Task[0]);
            } else {
                return new Task[0];
            }
        } catch (DukeException | IOException exception) {
            throw new DukeException("error reading load file");
        }
    }
}
