package duke.command;

import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;

import duke.task.Task;
import duke.DukeException;

public class TodoList {

    private static final int MAXLISTNUM = 100;
    private static final String SPLITTER = "    ____________________________________________________________";
    
    private ArrayList<Task> tasks = new ArrayList<>();
    private int listnum;
    private Storage storage;

    public TodoList(Storage storage) throws IOException, DukeException {
        this.storage = storage;
        this.tasks = storage.getTasks();
        this.listnum = storage.getListnum();
    }

    public TodoList() throws IOException, DukeException {
        listnum = 0;
    }

    public int getListnum() {
        return listnum;
    }

    public void setListnum(int listnum) {
        this.listnum = listnum;
    }

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

    public void showList() {
        System.out.println(SPLITTER);
        for(int i = 0; i < listnum; i++) {
            Ui.showTask(i + 1, tasks.get(i), 4);
        }
        System.out.println(SPLITTER);
        System.out.println();
    }

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
