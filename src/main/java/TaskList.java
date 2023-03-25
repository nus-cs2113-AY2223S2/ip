import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/*
 * TaskList is the class of Duke which contains the list of tasks and methods to makes changes to the list
 * It contains only the ArrayList tasks
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<Task>();

    /*
     * Writes file data from file scanned from file to tasks object
     * Uses <code>Scanner</code> to read contents from file, splits content into
     * String[]
     * 
     * @param File file with contents of previously created tasklist
     * 
     * @throws IOException if file data is not formatted as specified
     */
    public void Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    /*
     * Formats file data retrieved by Tasklist, parses String[] and adds them to
     * tasks as Todo, Event or Deadline subclasses
     */

    public List<Task> returnTaskList() {
        return tasks;
    }

    public void clearTaskList() {
        tasks.clear();
        System.out.println("Task list cleared!");
    }

    public void addEvent(String taskName) {
        Event t = new Event(taskName);
        tasks.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", tasks.size()));
    }

    public void addTodo(String taskName) {
        Todo t = new Todo(taskName);
        tasks.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", tasks.size()));
    }

    public void addDeadline(String taskName) {
        Deadline t = new Deadline(taskName);
        tasks.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", tasks.size()));
    }

    public void changeTaskState(boolean doneState, Integer index) {
        if (index > tasks.size() || index < 1) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            if (doneState) {
                tasks.get(index).markAsDone();
            } else {
                tasks.get(index).markAsUndone();
            }
        }
    }

    public void delete(int index) {
        if (index > tasks.size() || index < 1) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            System.out.printf("Noted. I've removed this task:" +
                    tasks.get(index).toString());
            tasks.remove(index);
            System.out.println(String.format("\nNow you have %d tasks in the list.\n", tasks.size()));
        }
    }

    /*
     * Main list method, lists all contents of tasklist
     */
    public void list() {
        if (tasks.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            Integer i = 0;
            for (Task task : tasks) {
                System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                i++;
            }
        }
    }

    /*
     * Lists overdue deadline subclass objects in tasklist
     */
    public void listOverdue() {
        if (tasks.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the overdue deadlines in your list:");
            Integer i = 0;
            for (Task task : tasks) {
                if (task instanceof Deadline && ((Deadline) task).isOverdue) {
                    System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                    i++;
                }
            }
        }
    }

    /*
     * lists all tasks in task list containing <code>keyword</code>
     * 
     * @param keyword to be searched for
     */
    public void find(String keyword) {
        if (tasks.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            Integer i = 0;
            for (Task task : tasks) {
                if (task.description.contains(keyword)) {
                    System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                    i++;
                }
            }
        }
    }
}
