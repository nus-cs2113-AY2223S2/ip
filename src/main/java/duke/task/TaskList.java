package duke.task;

import duke.ui.UI;
import duke.exception.DukeException;
import duke.exception.TaskNumberException;

import java.util.ArrayList;

/**
 * Class that stores tasks and handles operations performed on tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list
     *
     * @param taskParameters The parameters for the task
     * @param taskType The type of the task
     */
    public void addNewTask(String[] taskParameters, TaskType taskType) {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new ToDo(taskParameters[0]);
            break;
        case DEADLINE:
            newTask = new Deadline(taskParameters[0], taskParameters[1]);
            break;
        case EVENT:
            newTask = new Event(taskParameters[0], taskParameters[1], taskParameters[2]);
            break;
        default:
            // No task type specified, there is some bug in the code if instruction reaches this point,
            // simply do not add anything to the tasks
            UI.printToUser("Error in addNewTask method, no task type specified");
            return;
        }
        tasks.add(newTask);
        // Print a singular form of task if there is only one task in the list
        String taskString = "tasks";
        if(tasks.size() == 1){
            taskString = "task";
        }
        UI.printToUser("Got it. I've added this task:\n" +
                "  " + newTask + "\n" +
                "Now you have " + tasks.size() + " " + taskString + " in the list.");
    }

    /**
     * Gets the task index from the string of task numbers received from parser
     *
     * @param taskNumbers String array received from parser representing task numbers
     * @param totalTasks Total tasks in the list
     * @return The 0-based index of the task indicated by user
     * @throws DukeException if the task number is invalid
     */
    private int getTaskIndex(String[] taskNumbers, int totalTasks) throws DukeException{
        // From parser, in the current implementation, taskNumbers.length is always equal to 1
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumbers[0]);
        } catch (Exception e){
            throw new TaskNumberException();
        }
        if(taskIndex <= 0 || taskIndex > totalTasks){
            throw new TaskNumberException();
        }

        // Minus one from task index since the array tasks is 0-based
        taskIndex -= 1;
        return taskIndex;
    }

    /**
     * Changes the task status of a task
     *
     * @param taskNumbers String array representing the 1-based task number to change the status of
     * @param isDone Boolean representing what to set the status of the task to be
     * @throws DukeException if task number is invalid
     */
    public void changeTaskStatus(String[] taskNumbers, boolean isDone) throws DukeException{
        // From parser, taskNumbers.length is always equal to 1
        int taskIndex = getTaskIndex(taskNumbers, tasks.size());

        if(isDone){
            if(tasks.get(taskIndex).isDone()){
                UI.printToUser("This task is already marked done:\n"
                        + tasks.get(taskIndex).toString());
            } else{
                tasks.get(taskIndex).setDone(true);
                UI.printToUser("Nice! I've marked this task as done:\n"
                        + tasks.get(taskIndex).toString());
            }
        }else{
            if(!tasks.get(taskIndex).isDone()){
                UI.printToUser("This task is already marked as not done:\n"
                        + tasks.get(taskIndex).toString());
            } else{
                tasks.get(taskIndex).setDone(false);
                UI.printToUser("OK, I've marked this task as not done yet:\n"
                        + tasks.get(taskIndex).toString());
            }
        }
    }

    /**
     * Deletes a task
     *
     * @param taskNumbers String array representing the 1-based task number to delete
     * @throws DukeException if task number is invalid
     */
    public void deleteTask(String[] taskNumbers) throws DukeException{
        int taskIndex = getTaskIndex(taskNumbers, tasks.size());

        Task taskToRemove = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        // Print a singular form of task if there is only one task in the list
        String taskString = "tasks";
        if(tasks.size() == 1){
            taskString = "task";
        }
        String successMessage = "Noted. I've removed this task:\n" +
                "  " + taskToRemove + "\n" +
                "Now you have " + tasks.size() + " " + taskString + " in the list." ;
        UI.printToUser(successMessage);
    }

    /**
     * Searches through the list of tasks and returns an array list of tasks containing the keyword passed in
     *
     * @param keyWord The keyword to search for
     * @return An array list of tasks with the keyword in their name
     */
    public ArrayList<Task> tasksWithKeyword(String keyWord){
        ArrayList<Task> selectedTasks = new ArrayList<>();
        for(Task task : tasks) {
            if (task.getTaskName().contains(keyWord)) {
                selectedTasks.add(task);
            }
        }
        return selectedTasks;
    }

    public ArrayList<Task> getTaskArrayList() {
        return tasks;
    }
}
