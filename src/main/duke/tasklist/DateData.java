package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class DateData {

    private final HashMap<LocalDate, ArrayList<Task>> byDateTable;
    private final HashMap<LocalDate, ArrayList<Task>> fromDateTable;

    public DateData() {
        this.byDateTable = new HashMap<>();
        this.fromDateTable = new HashMap<>();
    }

    public void addDeadline (Deadline deadline) {
        LocalDate date = deadline.getLocalByDate();
        if (byDateTable.get(date) == null) {
            ArrayList<Task> temp = new ArrayList<>();
            temp.add(deadline);
            byDateTable.put(date, temp);
        } else {
            byDateTable.get(date).add(deadline);
        }
    }

    public void addEvent (Event event) {
        LocalDate byDate = event.getLocalByDate();
        LocalDate fromDate = event.getLocalFromDate();
        if (byDateTable.get(byDate) == null) {
            ArrayList<Task> temp = new ArrayList<>();
            temp.add(event);
            byDateTable.put(byDate, temp);
        } else {
            byDateTable.get(byDate).add(event);
        }
        if (fromDateTable.get(fromDate) == null) {
            ArrayList<Task> temp = new ArrayList<>();
            temp.add(event);
            fromDateTable.put(byDate, temp);
        } else {
            fromDateTable.get(fromDate).add(event);
        }
    }

}
