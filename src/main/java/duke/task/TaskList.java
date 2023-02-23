package duke.task;

import duke.exceptions.DukeWrongArgsException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

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

    public Todo addTodo(String cleanInput) throws StringIndexOutOfBoundsException {
        Todo todo = duke.Parser.parseTodo(cleanInput);
        this.add(todo);
        return todo;
    }

    public Deadline addDeadline(String[] message) throws DukeWrongArgsException {
        Deadline deadline = duke.Parser.parseDeadline(message);
        this.add(deadline);
        return deadline;
    }

    public Event addEvent(String[] message) throws DukeWrongArgsException {
        Event event = duke.Parser.parseEvent(message);
        this.add(event);
        return event;
    }

    public int markItem(String[] message) throws DukeWrongArgsException, NumberFormatException {
        int taskNumber = getTaskNumber(message);
        this.get(taskNumber - 1).setAsDone();
        return taskNumber - 1;
    }

    public int unmarkItem(String[] message) throws DukeWrongArgsException, NumberFormatException {
        int taskNumber = getTaskNumber(message);
        this.get(taskNumber - 1).setAsNotDone();
        return taskNumber - 1;
    }

    public Task deleteTask(String[] message) throws DukeWrongArgsException {
        int taskNumber = getTaskNumber(message);
        Task deletedTask = get(taskNumber - 1);
        remove(taskNumber - 1);
        return deletedTask;
    }

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
}
