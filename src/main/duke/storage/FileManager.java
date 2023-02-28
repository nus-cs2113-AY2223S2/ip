package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.DateData;
import duke.tasklist.FindData;
import duke.tasklist.TaskData;
import duke.util.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains all the methods to read and write to the datafile stored locally for saving the list of task
 * objects such that the task objects are carried between each instance of running the application.
 */
public class FileManager {

    private static final ArrayList<String> storedData = new ArrayList<>();

    /**
     * Reads the datafile stored locally if it exists.
     *
     * @param path The path to the datafile.
     * @throws FileNotFoundException Throws an exception if the datafile does not exist in the <code>path</code>
     * specified.
     */
    public static void readFile(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            storedData.add(s.nextLine());
        }
        s.close();
    }

    /**
     * Converts the task objects passed through <code>tasks</code> into a readable format and stores them into the
     * datafile at the <code>path</code> specified.
     * This completely overwrites all contents in the datafile previously stored.
     *
     * @param path The path to the datafile.
     * @param tasks The <code>Hashmap</code> containing all the task objects to write to the datafile.
     * @throws IOException Throws an exception if this function fails to write to the datafile.
     */
    public static void writeFile(String path, HashMap<Integer, Task> tasks) throws IOException {
        //Overwrites previous text in file
        FileWriter fw = new FileWriter(path, false);
        for (Task i: tasks.values()) {
            String t = i.checkType();
            char done = i.checkDone().charAt(1);
            String w = t.charAt(1) + " % " + done + " % " + i.getTask() + "\n";
            fw.write(w);
        }
        fw.close();
    }

    /**
     * Converts a single task object into a readable format and stores it into the datafile at the <code>path</code>
     * specified.
     * This appends to the end of the datafile.
     *
     * @param path The path to the datafile.
     * @param task The single task object to write to the datafile.
     * @throws IOException Throws an exception if this function fails to write to the datafile.
     */
    public static void writeTask(String path, Task task) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        String t = task.checkType();
        char done = task.checkDone().charAt(1);
        String w = t.charAt(1) + " % " + done + " % " + task.getTask() + "\n";
        fw.write(w);
        fw.close();
    }

    /**
     * Creates an empty datafile at the <code>path</code> specified.
     *
     * @param path The path to create the datafile.
     */
    public static void createFile(String path) {
        try {
            Path dir = Paths.get("data");
            Files.createDirectories(dir);
            File newFile = new File(path);
            if (newFile.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Decodes the datafile such and converts them into an application readable format to pass to the <code>tasks</code>
     * object, <code>dates</code> object, <code>find</code> object such that the previously stored task objects are
     * stored in the current instance of the application.
     *
     * @param tasks The object containing all the task objects in the application.
     * @param dates The object with the functionality related to <code>LocalDate</code> and <code>date</code>.
     * @param find The object with the functionality related to <code>find</code>.
     * @throws DukeException Throws an exception if the datafile finds an invalid format in the datafile.
     */
    public static void handleFile(TaskData tasks, DateData dates, FindData find) throws DukeException {
        for (String str: storedData) {
            String[] temp = str.split("%",5);
            switch (temp[0].trim()) {
            case ("T"):
                ToDo todo = tasks.handleTodo(temp[2].trim(), false);
                if (temp[1].trim().equals("X")) {
                    tasks.handleMark("task", temp[2].trim(), false);
                }
                find.addTask(todo, tasks.getTaskCount());
                break;
            case ("D"):
                try {
                    Deadline deadline = tasks.handleDeadline(temp[2].trim()
                            + "/by" + temp[3], false);
                    if (temp[1].trim().equals("X")) {
                        tasks.handleMark("task", temp[2].trim(), false);
                    }
                    dates.addDeadline(deadline, tasks.getTaskCount());
                    find.addTask(deadline, tasks.getTaskCount());
                } catch (DukeException e) {
                    throw new DukeException();
                }
                break;
            case ("E"):
                try {
                    Event event = tasks.handleEvent(temp[2].trim() + "/from"
                            + temp[3] + "/to" + temp[4], false);
                    if (temp[1].trim().equals("X")) {
                        tasks.handleMark("task", temp[2].trim(), false);
                    }
                    dates.addEvent(event, tasks.getTaskCount());
                    find.addTask(event, tasks.getTaskCount());
                } catch (DukeException e) {
                    throw new DukeException();
                }
                break;
            default:
                throw new DukeException();
            }
        }
    }
}
