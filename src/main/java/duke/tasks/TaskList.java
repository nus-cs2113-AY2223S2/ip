/**
 * TaskList class that contains an ArrayList of Task objects.
 */

package duke.Tasks;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public ArrayList<Task> getList() {
        return list;
    }
    public void setList(ArrayList<Task> list) {
        this.list = list;
    }
    public Task getTask(int index) {
        return list.get(index);
    }
    public int getIndex (Task task) {
        return list.indexOf(task);
    }
    public void adder(Task task) {
        list.add(task);
    }

}
