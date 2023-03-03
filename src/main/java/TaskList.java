import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

/*
 * TaskList is the class of Duke which contains the list of tasks and methods to makes changes to the list
 * It contains only the ArrayList taskList
 */
public class TaskList {
    private static List<Task> taskList = new ArrayList<Task>();

    /*
     * Writes file data from file scanned from file to taskList object
     * Uses <code>Scanner</code> to read contents from file, splits content into
     * String[]
     * 
     * @param File file with contents of previously created tasklist
     * 
     * @throws IOException if file data is not formatted as specified
     */
    public void Tasklist(File file) {
        String data;
        try {
            if (!file.createNewFile()) {
                Scanner fileData = new Scanner(file);
                while (fileData.hasNext()) {
                    data = fileData.nextLine();
                    String[] inputArgs = data.split("|");
                    addFileData(inputArgs);
                }
                fileData.close();
            }
        } catch (IOException e) {
            System.out.print("\nError getting file data");
        }
        System.out.println("These are the tasks from your file:\n");
    }

    /*
     * Formats file data retrieved by Tasklist, parses String[] and adds them to
     * taskList as Todo, Event or Deadline subclasses
     */
    private static void addFileData(String[] inputArgs) {
        Task newTask;
        String command = inputArgs[0];
        boolean taskStatus = Boolean.parseBoolean(inputArgs[1]);
        switch (command) {
            case "T":
                newTask = new Todo(inputArgs[2]);
                break;
            case "D":
                newTask = new Deadline(inputArgs[2]);
                break;
            case "E":
                newTask = new Event(inputArgs[2]);
                break;
            default:
                throw new IllegalStateException("File contents are invalid");
        }
        if (taskStatus) {
            newTask.markAsDone();
        }
        taskList.add(newTask);
    }

    public static List<Task> returnTaskList() {
        return taskList;
    }

    public void addEvent(String taskName) {
        Event t = new Event(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void addTodo(String taskName) {
        Todo t = new Todo(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void addDeadline(String taskName) {
        Deadline t = new Deadline(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void changeTaskState(boolean doneState, Integer index) {
        if (index > taskList.size()) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            if (doneState) {
                taskList.get(index).markAsDone();
            } else {
                taskList.get(index).markAsUndone();
            }
        }
    }

    public void delete(int index) {
        if (index > taskList.size()) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            System.out.printf("Noted. I've removed this task:" +
                    taskList.get(index).toString());
            taskList.remove(index);
            System.out.println(String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
        }
    }

    /*
     * Main list method, lists all contents of tasklist
     */
    public void list() {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            Integer i = 0;
            for (Task task : taskList) {
                System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                i++;
            }
        }
    }

    /*
     * Lists overdue deadline subclass objects in tasklist
     */
    public void listOverdue() {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the overdue deadlines in your list:");
            Integer i = 0;
            for (Task task : taskList) {
                if (task instanceof Deadline & ((Deadline) task).isOverdue) {
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
        if (taskList.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            Integer i = 0;
            for (Task task : taskList) {
                if (task.description.contains(keyword)) {
                    System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                    i++;
                }
            }
        }
    }
}
