package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;

import static duke.ui.Ui.printBorder;

public class TaskList {
    public ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }
    public void removeTask(int index) {
        tasks.remove(index);
    }

    //print list of duke.tasks
    public static void printList(TaskList tasks){
        printBorder();

        int counter = 1;
        for (Task t : tasks.getTasks()) {
            if (t == null) {
                break;
            } else {
                System.out.println(counter + ". " + t);
                counter++;
            }
        }
        System.out.println("You have " + tasks.getSize() + " tasks in your list.");
        printBorder();
    }
}
