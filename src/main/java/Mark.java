import java.time.LocalDateTime;
import java.util.ArrayList;

public class Mark {

    /**
     * Marks ith Task in tasks as done
     * @param i index of task to be marked
     * @param line user input line
     * @param tasks arraylist of tasks
     */
    public static void mark(int i, String line, ArrayList<Task> tasks) throws DukeException {
        int number = Integer.parseInt(line.substring(5));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        Task task = tasks.get(number - 1);
        String description = task.getDescription();
        String type = task.getType();
        LocalDateTime datetime1 = task.getDateTime1();
        LocalDateTime datetime2 = task.getDateTime2();
        tasks.set(number - 1, new Task(description, type, "[X]", datetime1, datetime2));
    }

    /**
     * Marks ith Task in tasks as undone
     * @param i index of task to be unmarked
     * @param line user input line
     * @param tasks arraylist of tasks
     */
    public static void unmark(int i, String line, ArrayList<Task> tasks) throws DukeException {
        int number = Integer.parseInt(line.substring(7));
        if (number > i || number < 1) {
            throw new DukeException();
        }
        Task task = tasks.get(number - 1);
        String description = task.getDescription();
        String type = task.getType();
        LocalDateTime datetime1 = task.getDateTime1();
        LocalDateTime datetime2 = task.getDateTime2();
        tasks.set(number - 1, new Task(description, type, "[ ]", datetime1, datetime2));
    }

}
