package duke;

import java.util.ArrayList;

public class TaskList {

    private static final String MATCHING_TASK_MESSAGE = "Here are the matching tasks in your list:";
    protected static ArrayList<Task> tasks;
    private static final String BY_DELIMITER = " /by ";
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds the new Todo task to the task list
     *
     * @param td The Todo task to add
     */
    public void addTodo(Todo td) {
        tasks.add(td);
    }

    /**
     * Adds the new Deadline task to the task list
     *
     * @param d The Deadline task to add
     */
    public void addDeadline(Deadline d) {
        tasks.add(d);
    }

    /**
     * Adds the new Event task to the task list
     *
     * @param e The Event task to add
     */
    public void addEvent(Event e) {
        tasks.add(e);
    }

    public void markIndexAsDone(int markIndex) {
        tasks.get(markIndex).markAsDone();
    }

    public void unmarkIndexAsDone(int unmarkIndex) {
        tasks.get(unmarkIndex).markAsNotDone();
    }

    /**
     * @param taskIndex Index of Task in Array List to retrieve
     * @return Task that is retrieved
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Retrieve a Task that is located in the Task list
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Deletes a Task that is located in the Task list
     *
     * @param deleteIndex Index of Task in Array List to delete from
     */
    public void deleteTask(int deleteIndex) {
        tasks.get(deleteIndex).remove();
        tasks.remove(deleteIndex);
    }

    /**
     * Creates a Todo based on what the user typed and save it into the ArrayList
     *
     * @param words original string that the user typed in
     * @return Todo data type
     */
    public Todo createTodo(String[] words) {
        Todo td = new Todo(words[1]);
        tasks.add(td);
        return td;
    }

    /**
     * Creates a Deadline based on what the user typed and save it into the ArrayList
     *
     * @param words original string that the user typed in
     * @return Deadline data type
     */
    public Deadline createDeadline(String[] words) {

        String line;
        line = words[1]; // to remove the command
        String[] deadlineDetails = line.split(BY_DELIMITER);
        Deadline d = new Deadline(deadlineDetails[0], deadlineDetails[1]);
        tasks.add(d);
        return d;

    }

    /**
     * Creates an Event based on what the user typed and save it into the ArrayList
     *
     * @param words original string that the user typed in
     * @return Event data type
     */
    public Event createEvent(String[] words) {

        String line;
        line = words[1]; // to remove the command
        String[] eventDetails = line.split(FROM_DELIMITER);
        String eventName = eventDetails[0];
        String from = line.substring(line.indexOf(FROM_DELIMITER) + FROM_DELIMITER.length(), line.indexOf(TO_DELIMITER));
        eventDetails = line.split(TO_DELIMITER);
        String to = eventDetails[1];
        Event e = new Event(eventName, from, to);
        tasks.add(e);
        return e;
    }

    /**
     * Print the current Task list
     */
    public void printList() {
        int listIndex = 0;
        for (Task t : tasks) {
            if (t != null) {
                System.out.println(++listIndex + ". " + t);
            }
        }
    }

    /**
     * Prints the current list of tasks that matches the keyword that the user typed
     *
     * @param words Original string that the user typed in
     */
    public void printMatchedList(String[] words) {
        System.out.println(MATCHING_TASK_MESSAGE);
        String keyword = words[1];
        int listIndex = 0;
        for (Task t : tasks) {
            if (t != null && t.description.matches("(.*)" + keyword + "(.*)")) {
                System.out.println(++listIndex + ". " + t);
            }
        }
    }


}
