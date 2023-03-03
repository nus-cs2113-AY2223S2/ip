package duke;

import duke.commands.Todo;
import duke.commands.Task;
import duke.commands.Deadline;
import duke.commands.Event;

import duke.commands.Datetime;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int listSize;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
        this.listSize = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        this.listSize = tasks.size();
    }

    public int getSize() {
        return listSize;
    }

    public String getDescription(int index) {
        return taskList.get(index).toString();
    }

    public void markThisTask(int index) {
        taskList.get(index).markAsDone();
    }

    public void unMarkThisTask(int index) {
        taskList.get(index).unmark();
    }

    public String getTask(int index) {
        return taskList.get(index).toString();
    }

    public void deleteThisTask(int index) {
        taskList.remove(index);
        listSize--;
    }

    public void addToDo(String description) {
        Todo newtodo = new Todo(description);
        taskList.add(newtodo);
        listSize++;
    }
    
    /**
     * add a event or deadline
     * 
     * @param description the description (apart from time) of the task
     * @param dateAndTime the time information of the task
     * @param tasktype the type of the task
     */
    public void addTaskWithTime(String description, Datetime dateAndTime, String tasktype) {
        if (tasktype.equals("deadline")) {
            Deadline newDeadline = new Deadline(description, dateAndTime);
            taskList.add(newDeadline);
        } else {
            Event newEvent = new Event(description, dateAndTime);
            taskList.add(newEvent);
        }
        listSize++;
    }

    public String latesttask() {
        return taskList.get(listSize - 1).toString();
    }

    public ArrayList<Task> fullList() {
        return this.taskList;
    }

    /**
     * search for relevant tasks
     * 
     * @param keyword the keyword used for searching
     * @return A arrarList containing details of relevant tasks
     */
    public ArrayList<Task> searchRelaventTask(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for(Task cur: taskList) {
            if(cur.getTaskDiscription().indexOf(keyword) != -1) result.add(cur);
        }

        return result;
    }

}
