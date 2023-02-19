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

/**
 * Represents a controller of how the contents of the <code>ArrayList<Task></code> is manipulated
 * and changed, with functions specific to each change.
 */
public class TaskManager {
    private ArrayList<Task> tasksList;
    private ArrayList<Task> foundList;
    public static final int TODO_BEGIN_INDEX = 5;
    public static final int DEADLINE_BEGIN_INDEX = 9;
    public static final int EVENT_BEGIN_INDEX = 6;

    /**
     * Constructs a new TaskManager Object from an ArrayList of Tasks.
     *
     * @param taskList A list of Task Objects that initialise the <code>taskList</code>.
     */
    public TaskManager(ArrayList<Task> taskList) {
        tasksList = taskList;
        foundList = new ArrayList<>();
    }

    /**
     * Returns the list of Tasks currently stored in this Task Manager.
     *
     * @return The list of all currently stored Tasks.
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Adds a new Task that is of the form of an Event Object.
     *
     * @param line A String containing both the command that specify the type of object
     *             to be added, and the contents of this Event Object.
     * @return A Task Object of type Event with the contents specified in <code>line</code>.
     * @throws InvalidEventException If the input is of an incorrect format.
     */
    public Task addNewEventTask(String line) throws InvalidEventException {
        Task newTask = new Event(line.substring(EVENT_BEGIN_INDEX));
        tasksList.add(newTask);
        return newTask;
    }
    /**
     * Adds a new Task that is of the form of a Deadline Object.
     *
     * @param line A String containing both the command that specify the type of object
     *             to be added, and the contents of this Deadline Object.
     * @return A Task Object of type Deadline with the contents specified in <code>line</code>.
     * @throws InvalidDeadlineException If the input is of an incorrect format.
     */
    public Task addNewDeadlineTask(String line) throws InvalidDeadlineException {
        Task newTask = new Deadline(line.substring(DEADLINE_BEGIN_INDEX));
        tasksList.add(newTask);
        return newTask;
    }
    /**
     * Adds a new Task that is of the form of a Todo Object.
     *
     * @param line A String containing both the command that specify the type of object
     *             to be added, and the contents of this Todo Object.
     * @return A Task Object of type Todo with the contents specified in <code>line</code>.
     */
    public Task addNewTodoTask(String line) {
        Task newTask = new ToDo(line.substring(TODO_BEGIN_INDEX));
        tasksList.add(newTask);
        return newTask;
    }

    /**
     * Mark or unmark a Task in the position specified by <code>indexToMark</code> as done.
     *
     * @param indexToMark An integer representing the location of the task inside a list starting
     *                    from 1.
     * @param isDone A boolean function that is true to mark a Task as done, else to unmark the Task.
     * @return The Task Object that have been changed by this function.
     * @throws TaskNumberOutOfRangeException If the <code>indexToMark</code> is of a value not inside the list.
     */
    public Task changeStateOfTask(int indexToMark, boolean isDone)
            throws TaskNumberOutOfRangeException {
        if (indexToMark >= Task.getItemCount() || indexToMark < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        tasksList.get(indexToMark).markAsState(isDone);
        return tasksList.get(indexToMark);
    }

    /**
     * Displays all the items inside the list, if the list is empty, display an error
     * message instead.
     * @param display The OutputDialogueManager that contains the String of dialogues to be
     *                displayed to provide further information to the user.
     */
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

    /**
     * Deletes an item from the list of Tasks, specified by the <code>indexToDelete</code>.
     * @param indexToDelete An integer representing the position of a Task in a list of Tasks
     *                      starting from 1.
     * @return The Task that is deleted.
     * @throws TaskNumberOutOfRangeException If the <code>indexToDelete</code> is of a value not inside the list.
     */
    public Task deleteItem(int indexToDelete) throws TaskNumberOutOfRangeException {
        if (indexToDelete >= Task.getItemCount() || indexToDelete < 0) {
            throw new TaskNumberOutOfRangeException();
        }
        Task toBeDeleted = tasksList.get(indexToDelete);
        tasksList.remove(indexToDelete);
        Task.decreaseItemCount();
        return toBeDeleted;
    }

    /**
     * Search the list of Tasks and find all the Tasks that contains the content of String
     * <code>toFind</code>, then store all those contents into the ArrayList of <code>foundList</code>.
     * @param toFind A String containing the word or phrase that one wished to find.
     * @param display The OutputDialogueManager that contains the String of dialogues to be
     *                displayed to provide further information to the user.
     * @throws NoMatchingItemException If no item in the lst of all tasks have content that contains
     *                                 the word or phrase in the input.
     */
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
