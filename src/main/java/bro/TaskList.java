package bro;

import bro.exceptions.invalidInputFormat;
import bro.exceptions.invalidTaskIndexException;
import bro.tasks.Task;
import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.ToDo;
import bro.tasks.Type;
import static bro.Ui.printReply;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private final ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public void add(Task task) {
        taskList.add(task);
    }

    public void createToDo(TaskList taskListObject, String[] arrayOfInputs) throws invalidInputFormat {
        ArrayList<Task> taskList = taskListObject.getTaskList();
        StringBuilder todoName = new StringBuilder();
        if (arrayOfInputs.length < 2) {
            throw new invalidInputFormat(Type.TODO);
        }
        for (int i = 1; i < arrayOfInputs.length; ++i) {
            todoName.append(" ").append(arrayOfInputs[i]);
        }
        Task todo = new ToDo(todoName.toString().trim());
        taskList.add(todo);
        printReply(" added: " + todo);
    }

    public void createDeadline(TaskList taskListObject, String[] arrayOfInputs) throws invalidInputFormat {
        ArrayList<Task> taskList = taskListObject.getTaskList();
        int indexOfDeadline = Arrays.asList(arrayOfInputs).indexOf("/by");
        if (indexOfDeadline == -1 || indexOfDeadline == arrayOfInputs.length - 1) { // user did not input "/by" or did not input a deadline time
            throw new invalidInputFormat(Type.DEADLINE);
        }
        // Separate arrayOfInputs into `deadlineName` and `by` to construct deadline object
        StringBuilder deadlineName = new StringBuilder();
        StringBuilder by = new StringBuilder();
        for (int i = 1; i < indexOfDeadline; ++i) {
            deadlineName.append(" ").append(arrayOfInputs[i]);
        }
        for (int i = indexOfDeadline + 1; i < arrayOfInputs.length; ++i) {
            by.append(" ").append(arrayOfInputs[i]);
        }
        Task deadline = new Deadline(deadlineName.toString().trim(), by.toString().trim());
        taskList.add(deadline);
        printReply(" added: " + deadline);
    }

    public void createEvent(TaskList taskListObject, String[] arrayOfInputs) throws invalidInputFormat {
        ArrayList<Task> taskList = taskListObject.getTaskList();
        int indexOfStartTime = Arrays.asList(arrayOfInputs).indexOf("/from");
        int indexOfEndTime = Arrays.asList(arrayOfInputs).indexOf("/to");
        if (indexOfStartTime == -1 || indexOfEndTime == -1 || indexOfEndTime == arrayOfInputs.length - 1) { // user did not input "/from" or "/to" or did not input an end time
            throw new invalidInputFormat(Type.DEADLINE);
        }
        StringBuilder eventName = new StringBuilder();
        StringBuilder startTime = new StringBuilder();
        StringBuilder endTime = new StringBuilder();
        for (int i = 1; i < indexOfStartTime; ++i) {
            eventName.append(" ").append(arrayOfInputs[i]);
        }
        for (int i = indexOfStartTime + 1; i < indexOfEndTime; ++i) {
            startTime.append(" ").append(arrayOfInputs[i]);
        }
        for (int i = indexOfEndTime + 1; i < arrayOfInputs.length; ++i) {
            endTime.append(" ").append(arrayOfInputs[i]);
        }
        Task event = new Event(eventName.toString().trim(), startTime.toString().trim(), endTime.toString().trim());
        taskList.add(event);
        printReply(" added: " + event);
    }

    private int checkAndGetValidTaskIndex(Type queryType, int sizeOfTaskList, String[] arrayOfInputs) throws invalidInputFormat, invalidTaskIndexException {
        int taskIndex;
        // Validate if input format is valid
        try {
            taskIndex = Integer.parseInt(arrayOfInputs[1]) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new invalidInputFormat(queryType);
        }
        // Validate if input Task index is valid
        if (taskIndex + 1 > sizeOfTaskList || taskIndex < 0) {
            throw new invalidTaskIndexException();
        }
        return taskIndex;
    }

    public void markComplete(boolean markAsComplete, ArrayList<Task> taskList, String[] arrayOfInputs) throws invalidInputFormat, invalidTaskIndexException {
        int taskIndex = checkAndGetValidTaskIndex(Type.MARK, taskList.size(), arrayOfInputs);
        if (markAsComplete){   // mark as Completed
            taskList.get(taskIndex).setCompleted();
            printReply(" Marked " + taskList.get(taskIndex) + " as done.");
        }
        else {                      // mark as Uncompleted
            taskList.get(taskIndex).setUncompleted();
            printReply(" Marked " + taskList.get(taskIndex) + " as not done.");
        }
    }

    public void deleteTask(TaskList taskListObject, String[] arrayOfInputs) throws invalidInputFormat, invalidTaskIndexException {
        ArrayList<Task> taskList = taskListObject.getTaskList();
        int taskIndex = checkAndGetValidTaskIndex(Type.DELETE, taskList.size(), arrayOfInputs);
        Task currentTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        printReply(" Ok bro I remove this task:\n" + "   [" + currentTask.getType() + "][" + currentTask.mark() + "] " + currentTask +
                "\n Now you have " + taskList.size() + " tasks in the list.");
    }
}
