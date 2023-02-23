package duke.task;

import duke.exceptions.DukeWrongArgsException;
import java.util.ArrayList;
import java.util.Arrays;

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

    private static Todo parseTodo(String message) throws StringIndexOutOfBoundsException {
        int secondArgStartIndex = 5;
        if (message.length() < secondArgStartIndex) {
            throw new StringIndexOutOfBoundsException();
        } else {
            return new Todo(message.substring(secondArgStartIndex));
        }
    }

    private static Deadline parseDeadline(String[] message) throws DukeWrongArgsException {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 2; i < message.length; i++) {
            if (message[i].equalsIgnoreCase("/by")) {
                descriptionEndIndex = i;
                endDateStartIndex = i + 1;
                break;
            }
        }

        // Checks if correct argument is provided
        if (endDateStartIndex == 0) {
            throw new DukeWrongArgsException();
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] deadlineArray = new String[2];
        deadlineArray[0] = String.join(" ", descriptionArray);
        deadlineArray[1] = String.join(" ", endDateArray);

        return new Deadline(deadlineArray[0], deadlineArray[1]);
    }

    private static Event parseEvent(String[] message) throws DukeWrongArgsException {
        int descriptionStartIndex = 1;
        int descriptionEndIndex = 0;
        int startDateStartIndex = 0;
        int startDateEndIndex = 0;
        int endDateStartIndex = 0;
        int endDateEndIndex = message.length;

        for (int i = 1; i < message.length; i++) {
            if (message[i].equalsIgnoreCase("/from")) {
                descriptionEndIndex = i;
                startDateStartIndex = i + 1;
            }
            if (message[i].equalsIgnoreCase("/to")) {
                startDateEndIndex = i;
                endDateStartIndex = i + 1;
                break;
            }
        }

        if (startDateStartIndex == 0 || startDateEndIndex == 0) {
            throw new DukeWrongArgsException();
        }

        String[] descriptionArray = Arrays.copyOfRange(message, descriptionStartIndex, descriptionEndIndex);
        String[] startDateArray = Arrays.copyOfRange(message, startDateStartIndex, startDateEndIndex);
        String[] endDateArray = Arrays.copyOfRange(message, endDateStartIndex, endDateEndIndex);

        String[] eventArray = new String[3];
        eventArray[0] = String.join(" ", descriptionArray);
        eventArray[1] = String.join(" ", startDateArray);
        eventArray[2] = String.join(" ", endDateArray);

        return new Event(eventArray[0], eventArray[1], eventArray[2]);
    }

    public Todo addTodo(String cleanInput) throws StringIndexOutOfBoundsException {
        Todo todo = parseTodo(cleanInput);
        this.add(todo);
        return todo;
    }

    public Deadline addDeadline(String[] message) throws DukeWrongArgsException {
        Deadline deadline = parseDeadline(message);
        this.add(deadline);
        return deadline;
    }

    public Event addEvent(String[] message) throws DukeWrongArgsException {
        Event event = parseEvent(message);
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
