import duke.exception.IllegalFormatException;
import duke.exception.InvalidInputException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();

    protected UI ui = new UI();

    protected void showList() {
        ui.printList(tasks, tasks.size());
    }

    /**
     * Marks the task as completed based on its index.
     *
     * @param userInput Input string that is provided by the user.
     * @throws InvalidInputException when the index provided is not within the task list
     * @throws MissingInputException when the index is not provided by the user.
     */
    protected void markTask(String userInput) throws InvalidInputException, MissingInputException {
        if (userInput.substring(4).trim().equals("")) {
            throw new MissingInputException();
        } else {
            int taskIndex = Integer.parseInt(userInput.substring(4).trim());
            if (taskIndex == 0 || taskIndex > tasks.size()){
                throw new InvalidInputException();
            } else {
                Task markedTask = tasks.get(taskIndex - 1);
                markedTask.markDone();
                ui.printTaskMarked(markedTask);
            }
        }
    }

    /**
     * Undo the mark on the task based on its index.
     *
     * @param userInput Input string that is provided by the user.
     * @throws InvalidInputException when the index provided is not within the task list.
     * @throws MissingInputException when the index is not provided by the user.
     */
    protected void unmarkTask(String userInput) throws InvalidInputException, MissingInputException {
        if (userInput.substring(6).trim().equals("")) {
            throw new MissingInputException();
        } else {
            int taskIndex = Integer.parseInt(userInput.substring(6).trim());
            if (taskIndex == 0 || taskIndex > tasks.size()){
                throw new InvalidInputException();
            } else {
                Task unmarkedTask = tasks.get(taskIndex - 1);
                unmarkedTask.unmarkDone();
                ui.printTaskUnmarked(unmarkedTask);
            }
        }
    }


    /**
     * Creates a Todo task by taking the substring of the word(s) after "todo".
     *
     * @param userInput Input string that is provided by the user.
     * @throws MissingInputException when the description of the task if not provided.
     */
    protected void createTodo(String userInput) throws MissingInputException {
        if (userInput.substring(4).trim().equals("")) {
            throw new MissingInputException();
        } else {
            String description = userInput.substring(4).trim();
            Todo todoTask = new Todo(description);
            tasks.add(todoTask);
            ui.printTaskAdded(todoTask, tasks.size());
        }
    }

    /**
     * Creates a deadline task by taking the substring of the words after "deadline" and before "/by".
     * The deadline of the task is based on the substring of word(s) after "/by'.
     *
     * @param userInput Input string that is provided by the user.
     * @throws MissingInputException if either the deadline date or description is incomplete.
     * @throws InvalidInputException if "/by" is no provided.
     */
    protected void createDeadline(String userInput) throws InvalidInputException, MissingInputException {
        if ((userInput.substring(8).trim().equals("") || userInput.indexOf("/by") < 8)) {
            throw new InvalidInputException();
        } else {
            int byIndex = userInput.indexOf("/by");
            String description = userInput.substring(8, byIndex).trim();
            String date = userInput.substring(byIndex + 3).trim();
            if (description.length() < 1 | date.length() < 1) {
                throw new MissingInputException();
            }
            Deadline deadlineTask = new Deadline(description, date);
            tasks.add(deadlineTask);
            ui.printTaskAdded(deadlineTask, tasks.size());
        }
    }

    /**
     * Creates an event task by taking the substring of the words after "event" and before "/from".
     * Start time of the event is derived from taking the substring between "/from" and "/to".
     * End time of the event is derived from taking the substring after "/to".
     *
     * @param userInput Input string that is provided by the user.
     * @throws IllegalFormatException if the order of "/from" has been placed after "/to".
     * @throws InvalidInputException if either the expected description "/from" or "/to" is not provided.
     * @throws MissingInputException if either the description, start time or end time is incomplete.
     */
    protected void createEvent(String userInput) throws IllegalFormatException, InvalidInputException, MissingInputException {
        if (userInput.substring(5).trim().equals("") || userInput.indexOf("/from") < 5 || userInput.indexOf("/to") < 5) {
            throw new InvalidInputException();
        } else {
            int fromIndex = userInput.indexOf("/from");
            int toIndex = userInput.indexOf("/to");
            String description = userInput.substring(5, fromIndex).trim();
            String eventStart = userInput.substring(fromIndex + 5, toIndex).trim();
            String eventEnd = userInput.substring(toIndex + 3).trim();
            if (description.length() < 1 | eventStart.length() < 1 | eventEnd.length() < 1) {
                throw new MissingInputException();
            } else if (fromIndex > toIndex) {
                throw new IllegalFormatException();
            }
            Event eventTask = new Event(description, eventStart, eventEnd);
            tasks.add(eventTask);
            ui.printTaskAdded(eventTask, tasks.size());
        }
    }

    /**
     * Removes the task in the list based on the index provided.
     *
     * @param userInput Input string that is provided by the user.
     * @throws InvalidInputException if the task to be deleted is not within the list
     */
    protected void deleteTask(String userInput) throws InvalidInputException {
        int deleteIndex = Integer.parseInt(userInput.substring(6).trim());
        if (deleteIndex > tasks.size() || deleteIndex == 0) {
            throw new InvalidInputException();
        } else {
            Task deleteTask = tasks.get(deleteIndex - 1);
            tasks.remove(deleteTask);
            ui.printTaskDeleted(deleteTask, tasks.size());
        }
    }

    /**
     * Finds the tasks in the list that contains the keyword provided and store in a new taskList.
     *
     * @param userInput Input string that is provided by the user.
     * @throws MissingInputException if the keyword is not provided.
     */
    protected void findTask(String userInput) throws MissingInputException {
        if (userInput.substring(4).trim().equals("")) {
            throw new MissingInputException();
        } else {
            String keyword = userInput.substring(4).trim();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.findMatch(keyword)) {
                    matchingTasks.add(task);
                }
            }
            ui.printTaskFound(matchingTasks, matchingTasks.size());
        }
    }

    /**
     * Creates a Todo task based on the file being read in duke.txt
     *
     * @param taskStatus Input string of either "0" or "1" to determine if the task is done.
     * @param taskDescription Input string that describes the task.
     */
    public void readTodoTask(String taskStatus, String taskDescription) {
        Todo existingTask = new Todo(taskDescription);
        if (taskStatus.equals("1")) {
            existingTask.markDone();
        }
        tasks.add(existingTask);
    }

    /**
     * Creates a Deadline task based on the file being read in duke.txt
     *
     * @param taskStatus Input string of either "0" or "1" to determine if the task is done.
     * @param taskDescription Input string that describes the task.
     * @param byDate Input string that contains the date of the deadline task.
     */
    public void readDeadlineTask(String taskStatus, String taskDescription, String byDate) {
        Deadline existingTask = new Deadline(taskDescription, byDate);
        if (taskStatus.equals("1")) {
            existingTask.markDone();
        }
        tasks.add(existingTask);
    }

    /**
     * Create an Event task based on the file being read in duke.txt
     *
     * @param taskStatus Input string of either "0" or "1" to determine if the task is done.
     * @param taskDescription Input string that describes the task.
     * @param fromDate Input string that contains the start date of the Event task.
     * @param toDate Input string that contains the end date of the Event task.
     */
    public void readEventTask(String taskStatus, String taskDescription, String fromDate, String toDate) {
        Event existingTask = new Event(taskDescription, fromDate, toDate);
        if (taskStatus.equals("1")) {
            existingTask.markDone();
        }
        tasks.add(existingTask);
    }
}
