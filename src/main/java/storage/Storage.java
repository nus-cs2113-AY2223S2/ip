package storage;
import exceptions.FileParseReadingException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;
import tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The <code>Storage</code> class contains various methods which involves
 * handling of the saved file. For instance, file reading, file writing and saving.
 */
public class Storage {

    private static final String SEPARATOR = " I:I ";
    public static final String DIRECTORY_CREATED = "\nDirectory for file saving created.";
    public static final String DIRECTORY_EXISTS = "\nDirectory for file saving already exists.";
    public static final String FILE_CREATED = "Save file created.";
    public static final String FILE_EXISTS = "Save file already exists.";
    public static final String FILE_PARSING_ERROR = "The task does not meet the parsing requirements for unpacking.";
    private static String filePath;

    /**
     * Sets the <code>filePath</code> to a specific value.
     *
     * @param filePath the String containing the location of the file path to be created.
     */
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Creates a new file and directory to store
     * save file, if it does not already exist.
     *
     * @throws IOException if an I/O error has occurred.
     */
    public static void createSavedFile() throws IOException {
        File f = new File(filePath);
        File directory = new File("data");
        if (directory.mkdir()) {
            System.out.println(DIRECTORY_CREATED);
        } else {
            System.out.println(DIRECTORY_EXISTS);
        }
        if (f.createNewFile()) {
            System.out.println(FILE_CREATED);
        } else {
            System.out.println(FILE_EXISTS);
        }
        System.out.println();
    }

    /**
     * Returns a list of Strings with each index containing a <code>String</code> format
     * of a <code>Task</code> present in the <code>taskList</code> input.
     * <p></p>
     * This is to be used to store the current list of tasks in a text file.
     *
     * @param taskList the list of tasks to be encoded into Strings.
     * @return a list of Strings containing all the <code>Task</code> present in <code>taskList</code>.
     */
    public static ArrayList<String> encodeTasksToString(ArrayList<Task> taskList) {
        ArrayList<String> dataToStore = new ArrayList<>();
        String type, status, description, by, from, to, index;
        String newTaskInString = "";
        Deadline thisDeadline;
        Event thisEvent;
        dataToStore.add("Tasks List");
        for (int i = 0; i < taskList.size(); i += 1) {
            index = Integer.toString(i+1);
            type = taskList.get(i).getType();
            switch (type) {
            case "T" :
                status = taskList.get(i).getStatusFileIcon();
                description = taskList.get(i).getDescription();
                newTaskInString = index + SEPARATOR  + type + SEPARATOR + status + SEPARATOR + description;
                break;
            case "D" :
                thisDeadline = (Deadline) taskList.get(i);
                status = thisDeadline.getStatusFileIcon();
                description = thisDeadline.getDescription();
                by = thisDeadline.getBy();
                newTaskInString = index + SEPARATOR + type + SEPARATOR + status + SEPARATOR + description + SEPARATOR + by;
                break;
            case "E" :
                thisEvent = (Event) taskList.get(i);
                status = thisEvent.getStatusFileIcon();
                description = thisEvent.getDescription();
                from = thisEvent.getStart();
                to = thisEvent.getEnd();
                newTaskInString = index + SEPARATOR + type + SEPARATOR + status + SEPARATOR + description + SEPARATOR + from + SEPARATOR + to;
            }
            dataToStore.add(newTaskInString);
        }
        return dataToStore;
    }

    /**
     * Decodes an encoded <code>Task</code> present in the input <code>str</code>.
     * Returns the <code>Task</code> from the input <code>str</code>.
     * <p></p>
     * This helps in the storing of the current list of tasks in a text file.
     *
     * @param str the encoded String containing <code>Task</code>.
     * @return a <code>Task</code> decoded from the input <code>str</code>.
     * @throws FileParseReadingException if <code>str</code> not encoded properly.
     */
    public static Task decodeTaskFromStrings(String str) throws FileParseReadingException {
        String[] parsed = str.split(SEPARATOR);
        Task task;
        switch (parsed[1]) {
        case "T":
            task = new ToDo(parsed[3]);
            break;
        case "D":
            task = new Deadline(parsed[3], parsed[4]);
            break;
        case "E":
            task = new Event(parsed[3], parsed[4], parsed[5]);
            break;
        default:
            throw new FileParseReadingException(FILE_PARSING_ERROR);
        }
        if (parsed[2].equals("0")) {
            task.markAsUndone();
        } else {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Prints the contents of the <code>taskList.txt</code> if it exists.
     *
     * @throws FileNotFoundException if <code>filePath</code> not found or saved file does not exist.
     */
    public static void showFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        System.out.println();
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Reads the contents of the task list stored in the saved file
     * specified by the <code>filePath</code>. Returns the contents
     * as an instance of a <code>TaskList</code> class;
     *
     * @return an instance of <code>TaskList</code> with its contents
     *         filled by the contents of the specified file.
     * @throws FileParseReadingException if the <code>Strings</code> in file not encoded properly.
     * @throws FileNotFoundException if the <code>filePath</code> cannot be found.
     */
    public static TaskList readFileContents() throws FileNotFoundException, FileParseReadingException {
        ArrayList<Task> list = new ArrayList<>();
        Task tempTask;
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (s.hasNext()) {
            s.nextLine();
        }
        while (s.hasNext()) {
            tempTask = decodeTaskFromStrings(s.nextLine());
            list.add(tempTask);
        }
        return new TaskList(list);
    }

    /**
     * Writes the contents of the input <code>textToAdd</code> into the
     * file specified by <code>filePath</code>.
     *
     * @throws IOException if an I/O error has occurred.
     */
    public static void writeToFile(ArrayList<String> textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (String str : textToAdd) {
            fw.write(str + '\n');
        }
        fw.close();
    }

    /**
     * Writes the contents of the input <code>taskList</code> into the
     * file specified by <code>filePath</code> after encoding the
     * <code>Task</code> into <code>Strings</code>.
     *
     * @throws IOException if an I/O error has occurred.
     */
    public static void saveToFile(TaskList taskList) throws IOException {
        ArrayList<String> linesToWrite;
        linesToWrite = Storage.encodeTasksToString(taskList.getTasksList());
        Storage.writeToFile(linesToWrite);
    }

}
