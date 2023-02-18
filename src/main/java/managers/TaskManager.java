package managers;

import enums.DialogueTypes;
import errors.EmptyTaskListException;
import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import errors.TaskNumberOutOfRangeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasksList;
    public static final int TODO_BEGIN_INDEX = 5;
    public static final int DEADLINE_BEGIN_INDEX = 9;
    public static final int EVENT_BEGIN_INDEX = 6;

    public TaskManager(ArrayList<Task> taskList) {
        tasksList = taskList;
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public Task addNewEventTask(String line) throws InvalidEventException {
        Task newTask = new Event(line.substring(EVENT_BEGIN_INDEX));
        tasksList.add(newTask);
        return newTask;
    }

    public Task addNewDeadlineTask(String line) throws InvalidDeadlineException {
        Task newTask = new Deadline(line.substring(DEADLINE_BEGIN_INDEX));
        tasksList.add(newTask);
        return newTask;
    }

    public Task addNewTodoTask(String line) {
        Task newTask = new ToDo(line.substring(TODO_BEGIN_INDEX));
        tasksList.add(newTask);
        return newTask;
    }

    public Task changeStateOfTask(int indexToMark, boolean isDone)
            throws TaskNumberOutOfRangeException, NumberFormatException {
        if (indexToMark >= Task.getItemCount() || indexToMark < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        tasksList.get(indexToMark).markAsState(isDone);
        return tasksList.get(indexToMark);
    }
    public void listAllItems(OutputDialogueManager display) {
        try {
            checkStateOfTasksList();
            display.printInteraction(DialogueTypes.LIST_TASKS);
            printAllTasksInList();
        } catch (EmptyTaskListException e) {
            display.printErrorDialogue(DialogueTypes.NO_TASK_IN_LIST);
        }
    }

    private static void checkStateOfTasksList() throws EmptyTaskListException {
        if (Task.getItemCount() < 1) {
            throw new EmptyTaskListException();
        }
    }

    private void printAllTasksInList() {
        int taskCount = 1;
        for (Task item: tasksList) {
            System.out.print(taskCount);
            item.printTask();
            taskCount++;
        }
    }

    public Task deleteItem(int indexToDelete) throws TaskNumberOutOfRangeException {
//        int indexToDelete = Integer.parseInt(command) - 1;
        if (indexToDelete >= Task.getItemCount() || indexToDelete < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        Task toBeDeleted = tasksList.get(indexToDelete);
        tasksList.remove(indexToDelete);
        Task.decreaseItemCount();
        return toBeDeleted;
    }
}
