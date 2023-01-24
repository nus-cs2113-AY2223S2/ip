public class Task {
    /** The name of a task */
    private String taskName;
    
    /** A boolean variable to indicate if a task have been marked */
    private boolean isMarked;

    /** Check box to display the state of the task */
    private CheckBox checkBox;

    /** The unique ID assgied to this task */
    private int taskID;

    public Task(String name, int ID) {
        this.taskName = name;
        this.taskID = ID;
        this.checkBox = new CheckBox();
        this.isMarked = false;
    }

    /**
     * Returns the name of the task.
     * @return taskName
     */
    public String getTaskName() {
        return taskName;
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
