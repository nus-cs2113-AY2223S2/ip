package task;

import java.util.ArrayList;

import io.DukeException;
import io.Storage;

import io.Ui;

public class TaskList {
    private ArrayList<Task> tasks;
    private int numberOfTasks;

    /**
     * Constructor to use with saved file. Populates task list.
     * @param listOfTasks Tasks from save file (handled by {@link io.Storage}).
     */
    public TaskList(ArrayList<String[]> listOfTasks) throws DukeException {
        tasks = new ArrayList<Task>();
        for (String[] strings : listOfTasks) {
            try {
                tasks.add(createTaskFromFile(strings));
            } catch (DukeException e) {
                System.out.println("Error trying to add a task from save file.");
                throw new DukeException();
            }
        }

        numberOfTasks = tasks.size();
    }
    /**
     * Constructor for BRAND NEW TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
        numberOfTasks = 0;
    }

    /**
     * Print the contents of Task List
     */
    public String getTaskListString() {
        String output = "Your Tasks: \n";
        for (Task task : tasks) {
            output += task.getTaskNumber() + task.toString() + '\n';
        }
        return output;
    }

    /**
     * Adds a Task to the list of Tasks.
     * @param task {@link Task} object.
     */
    public void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }

    /**
     * Reads a line of strings from save file and returns a Task.
     * @param input Strings describing the task.
     * @return The Task, marked as done or not.
     * @throws DukeException When there is an error in the file describing the task.
     */
    private Task createTaskFromFile(String[] input) throws DukeException {
        Task newTask;
        switch(input[0]) {
        case "T":
            newTask = new Todo(input[2], getNextTaskNumber());
            break;
        case "D":
            newTask = new Deadline(input[2], getNextTaskNumber(), input[3]);
            break;
        case "E":
            newTask = new Event(input[2], getNextTaskNumber(), input[3], input[4]);
            break;
        default:
            // When there is error in reading the file.
            throw new DukeException();
        }

        // Set it as done.
        if (input[1].equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }

    /**
     * Deletes a task from the task list and decrement number of tasks.
     * @param taskNumber 1-indexed task index.
     */
    public Task deleteTask(int taskNumber) {
        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        numberOfTasks--;
        return deletedTask;
    }

    /**
     * Two-in-one method to mark or unmark task since it is so similar
     * @param command Either "mark" or "unmark"
     * @param commandArgs Should be an int corresponding to the task number (1-index)
     * @return Feedback string: "Task marked: ____"
     */
    public String executeMarkUnmark(String command, String commandArgs) {
        int taskNumber;
        // Check is Integer
        try {
            taskNumber = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            // Not an int. Send back an error message.
            return Ui.ERROR_MESSAGE_TASK_INDEX;
        }
        // Check Integer is in range
        try {
            if (command.equals("mark")) {
                return tasks.get(taskNumber - 1).markAsDone();
            } else if (command.equals("unmark")) {
                return tasks.get(taskNumber - 1).markAsUndone();
            }
        } catch (IndexOutOfBoundsException e) {
            return Ui.ERROR_MESSAGE_TASK_INDEX;
        }
        // If nothing else works...
        return "Huh?";
    }

    // Getter for number of task
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    // A bit unoptimised, but this is to get the next number for numbering purposes.
    public int getNextTaskNumber() {
        return numberOfTasks + 1;
    }

    public void writeAllToFile(Storage storage) {
        String output = "";
        for (Task task : tasks) {
            if (task != null) {
                output += task.getFileWriteFormat() + '\n';
            }
        }
        storage.writeToFile(output);
    }
}
