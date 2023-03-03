package duke.storage;

import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private File file;

    /**
     * Initialises an instance of Storage.
     * Create and store a file object into the instance of Storage.
     *
     * @param filePath File path of duke.txt.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Read data from duke.txt and parses the data into a task array list.
     *
     * @return Task array list loaded from duke.txt.
     * @throws IOException if duke.txt is an empty file or does not exist.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<String> data = readFile();
        ArrayList<Task> tasks = parse(data);

        return tasks;
    }

    /**
     * Writes task array list into duke.txt.
     * Creates ./data/duke.txt if it does not exist.
     *
     * @param tasks Task array list
     * @throws IOException if there is an error creating ./data/duke.txt or writing task array list to duke.txt.
     */
    public void writeFile(ArrayList<Task> tasks) throws IOException {
        createFile();
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks) {
            String textToAdd = task.toFile() + "\n";
            fw.write(textToAdd);
        }

        fw.close();
    }

    /**
     * Reads data from duke.txt and stores it in a string array list.
     *
     * @return Data from duke.txt formatted as a string array list.
     * @throws IOException if duke.txt is an empty file or does not exist.
     */
    private ArrayList<String> readFile() throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (file.length() == 0) {
            throw new IOException();
        }

        ArrayList<String> data = (ArrayList) Files.readAllLines(file.toPath(), Charset.defaultCharset());
        return data;
    }

    /**
     * Creates ./data/duke.txt if it does not exist.
     *
     * @throws IOException if there is an error creating ./data/duke.txt.
     */
    private void createFile() throws IOException {
        if (file.exists()) {
            return;
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }


    /**
     * Parses data read from duke.txt into a task array list.
     *
     * @param data Data from duke.txt formatted as a string array list.
     * @return Task array list
     */
    private ArrayList<Task> parse(ArrayList<String> data) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String line : data) {
            String taskType = getTaskType(line);
            String taskStatus = getTaskStatus(line);

            switch (taskType) {
            case "T":
                String toDoTaskName = getToDoTaskName(line);
                tasks.add(new Todo(toDoTaskName));
                break;
            case "D":
                String[] deadlineDetails = getDeadlineDetails(line);
                String deadlineTaskName = deadlineDetails[0];
                String by = deadlineDetails[1];
                tasks.add(new Deadline(deadlineTaskName, by));
                break;
            case "E":
                String[] eventDetails = getEventDetails(line);
                String eventTaskName = eventDetails[0];
                String from = eventDetails[1];
                String to = eventDetails[2];
                tasks.add(new Event(eventTaskName, from, to));
            default:
                break;
            }

            if (taskStatus.startsWith("1")) {
                tasks.get(tasks.size() - 1).setCompleted();
            }
        }

        return tasks;
    }

    /**
     * Retrieves task type from data string array list.
     *
     * @param line An entry in the data string array list.
     * @return Task type.
     */
    private String getTaskType(String line) {
        String[] words = line.split(" \\| ", 2);
        String taskType = words[0];
        return taskType;
    }

    /**
     * Retrieves task status from data string array list.
     *
     * @param line An entry in the data string array list.
     * @return Task status.
     */
    private String getTaskStatus(String line) {
        String[] words = line.split(" \\| ", 3);
        String taskStatus = words[1];
        return taskStatus;
    }

    /**
     * Retrieves to do task name from data string array list.
     *
     * @param line An entry in the data string array list.
     * @return To do task name.
     */
    private String getToDoTaskName(String line) {
        String[] words = line.split(" \\| ", 3);
        String toDoTaskName = words[2];
        return toDoTaskName;
    }

    /**
     * Retrieves deadline task name and end date/time from data string array list.
     *
     * @param line An entry in the data string array list.
     * @return A string array of deadline details containing task name and end date/time.
     */
    private String[] getDeadlineDetails(String line) {
        String[] words = line.split(" \\| ", 4);
        String deadlineTaskName = words[2];
        String by = words[3];
        String[] deadlineDetails = new String[]{deadlineTaskName, by};

        return deadlineDetails;
    }

    /**
     * Retrieves event task name, start date/time and end date/time from data string array list.
     *
     * @param line An entry in the data string array list.
     * @return A string array of event details containing task name, start date/time, end date/time.
     */
    private String[] getEventDetails(String line) {
        String[] words = line.split(" \\| ", 5);
        String eventTaskName = words[2];
        String from = words[3];
        String by = words[4];
        String[] eventDetails = new String[]{eventTaskName, from, by};

        return eventDetails;
    }
}