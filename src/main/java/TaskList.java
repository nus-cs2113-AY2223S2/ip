import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;
import duke.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an underlying ArrayList<Task> that stores the task list.
 * Contains methods to mark, unmark, add, delete, find different types of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected Ui ui;
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        ui.printMarkedTask(tasks.get(taskNumber - 1));
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
        ui.printUnmarkedTask(tasks.get(taskNumber - 1));
    }

    public void addTodoTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTodoTask = new Todo(description);
        tasks.add(newTodoTask);
        ui.printAddedTask(newTodoTask, tasks.size());
    }

    public String formatDate(String dateString) {
        int firstHyphenIndex = dateString.indexOf("-");
        int dateStartIndex = firstHyphenIndex - 4;
        int dataEndIndex = firstHyphenIndex + 6;
        String extractedDate = dateString.substring(dateStartIndex, dataEndIndex);
        LocalDate date = LocalDate.parse(extractedDate);
        String formattedDate =  date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return dateString.replace(extractedDate, formattedDate);
    }

    public void addDeadlineTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        int firstSlashIndex = description.indexOf("/");
        int detailsEndIndex = firstSlashIndex - 1;
        String details = description.substring(0, detailsEndIndex);
        int byStartIndex = firstSlashIndex + 4;
        String by = description.substring(byStartIndex);
        if (by.contains("-")) { //case where user keys in a date
            by = formatDate(by);
        }
        Deadline newDeadlineTask = new Deadline(details, by);
        tasks.add(newDeadlineTask);
        ui.printAddedTask(newDeadlineTask, tasks.size());
    }

    public void addEventTask(String description) throws DukeException {
        if (description.isBlank()) {
            //for the case where user keys in a space after the command
            throw new DukeException("     ☹ OOPS!!! The description of a event cannot be empty.");
        }
        int firstSlashIndex = description.indexOf("/");
        int secondSlashIndex = description.indexOf("/", firstSlashIndex + 1);
        int detailsEndIndex = firstSlashIndex - 1;
        String details = description.substring(0, detailsEndIndex);
        int fromStartIndex = firstSlashIndex + 6;
        int fromEndIndex = secondSlashIndex - 1;
        String from = description.substring(fromStartIndex, fromEndIndex);
        int toStartIndex = secondSlashIndex + 4;
        String to = description.substring(toStartIndex);
        Event newEventTask = new Event(details, from, to);
        tasks.add(newEventTask);
        ui.printAddedTask(newEventTask, tasks.size());
    }

    public void deleteTask(int taskNumber) {
        int index = taskNumber - 1;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ui.printDeletedTask(deletedTask, tasks.size());
    }

    public void findTask(String keyword) {
        TaskList taskList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String details = tasks.get(i).getDetails();
            if (details.contains(keyword)) {
                taskList.tasks.add(currentTask);
            }
        }
        ui.printFoundTask(taskList);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public int getSize() {
        return tasks.size();
    }


}
