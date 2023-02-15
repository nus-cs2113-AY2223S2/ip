package commandHandler;

import java.io.FileWriter;
import java.io.IOException;

import data.tasksList;
import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import ui.Display;
import ui.exceptions.MissingCommandException;

public class Add {
    private static final String COMMAND_EVENT = "/event";
    private static final String COMMAND_TODO = "/todo";
    private static final String COMMAND_DEADLINE = "/deadline";
    private static final String FILE_PATH = "savedTasks.txt";

    public static void addTask(String userInput) throws MissingCommandException {
        String[] userInputArray = userInput.split(" ");
        String command = userInputArray[0];
        Task newTask;
        /** Check if task description is left empty **/
        if (userInputArray.length == 1) {
            throw new MissingCommandException("Please enter a description for your task!");
        }
        /** Handle different task types **/
        if (command.equals(COMMAND_TODO)) {
            newTask = new Todo(userInput);
        } else if (command.equals(COMMAND_DEADLINE)) {
            if (!userInput.contains("/by")) {
                throw new MissingCommandException("Please specify a deadline via the /by command!");
            }
            newTask = new Deadline(userInput);
        } else if (command.equals(COMMAND_EVENT)) {
            if (!userInput.contains("/start") || !userInput.contains("/end")) {
                throw new MissingCommandException(
                        "Please specify both start and end dates/times via the /start and /end commands!");
            }
            newTask = new Event(userInput);
        } else {
            return;
        }
        tasksList.addTask(newTask);
        addTaskToStorage(newTask, tasksList.userTaskCount);
        tasksList.userTaskCount++;
    }

    public static void addTaskToStorage(Task newTask, int taskCount) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(Integer.toString(taskCount) + ":" + newTask.formattedString() + "\n");
            fw.close();
        } catch (IOException e) {
            Display.warnUser(e.getMessage());
        }
    }

    /*
     * arr[0] -> index
     * arr[1] -> task type
     * arr[2] -> isDone
     * arr[3] -> task description
     * 
     * For event:
     * arr[4] -> start
     * arr[5] -> end
     * 
     * For deadline:
     * arr[4] -> cutoff
     */

    public static void addSavedTask(String arguments) {
        Task newTask;
        String[] taskStringArray = arguments.split(":");
        String command = taskStringArray[0];
        if (command.equals(COMMAND_TODO)) {
            String inputFormat = "/todo " + taskStringArray[3];
            newTask = new Todo(inputFormat);
        } else if (taskStringArray[1].equals("Deadline")) {
            String inputFormat = "/deadline " + taskStringArray[3] + " /by " + taskStringArray[4];
            newTask = new Deadline(inputFormat);
        } else {
            String inputFormat = "/event " + taskStringArray[3] + " /start " + taskStringArray[4] + " /end "
                    + taskStringArray[5];
            newTask = new Event(inputFormat);
        }
        if (taskStringArray[2].equals("true")) {
            newTask.markAsDone();
        }
        tasksList.addTask(newTask);
        tasksList.userTaskCount++;
    }

}
