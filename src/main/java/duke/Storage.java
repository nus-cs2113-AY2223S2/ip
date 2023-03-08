package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;

/**
 * A class for saving the task list as a text file and loading it.
 */
public class Storage {
    private static final int TASK_INFORMATION_BEGIN_INDEX = 7;
    private static final int TASK_TYPE_END_INDEX = 6;
    private static final int TASK_TYPE_INDICATOR_INDEX = 1;
    private static final int TASK_TYPE_STATUS_INDEX = 4;
    private static final String LOADING_FRONT_PARENTHESIS_DELIMITER = " \\(";
    private static final String LOADING_EVENT_FROM_DELIMITER = "from: ";
    private static final String LOADING_EVENT_TO_DELIMITER = " to: ";

    /**
     * Reads the data from the local save file and fills up the task list based on that data.
     *
     * @param filepath The path of the local save file.
     * @param tasks    The empty task list which will be filled up based on the data in the local save file.
     */
    public static void loadData(String filepath, ArrayList<Task> tasks) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskInStringFormat = s.nextLine();
                String taskType = taskInStringFormat.substring(0, TASK_TYPE_END_INDEX);
                String taskInformation = taskInStringFormat.substring(TASK_INFORMATION_BEGIN_INDEX);
                switch (taskType.charAt(TASK_TYPE_INDICATOR_INDEX)) {
                case 'T':
                    loadToDoTask(tasks, taskInformation, taskType);
                    break;
                case 'D':
                    loadDeadlineTask(tasks, taskInformation, taskType);
                    break;
                case 'E':
                    loadEventTask(tasks, taskInformation, taskType);
                    break;
                default:
                    Ui.printErrorMessage("conversion");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            Ui.printErrorMessage("file not found");
        }
    }

    /**
     * Adds a new event task into the task list, based on data in the local save file.
     *
     * @param tasks           The task list which the new event task will be added to.
     * @param taskInformation The string containing raw information about the new event task.
     * @param taskType        The string containing the task type and completion status of the new event task to be
     *                        added to the task list.
     */
    public static void loadEventTask(ArrayList<Task> tasks, String taskInformation, String taskType) {
        String taskName;
        taskName = taskInformation.split(LOADING_FRONT_PARENTHESIS_DELIMITER, 2)[0];
        taskInformation = taskInformation.split(LOADING_EVENT_FROM_DELIMITER, 2)[1];
        String eventStart = taskInformation.split(LOADING_EVENT_TO_DELIMITER, 2)[0];
        String eventEnd = taskInformation.split(LOADING_EVENT_TO_DELIMITER, 2)[1].replace(")", "");
        TaskList.addTask(new Event(taskName, eventStart, eventEnd));
        if (taskType.charAt(TASK_TYPE_STATUS_INDEX) == 'X') {
            tasks.get(tasks.size() - 1).setDone(true);
        }

    }

    /**
     * Adds a new deadline task into the task list, based on data in the local save file.
     *
     * @param tasks           The task list which the new deadline task will be added to.
     * @param taskInformation The string containing raw information about the new deadline task.
     * @param taskType        The string containing the task type and completion status of the new deadline task to be
     *                        added to the task list.
     */
    public static void loadDeadlineTask(ArrayList<Task> tasks, String taskInformation, String taskType) {
        String taskName;
        taskName = taskInformation.split(LOADING_FRONT_PARENTHESIS_DELIMITER, 2)[0];
        String deadlineBy = taskInformation.split(": ", 2)[1].replace(")", "");
        deadlineBy = Parser.parseSavedDeadline(deadlineBy);
        TaskList.addTask(new Deadline(taskName, deadlineBy));
        if (taskType.charAt(TASK_TYPE_STATUS_INDEX) == 'X') {
            tasks.get(tasks.size() - 1).setDone(true);
        }
    }

    /**
     * Adds a new todo task into the task list, based on data in the local save file.
     *
     * @param tasks           The task list which the new todo task will be added to.
     * @param taskInformation The string containing raw information about the new todo task.
     * @param taskType        The string containing the task type and completion status of the new todo task to be
     *                        added to the task list.
     */
    public static void loadToDoTask(ArrayList<Task> tasks, String taskInformation, String taskType) {
        String taskName;
        taskName = taskInformation; // for TODO task, taskInformation is the task name
        TaskList.addTask(new ToDo(taskName));
        if (taskType.charAt(TASK_TYPE_STATUS_INDEX) == 'X') {
            tasks.get(tasks.size() - 1).setDone(true);
        }
    }

    /**
     * Writes the current task list to the local save file.
     *
     * @param filepath The path of the local save file.
     * @param tasks The task list being saved.
     */
    public static void saveData(String filepath, ArrayList<Task> tasks) {
        try {
            String tasksDataInStringFormat = convertTasksDataToString(tasks);
            Storage.writeToFile(filepath, tasksDataInStringFormat);
        } catch (IOException e) {
            Ui.printErrorMessage("saving");
        } catch (DukeException e) {
            Ui.printErrorMessage("conversion");
        }
    }

    /**
     * Converts the current task list into string format so that it can be saved as a text file.
     *
     * @param tasks The task list to be saved.
     * @return A string containing information based on the task list to be saved.
     * @throws DukeException If there is illegal memory access while converting the task list into string format.
     */
    public static String convertTasksDataToString(ArrayList<Task> tasks) throws DukeException {
        StringJoiner joiner = new StringJoiner("\n");
        try {
            for (Task task : tasks) {
                String taskInformation = task.toString();
                joiner.add(taskInformation);
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        return joiner.toString();
    }

    // code provided by module website
    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }
}
