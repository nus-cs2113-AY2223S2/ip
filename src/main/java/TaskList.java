import task.Deadline;
import exception.DukeException;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an underlying ArrayList<Task> that stores the task list.
 * Contains methods to mark, unmark, add, delete, find different types of tasks.
 */
public class TaskList {
    public static final String EMPTY_TODO_DESCRIPTION = "     ☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_DEADLINE_DESCRIPTION = "     ☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EMPTY_EVENT_DESCRIPTION = "     ☹ OOPS!!! The description of a event cannot be empty.";
    protected ArrayList<Task> tasks;
    protected Ui ui;

    /**
     * Constructs a new TaskList object that instantiates a new ArrayList.
     * Creates new Ui object in order to print certain responses.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    /**
     * Marks the given task as done.
     *
     * @param input the task number in String type in order of addition to the list.
     */
    public void markTask(String input) {
        int taskNumber = Integer.parseInt(input);
        tasks.get(taskNumber - 1).markAsDone();
        ui.printMarkedTask(tasks.get(taskNumber - 1));
    }

    /**
     * Marks the given task as not done.
     *
     * @param input the task number in String type in order of addition to the list.
     */
    public void unmarkTask(String input) {
        int taskNumber = Integer.parseInt(input);
        tasks.get(taskNumber - 1).markAsNotDone();
        ui.printUnmarkedTask(tasks.get(taskNumber - 1));
    }

    /**
     * Adds a task of type Todo to the task list given the correct format.
     * If addition succeeds, proceeds to print the Todo task that is added to the task list.
     *
     * @param description the details of the Todo task to be added.
     * @throws exception.DukeException if the description of the Todo task is empty.
     */
    public void addTodoTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException(EMPTY_TODO_DESCRIPTION);
        }
        Todo newTodoTask = new Todo(description);
        tasks.add(newTodoTask);
        ui.printAddedTask(newTodoTask, tasks.size());
    }

    /**
     * Formats the date string from "yyyy-mm-dd" to a format of "d MMM yyyy".
     *
     * @param dateString the string that is to be formatted and in the form of "yyyy-mm-dd".
     * @return the newly formatted string in "d MMM yyyy" format.
     */
    public String formatDate(String dateString) {
        int firstHyphenIndex = dateString.indexOf("-");
        int dateStartIndex = firstHyphenIndex - 4;
        int dataEndIndex = firstHyphenIndex + 6;
        String extractedDate = dateString.substring(dateStartIndex, dataEndIndex);
        LocalDate date = LocalDate.parse(extractedDate);
        String formattedDate =  date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return dateString.replace(extractedDate, formattedDate);
    }

    /**
     * Adds a task of type Deadline to the task list given the correct format, formats the date given in the deadline
     * if given in a certain format. If addition succeeds, proceeds to print the Deadline task that is added
     * to the task list.
     *
     * @param description the details of the Deadline task to be added.
     * @throws DukeException if the description of the Deadline task is empty.
     */
    public void addDeadlineTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException(EMPTY_DEADLINE_DESCRIPTION);
        }
        int firstSlashIndex = description.indexOf("/by");
        int detailsEndIndex = firstSlashIndex - 1;
        String details = description.substring(0, detailsEndIndex);
        int byStartIndex = firstSlashIndex + 4;
        String by = description.substring(byStartIndex);
        if (by.contains("-")) { //case where user keys in a date
            by = formatDate(by);
        }
        Deadline newDeadlineTask = new Deadline(details, by);
        tasks.add(newDeadlineTask);
        ui.printAddedTask(newDeadlineTask, tasks.size());
    }

    /**
     * Adds a task of type Event to the task list given the correct format.
     * If addition succeeds, proceeds to print the Event task that is added to the task list.
     *
     * @param description the details of the Event task to be added.
     * @throws DukeException if the description of the Event task is empty.
     */
    public void addEventTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException(EMPTY_EVENT_DESCRIPTION);
        }
        int firstSlashIndex = description.indexOf("/from");
        int secondSlashIndex = description.indexOf("/to", firstSlashIndex + 1);
        int detailsEndIndex = firstSlashIndex - 1;
        String details = description.substring(0, detailsEndIndex);
        int fromStartIndex = firstSlashIndex + 6;
        int fromEndIndex = secondSlashIndex - 1;
        String from = description.substring(fromStartIndex, fromEndIndex);
        int toStartIndex = secondSlashIndex + 4;
        String to = description.substring(toStartIndex);
        Event newEventTask = new Event(details, from, to);
        tasks.add(newEventTask);
        ui.printAddedTask(newEventTask, tasks.size());
    }

    /**
     * Deletes a task based on the task number given.
     * If deletion succeeds, proceeds to print the deleted task that is removed from the task list.
     *
     * @param taskNumber the order number at which is task is added to the task list.
     */
    public void deleteTask(int taskNumber) {
        int index = taskNumber - 1;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ui.printDeletedTask(deletedTask, tasks.size());
    }

    /**
     * Finds a task based on the keyword given.
     * If finding succeeds, proceeds to add the matching tasks to the arraylist of a new TaskList object.
     * Afterwards, prints the list of matching found tasks from the keyword given.
     *
     * @param keyword the keyword that is used to find matching tasks containing it.
     */
    public void findTask(String keyword) {
        TaskList taskList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String details = tasks.get(i).getDetails();
            if (details.contains(keyword)) {
                taskList.tasks.add(currentTask);
            }
        }
        ui.printFoundTask(taskList);
    }

    /**
     * Getter method that obtains the Task object currently in the arraylist of the TaskList object.
     *
     * @param taskNumber the order number at which is task is added to the task list.
     * @return the Task object found.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a new Task object into the arraylist of the TaskList object.
     *
     * @param newTask the new Task object to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Getter method that returns the size of the arraylist of the TaskList object.
     *
     * @return an integer corresponding to the total size of the arraylist.
     */
    public int getSize() {
        return tasks.size();
    }


}
