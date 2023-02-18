package commandHandler;

import java.io.FileWriter;
import java.io.IOException;

import data.ProcessStorageTasks;
import data.tasksList;
import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import ui.Display;
import ui.exceptions.MissingCommandException;

/**
 * Represents the add feature in the Duke program. Users may specify their type
 * of task to add, followed by the required arguments as specified in the
 * <code>/help</code> menu e.g.,
 * <code>/deadline do homework /by sunday morning</code>/
 */
public class Add {
    private static final String COMMAND_EVENT = "/event";
    private static final String COMMAND_TODO = "/todo";
    private static final String COMMAND_DEADLINE = "/deadline";
    private static final String COMMAND_BY = "/by";
    private static final String COMMAND_START = "/start";
    private static final String COMMAND_END = "/end";
    private static final String COMMAND_STORAGE_EVENT = "Event";
    private static final String COMMAND_STORAGE_TODO = "Todo";
    private static final String COMMAND_STORAGE_DEADLINE = "Deadline";

    /**
     * Adds a task specified by user into the Duke program task list.
     * 
     * @param command   Type of task.
     * @param arguments Required argmuments.
     * @throws MissingCommandException If user input is missing required arguments
     *                                 such as description or cutoff dates/times.
     */
    public static void addTask(String command, String arguments) throws MissingCommandException {
        Task newTask;
        /** Handle different task types **/
        switch (command) {
            case COMMAND_TODO:
                newTask = new Todo(arguments);
                break;
            case COMMAND_DEADLINE:
                if (!arguments.contains(COMMAND_BY)) {
                    throw new MissingCommandException("Please specify a deadline via the /by command!");
                }
                newTask = new Deadline(arguments);
                break;
            case COMMAND_EVENT:
                if (!arguments.contains(COMMAND_START) || !arguments.contains(COMMAND_END)) {
                    throw new MissingCommandException(
                            "Please specify both start and end dates/times via the /start and /end commands!");
                }
                newTask = new Event(arguments);
                break;
            default:
                return;
        }
        tasksList.addTask(newTask);
        addTaskToStorage(newTask, tasksList.userTaskCount);
        tasksList.userTaskCount++;
    }

    /**
     * Stores newly added task into savedTasks.txt file.
     * 
     * @param newTask   Task added by user.
     * @param taskCount Current saved tasks count.
     */
    public static void addTaskToStorage(Task newTask, int taskCount) {
        try {
            FileWriter fw = new FileWriter(ProcessStorageTasks.FILE_PATH, true);
            fw.write(Integer.toString(taskCount) + ":" + newTask.formattedString() + "\n");
            fw.close();
        } catch (IOException e) {
            Display.warnUser(e.getMessage());
        }
    }

    /*
     * taskStringArray[0] -> index
     * taskStringArray[1] -> command
     * taskStringArray[2] -> isDone
     * taskStringArray[3] -> task description
     * 
     * For event:
     * taskStringArray[4] -> start
     * taskStringArray[5] -> end
     * 
     * For deadline:
     * taskStringArray[4] -> cutoff
     */

    /**
     * Loads locally stored tasks into Duke program task list.
     * 
     * @param arguments Task details stored in storage text file.
     */

    public static void addSavedTask(String arguments) {
        Task newTask;
        String[] taskStringArray = arguments.split(":");
        String command = taskStringArray[1];
        if (command.equals(COMMAND_STORAGE_TODO)) {
            String inputFormat = taskStringArray[3];
            newTask = new Todo(inputFormat);
        } else if (command.equals(COMMAND_STORAGE_DEADLINE)) {
            String inputFormat = taskStringArray[3] + " /by " +
                    taskStringArray[4];
            newTask = new Deadline(inputFormat);
        } else if (command.equals(COMMAND_STORAGE_EVENT)) {
            String inputFormat = taskStringArray[3] + " /start " +
                    taskStringArray[4] + " /end "
                    + taskStringArray[5];
            newTask = new Event(inputFormat);
        } else {
            return;
        }
        if (taskStringArray[2].equals(String.valueOf(true))) {
            newTask.markAsDone();
        }
        tasksList.addTask(newTask);
        tasksList.userTaskCount++;
    }

}
