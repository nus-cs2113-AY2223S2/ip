package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;

import duke.common.Common;
import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.ToDo;
import duke.tasktypes.Task;

/**
 * Represents the storage of the data file.
 */
public class Storage {

    public static final String FILE_PATH = "data.txt";

    public static final String WRITEFILE_EXCEPTION_MESSAGE = "OPPS!!! Something went wrong when you write to data file";

    public static final String BACKSLASH = "/";

    public static final String PROBLEM_WITH_TRANSFERRING_DATA_TO_THE_ARRAYLIST = "There is a problem with transferring data to the arraylist";

    public File directory;
    public File file;
    public ArrayList<Task> tasks;

    /**
     * Constructs a new Storage object with the given file path and task list.
     * @param filePath The file path of the data file.
     * @param tasks The task list.
     */
    public Storage(String filePath, ArrayList<Task> tasks) {
        directory = new File("data");
        directory.mkdirs();
        file = new File(directory, filePath);
        this.tasks = tasks;
    }

    /**
     * Reads data from the storage into an ArrayList of Task objects.
     */
    public void moveDataToArrayList() {
        try {
            file.createNewFile();
            this.addTasks();
        } catch (Exception error) {
            System.out.println(PROBLEM_WITH_TRANSFERRING_DATA_TO_THE_ARRAYLIST);
        }
    }

    /**
     * Reads each line of the file and adds it as a task to the task list.
     * @throws FileNotFoundException if the file cannot be found
     */
    private void addTasks() throws FileNotFoundException {
        Scanner s = new Scanner(file); // create a Scanner using the file as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            addToTaskList(line);
        }
    }

    /**
     * Takes a line of text from the data file
     * Determines the task type and calls the appropriate method to add the task to the task list.
     * @param line a line of text from the data file containing task details
     */
    private void addToTaskList(String line) {
        String[] elements = line.split(Pattern.quote(Common.VERTICAL_BAR));
        if (elements[0].equals("T")) {
            addTodoToTaskList(elements);
        } else if (elements[0].equals("D")) {
            addDeadlineToTaskList(elements);
        } else {
            addEventToTaskList(elements);
        }
    }

    private void addTodoToTaskList(String[] elements) {
        ToDo task = new ToDo(elements[2]);
        setTaskStatus(elements[1],task);
        tasks.add(task);
    }

    private void addDeadlineToTaskList(String[] elements) {
        Deadline task = new Deadline(elements[2], elements[3]);
        setTaskStatus(elements[1],task);
        tasks.add(task);
    }

    private void addEventToTaskList(String[] elements) {
        Event task = new Event(elements[2], elements[3], elements[4]);
        setTaskStatus(elements[1],task);
        tasks.add(task);
    }

    /**
     * Sets the status of a task to done if the task is marked as done in the data file.
     * @param taskStatus the status of the task in the data file
     * @param task the task to be marked as done
     */
    private void setTaskStatus(String taskStatus, Task task) {
        if (taskStatus.equals("1")) {
            task.mark();
        } else {
            task.unMark();
        }
    }

    /**
     * Appends a string to the data file.
     * @param textToAppend the string to be appended to the data file
     * @throws IOException if there is a problem with appending the string to the data file
     */
    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(directory + BACKSLASH + FILE_PATH, true);
        fileWriter.write(textToAppend);
        fileWriter.close();
    }

    /**
     * Appends a task in string format to the data file.
     * @param inputToDataFile the string to be appended to the data file
     */
    public void appendTaskToDataFile(String inputToDataFile) {
        try {
            Common.dataFile.appendToFile(inputToDataFile);
        } catch (IOException e) {
            System.out.println(WRITEFILE_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Deletes a task from the data file.
     * @param lineNumber the position of the task to be deleted
     * @throws IOException if there is a problem with deleting the task
     */
    public void deleteTask(int lineNumber) throws IOException {
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        ArrayList<String> extractedTasks = new ArrayList<>();
        int lineCount = 0;
        while (s.hasNext()) {
            ++lineCount;
            String line = s.nextLine();
            if (lineCount == lineNumber) {
                continue;
            }
            extractedTasks.add(line);
        }
        String newFileContent = "";
        for (String line : extractedTasks) {
            newFileContent += (line + "\n");
        }
        FileWriter fileWriter = new FileWriter(directory + BACKSLASH + FILE_PATH);
        fileWriter.write(newFileContent);
        fileWriter.close();
    }

    /**
     * Updates the data file with the latest task list.
     * @throws IOException if there is a problem with updating the data file
     */
    public void updateTask() throws IOException {
        String updatedFileContent = "";
        for (Task task : Common.tasks) {
            updatedFileContent += task.putInputToDataFile();
        }
        FileWriter fileWriter = new FileWriter(directory + BACKSLASH + FILE_PATH);
        fileWriter.write(updatedFileContent);
        fileWriter.close();
    }

}