package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class DateData {

    private final HashMap<LocalDate, LinkedHashSet<Integer>> dateTable;

    public DateData() {
        this.dateTable = new HashMap<>();
    }

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
            dateTable.put(byDate, temp);
        } else {
            dateTable.get(fromDate).add(taskCount);
        }
    }

    public void handleDelete (int deletedIndex) {
        for (LocalDate i : dateTable.keySet()) {
            dateTable.get(i).remove(deletedIndex);
            for (int j : dateTable.get(i)) {
                if (j > deletedIndex) {
                    dateTable.get(i).remove(j);
                    dateTable.get(i).add(j + 1);
                }
            }
        }
    }

    public LinkedHashSet<Integer> findDate (LocalDate date) {
        if (dateTable.get(date) == null) {
            return new LinkedHashSet<>();
        }
        return dateTable.get(date);
    }
}
