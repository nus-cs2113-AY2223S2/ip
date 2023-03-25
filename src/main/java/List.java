import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * List class for methods dealing with adding/removing from list
 */
public class List {

    /**
     * Deletes ith task in list
     * @param tasks arraylist of tasks
     * @param i index of task in list to be deleted
     * @param line user input
     */
    public static void delete(ArrayList<Task> tasks, int i, String line) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        tasks.remove(number - 1);
    }

    /**
     * Adds a todo task to the list
     * @param tasks arraylist of tasks
     * @param formatter for formatting LocalDateTime values to "yyyy-MM-dd'T'HH:mm"
     * @param line user input
     */
    public static void todo(ArrayList<Task> tasks, DateTimeFormatter formatter, String line) throws DukeException {
        String description = line.substring(4);
        if (description.isBlank()) {
            throw new DukeException();
        }
        LocalDateTime datetime1 = DateTime.toLocalDateTime("2015-10-23T03:34", formatter);
        LocalDateTime datetime2 = DateTime.toLocalDateTime("2015-10-23T03:34", formatter);
        tasks.add(new Task(description, "[T]", "[ ]", datetime1, datetime2));
    }

    /**
     * Adds a deadline task to the list
     * @param tasks arraylist of tasks
     * @param formatter for formatting LocalDateTime values to "yyyy-MM-dd'T'HH:mm"
     * @param line user input
     */
    public static void deadline(ArrayList<Task> tasks, DateTimeFormatter formatter, String line) throws DukeException {
        int slash = line.indexOf("/");
        String description = line.substring(8, slash - 1);
        if (description.isBlank()) {
            throw new DukeException();
        }
        String by = line.substring(slash + 4);
        LocalDateTime datetime1 = DateTime.toLocalDateTime(by, formatter);
        LocalDateTime datetime2 = DateTime.toLocalDateTime("2015-10-23T03:34", formatter);
        tasks.add(new Task(description, "[D]", "[ ]", datetime1, datetime2));
    }

    /**
     * Adds an event task to the list
     * @param tasks arraylist of tasks
     * @param formatter for formatting LocalDateTime values to "yyyy-MM-dd'T'HH:mm"
     * @param line user input
     */
    public static void event(ArrayList<Task> tasks, DateTimeFormatter formatter, String line) throws DukeException {
        int slash1 = line.indexOf("/");
        int slash2 = line.indexOf("/", slash1 + 1);
        String description = line.substring(5, slash1 - 1);
        if (description.isBlank()) {
            throw new DukeException();
        }
        String from = line.substring(slash1 + 6, slash2 - 1);
        String to = line.substring(slash2 + 4);
        LocalDateTime datetime1 = DateTime.toLocalDateTime(from, formatter);
        LocalDateTime datetime2 = DateTime.toLocalDateTime(to, formatter);
        tasks.add(new Task(description, "[E]", "[ ]", datetime1, datetime2));
    }

}
