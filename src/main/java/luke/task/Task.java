package luke.task;

import luke.icon.CheckBox;
import luke.icon.Label;

public class Task {
    /** The name of a task */
    private String taskName;
    
    /** A boolean variable to indicate if a task have been marked */
    private boolean isMarked;

    /** Check box to display the state of the task */
    protected CheckBox checkBox;

    /** Label to indicate the type of task */
    protected Label label;

    /** The unique ID assgied to this task */
    private int taskID;


    public Task(String name, int ID) {
        this.taskName = name;
        this.taskID = ID;
        this.checkBox = new CheckBox();
        this.label = new Label();
        this.isMarked = false;
    }

    /**
     * Returns the name of the task.
     * @return taskName The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the ID of the task.
     * @return taskID the ID of the task.
     */
    public int getTaskID() {
        return taskID;
    }

    /** Prints out the checkBox followed by the name of the task */
    public void printTaskName() {
        checkBox.printCheckBox();
        System.out.println(taskName);
    }

    /** Marks the task as complete */
    public void markTask() {
        if (!isMarked) {
            isMarked = true;
            checkBox.markCheckBox();
        }
    }

    /** Unmarks the task */
    public void unmarkTask() {
        if (isMarked) {
            isMarked = false;
            checkBox.unmarkCheckBox();
        }
    }
}
