public class Task {
    /** The description of the task entered by the user. */
    protected String description;
    /** The state of the task, whether it is done or not. */
    protected boolean isDone;
    /** The index of the task in the list */
    protected int index;
    /** The type of task the following task is
     * Possible values: TODO, DEADLINE, EVENT
     * */
    protected TypeOfTask typeOfTask;

    public Task(String description, int index, TypeOfTask typeOfTask) {
        this.description = description;
        this.index = index;
        this.isDone = false;
        this.typeOfTask = typeOfTask;
    }

    /**
     * Returns the symbol to be printed depends on whether the task is marked as done or not.
     * @return The symbol whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the state of the task: whether is it done or not.
     * @return isDone The state of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Toggles the state of the task between done and not done.
     */
    public void switchIsDone() {
        isDone = !isDone;
    }

    /**
     * Returns the description of the task that the user entered.
     * @return description The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the index of the task in the list
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the type of task
     * @return typeOfTask
     */
    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    /** Returns the symbol to be printed based on the type of task
     * @return The symbol depending on whether it's a TODO, DEADLINE, or EVENT task
     */
    public String getStatusForTypeOfTask() {
        if (typeOfTask.equals(TypeOfTask.TODO)) {
            return "T";
        } else if (typeOfTask.equals(TypeOfTask.DEADLINE)) {
            return "D";
        } else if (typeOfTask.equals(TypeOfTask.EVENT)) {
            return "E";
        }
        return "ERROR";
    }
}

