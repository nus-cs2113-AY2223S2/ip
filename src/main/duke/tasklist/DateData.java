package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Represents all data to enable functions relating to <code>LocalDate</code>.
 */
public class DateData {

    private final HashMap<LocalDate, LinkedHashSet<Integer>> dateTable;

    /**
     * Initialize data for <code>LocalDate</code> functions.
     */
    public DateData() {
        this.dateTable = new HashMap<>();
    }

    /**
     * Function to add a <code>Deadline</code> into this object.
     * Checks if user input for <code>/by</code> can be converted into <code>LocalDate</code>.
     *
     * @param deadline The task object involved.
     * @param taskCount The number id for the task object in <code>TaskData</code>.
     */
    public void addDeadline (Deadline deadline, int taskCount) {
        LocalDate date = deadline.getLocalByDate();
        if (dateTable.get(date) == null) {
            LinkedHashSet<Integer> temp = new LinkedHashSet<>();
            temp.add(taskCount);
            dateTable.put(date, temp);
        } else {
            dateTable.get(date).add(taskCount);
        }
    }

    /**
     * Function to add an <code>Event</code> into this object.
     * Checks if user input for <code>/from</code> and <code>/to</code> can be converted into <code>LocalDate</code>.
     *
     * @param event The task object involved.
     * @param taskCount The number id for the task object in <code>TaskData</code>.
     */
    public void addEvent (Event event, int taskCount) {
        LocalDate byDate = event.getLocalByDate();
        LocalDate fromDate = event.getLocalFromDate();
        if (dateTable.get(byDate) == null) {
            LinkedHashSet<Integer> temp = new LinkedHashSet<>();
            temp.add(taskCount);
            dateTable.put(byDate, temp);
        } else {
            dateTable.get(byDate).add(taskCount);
        }
        if (dateTable.get(fromDate) == null) {
            LinkedHashSet<Integer> temp = new LinkedHashSet<>();
            temp.add(taskCount);
            dateTable.put(fromDate, temp);
        } else {
            dateTable.get(fromDate).add(taskCount);
        }
    }

    /**
     * Deletes the task object involved when <code>delete</code> is called.
     * Shifts all the entries such that the number id of each task object is in line with their number id inside
     * <code>TaskData</code>.
     *
     * @param deletedIndex The number id of the task object deleted.
     */
    public void handleDelete (int deletedIndex) {
        for (LocalDate i : dateTable.keySet()) {
            dateTable.get(i).remove(deletedIndex);
            for (int j : dateTable.get(i)) {
                if (j > deletedIndex) {
                    dateTable.get(i).remove(j);
                    dateTable.get(i).add(j - 1);
                }
            }
        }
    }

    /**
     * Finds all the task objects with the same date as the user input.
     *
     * @param date The user input date.
     * @return Returns a <code>LinkedHashSet</code> of all the number id of the task objects involved.
     * Returns an empty <code>LinkedHashSet</code> if no matching task objects.
     */
    public LinkedHashSet<Integer> findDate (LocalDate date) {
        if (dateTable.get(date) == null) {
            return new LinkedHashSet<>();
        }
        return dateTable.get(date);
    }
}
