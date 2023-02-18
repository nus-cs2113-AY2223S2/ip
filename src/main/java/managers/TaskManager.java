package managers;

import enums.DialogueTypes;
import enums.ErrorDialogueTypes;
import errors.EmptyTaskListException;
import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import errors.NoMatchingItemException;
import errors.TaskNumberOutOfRangeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class TaskManager {
    private ArrayList<Task> tasksList;
    private ArrayList<Task> foundList;
    public static final int TODO_BEGIN_INDEX = 5;
    public static final int DEADLINE_BEGIN_INDEX = 9;
    public static final int EVENT_BEGIN_INDEX = 6;

    public TaskManager(ArrayList<Task> taskList) {
        tasksList = taskList;
        foundList = new ArrayList<>();
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
            printAllTasksInList(tasksList);
        } catch (EmptyTaskListException e) {
            display.printErrorDialogue(ErrorDialogueTypes.NO_TASK_IN_LIST);
        }
    }

    private static void checkStateOfTasksList() throws EmptyTaskListException {
        if (Task.getItemCount() < 1) {
            throw new EmptyTaskListException();
        }
    }

    private void printAllTasksInList( ArrayList<Task> listOfTasks) {
        int taskCount = 1;
        for (Task item: listOfTasks) {
            System.out.print(taskCount);
            item.printTask();
            taskCount++;
        }
    }

    public Task deleteItem(int indexToDelete) throws TaskNumberOutOfRangeException {
        if (indexToDelete >= Task.getItemCount() || indexToDelete < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        Task toBeDeleted = tasksList.get(indexToDelete);
        tasksList.remove(indexToDelete);
        Task.decreaseItemCount();
        return toBeDeleted;
    }

    public void findTaskWithWord (String toFind, OutputDialogueManager display)
            throws NoMatchingItemException {
        foundList.clear();
        foundList = (ArrayList<Task>) tasksList.stream()
                .filter(t -> t.getItemName().toLowerCase().contains(toFind))
                .collect(toList());
        if (foundList.isEmpty()) {
            throw new NoMatchingItemException();
        }
        display.printInteraction(DialogueTypes.FIND_TASK);
        printAllTasksInList(foundList);
    }
}
