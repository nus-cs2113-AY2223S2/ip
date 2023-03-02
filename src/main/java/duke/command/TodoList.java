package duke.command;

import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;

import duke.task.Task;
import duke.DukeException;

/**
 * Manages all tasks.
 */
public class TodoList {

    private static final int MAXLISTNUM = 100;
    private static final String SPLITTER = "    ____________________________________________________________";
    
    private ArrayList<Task> tasks = new ArrayList<>();
    private int listnum;
    private Storage storage;

    /**
     * Constructor of TodoList. It will get data from <code>Storage</code>.
     * @param storage <code>Storage</code> object containing <code>ArrayList</code> for all tasks.
     * @throws IOException If any <code>IOException</code> occurs in file I/O.
     * @throws DukeException If any <code>DukeException</code> occurs in file I/O.
     */
    public TodoList(Storage storage) throws IOException, DukeException {
        this.storage = storage;
        this.tasks = storage.getTasks();
        this.listnum = storage.getListnum();
    }

    /**
     * Constructor for TodoList. It will construct a TodoList object with no data.
     * @throws IOException If any <code>IOException</code> occurs in file I/O.
     * @throws DukeException If any <code>DukeException</code> occurs in file I/O.
     */
    public TodoList() throws IOException, DukeException {
        listnum = 0;
    }

    /**
     * Return number of items in task list.
     * @return Number of items in task list.
     */
    public int getListnum() {
        return listnum;
    }

    /**
     * Set number of items in task list.
     * @param listnum Number of items in task list.
     */
    public void setListnum(int listnum) {
        this.listnum = listnum;
    }

    /**
     * Add one task to the end of the task list.
     * @param task Task to be added.
     * @return 0 if exception occurs and 1 if task is successfully added.
     * @throws DukeException If any <code>DukeException</code> occurs.
     */
    public int addItem(Task task) throws DukeException {
        if(listnum < MAXLISTNUM) {
            tasks.add(task);
            listnum++;
            System.out.println(SPLITTER);
            System.out.println("    " + "Got it. I've added this task:");
            // System.out.println("      " + task.toString());
            Ui.showTask(task, 6);
            System.out.println("    " + "Now you have " + listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            
            // save to file
            try {
                storage.save();
            } catch(IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        } else {
            throw new DukeException("This task does not exist!");
        }
    }

    /**
     * Delete one task according to its number in list.
     * @param num Number of the item to be deleted in task list.
     * @return 0 if exception occurs and 1 if the task is successfully deleted.
     * @throws DukeException If any <code>DukeException</code> occurs, e.g. 
     * deleting a task that does not exist.
     */
    public int deleteItem(int num) throws DukeException {
        if(tasks.size() > num) {
            System.out.println(SPLITTER);
            System.out.println("    " + "Noted. I've removed this task:");
            // System.out.println("      " + tasks.get(num));
            Ui.showTask(tasks.get(num), 6);
            System.out.println("    " + "Now you have " + --listnum + " task" + ((listnum>1) ? "s" : "") + " in the list.");
            System.out.println(SPLITTER);
            System.out.println();
            tasks.remove(num);

            // save to file
            try {
                storage.save();
            } catch(IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        } else {
            throw new DukeException("This task does not exist!");
        }
    }

    /**
     * Mark a task as done/not done.
     * @param num Number of the task in the list.
     * @param mark 1 for done and 0 for not done.
     * @return 0 if any exception occurs and 1 if the task is successfully marked.
     * @throws DukeException If any <code>DukeException</code> occurs, e.g. 
     * marking a task that does not exist.
     */
    public int markItem(int num, boolean mark) throws DukeException {
        if(num < listnum && num >= 0) {
            tasks.get(num).mark(mark);
            if(mark) {
                System.out.println(SPLITTER);
                System.out.println("    Nice! I've marked this task as done:");
            } else {
                System.out.println(SPLITTER);
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            // System.out.print("      ");
            // System.out.println(tasks.get(num));
            Ui.showTask(tasks.get(num), 6);
            System.out.println(SPLITTER);
            System.out.println();

            // save to file
            try {
                storage.save();
            } catch(IOException e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        } else {
            throw new DukeException("Index out of bound!");
        }
    }

    /**
     * Show all tasks in the list.
     */
    public void showList() {
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++) {
            Ui.showTask(i + 1, tasks.get(i), 4);
        }
        System.out.println(SPLITTER);
        System.out.println();
    }

    /**
     * Used when executing "find" command with "/by". It will find all tasks
     * that ends before <code>endTimeBefore</code> and print them out.
     * @param endTimeBefore Time provided.
     * @throws DukeException If any <code>DukeException</code> occurs.
     */
    public void findEndTimeBefore(LocalDateTime endTimeBefore) throws DukeException {
        Ui.showLine();
        System.out.println("     Here are the task(s) ending before " + 
                            endTimeBefore.format(Task.printFormatter) + " in your list: ");
        int counter = 0;
        for(Task task : tasks) {
            if(!task.haveValidDate()) {
                continue;
            }
            if(task.getEndTime().isBefore(endTimeBefore) || task.getEndTime().isEqual(endTimeBefore)) {
                Ui.showTask(++counter, task, 5);
            }
        }
        System.out.println("     (Total " + counter + ")");
        Ui.showLine();
        System.out.println();
    }

    /**
     * Used when executing "find" command without "/by". It will find all tasks
     * that has <code>pattern</code> substring in its description.
     * @param pattern Pattern string provided.
     * @throws DukeException If any <code>DukeException</code> occurs.
     */
    public void findDesc(String pattern) throws DukeException {
        Ui.showLine();
        System.out.println("     Here are the matching task(s) in your list: ");
        int counter = 0;
        for(Task task : tasks) {
            if(task.getDescription().contains(pattern)){
                Ui.showTask(++counter, task, 5);
            }
        }
        System.out.println("     (Total " + counter + ")");
        Ui.showLine();
        System.out.println();
    }
}
