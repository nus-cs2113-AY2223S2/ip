package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Errors;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * A class for saving the task list as a text file, and subsequently loading it.
 */
public abstract class Storage {
    public static final String DELIMITER = "\u001D";
    public static final String SAVE_PATH = "save.txt";

    /**
     * Checks if the save string (which represents a task) is valid,
     * which means it has the expected number of arguments,
     * and the completion status is either a "1" or a "0".
     *
     * @param splitTasks The save string split by the delimiter character.
     * @param expectedArgs The expected number of arguments, which differs depending on the type of task.
     * @return True if the save string is invalid, false otherwise.
     */
    private static boolean isInvalidSaveString(String[] splitTasks, int expectedArgs) {
        return splitTasks.length != expectedArgs || !(splitTasks[1].equals("1") || splitTasks[1].equals("0"));
    }

    /**
     * Converts the save string read from the save file to a Task object.
     *
     * @param taskString The save string read from the file.
     * @return The task object represented by the string.
     * @throws DukeException If the save string is invalid.
     */
    private static Task convertStringToTask(String taskString) throws DukeException {
        String[] splitTasks = taskString.split(DELIMITER);
        Task task;
        switch (splitTasks[0]) {
        case "T":
            if (isInvalidSaveString(splitTasks, 3)) {
                throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
            }
            task = new ToDo(splitTasks[2]);
            task.setDone(splitTasks[1].equals("1"));
            return task;
        case "D":
            if (isInvalidSaveString(splitTasks, 4)) {
                throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
            }
            task = new Deadline(splitTasks[2], splitTasks[3]);
            task.setDone(splitTasks[1].equals("1"));
            return task;
        case "E":
            if (isInvalidSaveString(splitTasks, 5)) {
                throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
            }
            task = new Event(splitTasks[2], splitTasks[3], splitTasks[4]);
            task.setDone(splitTasks[1].equals("1"));
            return task;
        default:
            throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
        }
    }

    /**
     * Reads in the task list from the save file.
     *
     * @param filePath The path of the save file.
     * @return The task list that was saved in the file.
     */
    public static TaskList readTasksFromFile(String filePath) {
        try (Scanner in = new Scanner(new File(filePath))) {
            TaskList taskList = new TaskList();

            while (in.hasNextLine()) {
                String taskString = in.nextLine();
                taskList.addTask(convertStringToTask(taskString));
            }

            return taskList;
        } catch (java.io.FileNotFoundException e) {
            return new TaskList();
        } catch (DukeException e) {
            Ui.printError(e.getMessage(), "save");
            return new TaskList();
        }
    }

    /**
     * Writes the current task list to the save file.
     *
     * @param filePath The path of the save file.
     * @param taskList The task list being saved.
     */
    public static void saveTasksToFile(String filePath, TaskList taskList) {
        try (FileWriter out = new FileWriter(filePath)) {
            out.write(taskList.toSaveString());
        } catch (java.io.IOException e) {
            Ui.printError(Errors.FAILED_SAVE.MESSAGE, "save");
        }
    }
}
