package Duke;

import Duke.Exception.EmptyDeadlineException;
import Duke.Exception.EmptyEventsException;
import Duke.Exception.EmptyToDoException;
import Duke.Exception.NullCommandException;
import Duke.Task.Deadlines;
import Duke.Task.Events;
import Duke.Task.Task;
import Duke.Task.ToDos;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    private static final int MARK_INDEX = 5;
    private static final int UNMARK_INDEX = 7;
    private static final int DELETE_INDEX = 7;

    private static final int FIND_INDEX = 5;
    public static ArrayList<Task> list;

    /**
     * Represents an arraylist of Task objects that can be modified.
     */
    public TaskList(String filepath) {
        try {
            list = Storage.stringToArraylistConverter(Storage.parseFileContentsToString(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("Illegal file path");
        }
    }

    /**
     * Adds a Task object to the TaskList.
     *
     * @param line input from user indicating the task to be added.
     * @throws EmptyToDoException If line is empty.
     * @throws EmptyDeadlineException If line is empty.
     * @throws EmptyEventsException If line is empty.
     * @throws IndexOutOfBoundsException If line's input format violates that of Deadlines or Events object.
     */
    public void addTask(String line) {
        Ui.addTaskSuccess();
        try {
            Task newTask = new Task(line);
            if (line.startsWith("todo")) {
                newTask = new ToDos(line);
            } else if (line.startsWith("deadline")) {
                newTask = new Deadlines(line);
            } else if (line.startsWith("event")) {
                newTask = new Events(line);
            }
            this.list.add(newTask);
            Ui.printTask(newTask);
            Ui.printListSize(this.list);
            Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
        } catch (EmptyToDoException e) {
            System.out.println("Sire, you have yet to tell me what is it you want to do.");
        } catch (EmptyDeadlineException e) {
            System.out.println("Sire, what is it that is due your specified time?");
        } catch (EmptyEventsException e) {
            System.out.println("Sire, your event is unclear. Please specify.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Don't hold back Sire. I am here to serve.");
        }
    }

    /**
     * Deletes the Task object from the TaskList.
     *
     * @param task input from user indicating the task to be deleted.
     */
    public void delete(String task) {
        String indexOfTask = task.substring(DELETE_INDEX);
        Ui.deleteSuccess(this.list, indexOfTask);
        this.list.remove(Integer.parseInt(indexOfTask) - 1);
        Ui.printListSize(this.list);
        Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
    }

    /**
     * Marks the Task object in the TaskList as done.
     *
     * @param task input from user indicating the task to be marked.
     */
    public void mark(String task) {
        String indexOfTask = task.substring(MARK_INDEX); // get the number of task to be marked
        Task taskToBeMarked = this.list.get(Integer.parseInt(indexOfTask) - 1); // convert from 1-based to 0-based
        taskToBeMarked.markTask();
        Ui.markSuccess(list, indexOfTask);
        Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
    }

    /**
     * Unmarks the Task object in the TaskList as undone.
     *
     * @param task input from user indicating the task to be unmarked.
     */
    public void unmark(String task) {
        String indexOfTask = task.substring(UNMARK_INDEX);
        Task taskToBeUnmarked = this.list.get(Integer.parseInt(indexOfTask) - 1);
        taskToBeUnmarked.unmarkTask();
        Ui.unmarkSuccess(list, indexOfTask);
        Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
    }

    /**
     * Prints the Task object in the TaskList.
     *
     * @param task task object to be printed.
     */
    public void printTasksFound (String task) {
        String taskToBeFound = task.substring(FIND_INDEX);
        Ui.findSuccess();
        for (int i = 0; i < this.list.size(); ++i) {
            if (this.list.get(i).getDescription().contains(taskToBeFound)) {
                Ui.printTask(this.list.get(i));
            }
        }
    }

    /**
     * Prints the current TaskList.
     */
    public void printCurrentList() {
        Ui.printCurrentList(this.list);
    }
}
