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

    public TaskList(String filepath) {
        try {
            list = Storage.stringToArraylistConverter(Storage.parseFileContentsToString(filepath));
        } catch (FileNotFoundException e) {
            System.out.println("Illegal file path");
        }
    }

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
            } else {
                throw new NullCommandException();
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
        } catch (NullCommandException e) {
            System.out.println("Sire, I am not trained to understand gibberish.");
        }
    }


    public void delete(String task) {
        String indexOfTask = task.substring(DELETE_INDEX);
        Ui.deleteSuccess(this.list, indexOfTask);
        this.list.remove(Integer.parseInt(indexOfTask) - 1);
        Ui.printListSize(this.list);
        Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
    }

    public void mark(String task) {
        String indexOfTask = task.substring(MARK_INDEX); // get the number of task to be marked
        Task taskToBeMarked = this.list.get(Integer.parseInt(indexOfTask) - 1); // convert from 1-based to 0-based
        taskToBeMarked.markTask();
        Ui.markSuccess(list, indexOfTask);
        Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
    }

    public void unmark(String task) {
        String indexOfTask = task.substring(UNMARK_INDEX);
        Task taskToBeUnmarked = this.list.get(Integer.parseInt(indexOfTask) - 1);
        taskToBeUnmarked.unmarkTask();
        Ui.unmarkSuccess(list, indexOfTask);
        Storage.dukeDataStorage(Storage.arraylistToStringConverter(this.list));
    }

    public void printTasksFound (String task) {
        String taskToBeFound = task.substring(FIND_INDEX);
        Ui.findSuccess();
        for (int i = 0; i < this.list.size(); ++i) {
            if (this.list.get(i).getDescription().contains(taskToBeFound)) {
                Ui.printTask(this.list.get(i));
            }
        }
    }

    public void printCurrentList() {
        Ui.printCurrentList(this.list);
    }
}
