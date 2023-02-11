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

public abstract class Storage {
    public static String DELIMITER = "\u001D";
    public static String SAVE_PATH = "save.txt";

    private static String convertTasksToSaveString(TaskList taskList) {
        StringJoiner taskListString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < taskList.size(); i++) {
            taskListString.add(taskList.getTask(i).toSaveString());
        }
        return taskListString.toString();
    }

    private static boolean isValidSaveString(String[] splitTasks, int expectedArgs) {
        return splitTasks.length == expectedArgs && (splitTasks[1].equals("1") || splitTasks[1].equals("0"));
    }

    private static Task convertStringToTask(String taskString) throws DukeException {
        String[] splitTasks = taskString.split(DELIMITER);
        Task task;
        switch (splitTasks[0]) {
        case "T":
            if (!isValidSaveString(splitTasks, 3)) {
                throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
            }
            task = new ToDo(splitTasks[2]);
            task.setDone(splitTasks[1].equals("1"));
            return task;
        case "D":
            if (!isValidSaveString(splitTasks, 4)) {
                throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
            }
            task = new Deadline(splitTasks[2], splitTasks[3]);
            task.setDone(splitTasks[1].equals("1"));
            return task;
        case "E":
            if (!isValidSaveString(splitTasks, 5)) {
                throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
            }
            task = new Event(splitTasks[2], splitTasks[3], splitTasks[4]);
            task.setDone(splitTasks[1].equals("1"));
            return task;
        default:
            throw new DukeException(Errors.INVALID_SAVE.MESSAGE);
        }
    }

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

    public static void saveTasksToFile(String filePath, TaskList taskList) {
        try (FileWriter out = new FileWriter(filePath)) {
            out.write(convertTasksToSaveString(taskList));
        } catch (java.io.IOException e) {
            Ui.printError(Errors.FAILED_SAVE.MESSAGE, "save");
        }
    }
}
