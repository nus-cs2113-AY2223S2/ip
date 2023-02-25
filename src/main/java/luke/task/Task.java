package luke.task;

import luke.icon.CheckBox;
import luke.icon.Label;

/**
 * A <code>Task</code> object represents a task that can be added by the user.
 * It contains a String to indicate the taskName, a <code>Label</code> object, a <code>CheckBox</code> object and a
 * unique ID used to reference the task.
 */
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

    /** The serial number of the task. E.g if the task is the 3rd to be printed then its serial number is 3 */
    protected int serialNumber;

    protected Task(String name, int ID) {
        this.taskName = name;
        this.taskID = ID;
        this.checkBox = new CheckBox();
        this.label = new Label();
        this.isMarked = false;
        this.serialNumber = ID;
    }

    /**
     * Returns the name of the task.
     * @return taskName The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the ID of the task.
     * @return taskID the ID of the task.
     */
    public int getTaskID() {
        return this.taskID;
    }

    /**
     * Update the serial number of the task.
     * @param newSerialNumber The new serial number.
     */
    public void updateSerialNumber(int newSerialNumber) {
        this.serialNumber = newSerialNumber;
    }

    /**
     * Returns the label of the task.
     * @return label of the task.
     */
    public String getTaskLabel() {
        return this.label.getLabel();
    }

    /** Prints out the checkBox followed by the name of the task */
    public void printTaskName() {
        System.out.print(this.serialNumber + ". ");
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
