package duke.task;

import duke.exceptions.DukeWrongArgsException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    /**
     * Adds a Todo task into the task-list.
     *
     * @param cleanInput Input from user that has whitespaces trimmed off.
     * @return Todo Returns the task that has been just added the the task-list
     * @throws StringIndexOutOfBoundsException On input of insufficient length.
     */
    public Todo addTodo(String cleanInput) throws StringIndexOutOfBoundsException {
        Todo todo = duke.Parser.parseTodo(cleanInput);
        this.add(todo);
        return todo;
    }

    /**
     * Adds a Deadline task into the task-list.
     *
     * @param message Input from the user split by spaces.
     * @return Deadline Returns the task that has been just added the task-list
     * @throws DukeWrongArgsException On wrong number of arguments provided.
     */
    public Deadline addDeadline(String[] message) throws DukeWrongArgsException {
        Deadline deadline = duke.Parser.parseDeadline(message);
        this.add(deadline);
        return deadline;
    }

    /**
     * Adds an Event task into the task-list.
     *
     * @param message Input from the user split by spaces.
     * @return Event Returns the task that has been just added the task-list
     * @throws DukeWrongArgsException On wrong number of arguments provided.
     */
    public Event addEvent(String[] message) throws DukeWrongArgsException {
        Event event = duke.Parser.parseEvent(message);
        this.add(event);
        return event;
    }

    /**
     * Marks the task in the task-list as done.
     *
     * @param message Input from the user split by spaces.
     * @return int Index of task in task-list that got marked as done.
     * @throws DukeWrongArgsException On wrong number of arguments provided.
     * @throws NumberFormatException  On invalid index of task to be marked.
     */
    public int markItem(String[] message) throws DukeWrongArgsException, NumberFormatException {
        int taskNumber = getTaskNumber(message);
        this.get(taskNumber - 1).setAsDone();
        return taskNumber - 1;
    }

    /**
     * Marks the task in the task-list as not done.
     *
     * @param message Input from the user split by spaces.
     * @return int Index of task in task-list that got marked as not done.
     * @throws DukeWrongArgsException On wrong number of arguments provided.
     * @throws NumberFormatException  On invalid index of task to be marked.
     */
    public int unmarkItem(String[] message) throws DukeWrongArgsException, NumberFormatException {
        int taskNumber = getTaskNumber(message);
        this.get(taskNumber - 1).setAsNotDone();
        return taskNumber - 1;
    }

    /**
     * Deletes the task in the task-list.
     *
     * @param message Input from the user split by spaces.
     * @return Task Returns tasks that just got deleted.
     * @throws DukeWrongArgsException On wrong number of arguments provided.
     */
    public Task deleteTask(String[] message) throws DukeWrongArgsException {
        int taskNumber = getTaskNumber(message);
        Task deletedTask = get(taskNumber - 1);
        remove(taskNumber - 1);
        return deletedTask;
    }

<<<<<<< HEAD
    /**
     * Loads the previous task-list saved in the database file.
     *
     * @param fileContent Database file concatenated into a String.
     */
=======
    public TaskList findTask(String message) {
        TaskList tasksFound = new TaskList();
        String keyword = duke.Parser.parseKeyword(message);
        for (Task task : this) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

>>>>>>> master
    public void loadTaskList(String fileContent) {
        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] taskInfo = line.split("\\|");
            switch (taskInfo[0]) {
            case "T":
                Todo todo = new Todo(taskInfo[2]);
                if (taskInfo[1].equals("X")) {
                    todo.setAsDone();
                } else {
                    todo.setAsNotDone();
                }
                this.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(taskInfo[2], taskInfo[3]);
                if (taskInfo[1].equals("X")) {
                    deadline.setAsDone();
                } else {
                    deadline.setAsNotDone();
                }
                this.add(deadline);
                break;
            case "E":
                Event event = new Event(taskInfo[2], taskInfo[4], taskInfo[3]);
                if (taskInfo[1].equals("X")) {
                    event.setAsDone();
                } else {
                    event.setAsNotDone();
                }
                this.add(event);
                break;
            }
        }
    }

    private static int getTaskNumber(String[] message) throws DukeWrongArgsException, NumberFormatException {
        // Check for correct number of arguments
        if (message.length != 2) {
            throw new DukeWrongArgsException();
        }

        // Check that second argument provided is a valid number
        try {
            return Integer.parseInt(message[1]);
        } catch (NumberFormatException error) {
            throw new NumberFormatException();
        }
    }
}
