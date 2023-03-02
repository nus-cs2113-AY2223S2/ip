class ToDo extends Task {

    ToDo(boolean isDone, String taskDescription) {
        super("T", isDone, taskDescription);
    }

    ToDo(String[] parameters) {
        this(parameters[1].equals("true") ? true : false, parameters[2]);
    }

    /**
     * Returns a new Task that is marked
     */
    public Task mark() {
        return new ToDo(true, super.taskDescription);
    }

    /**
     * Returns a new Task that is unmarked
     */
    public Task unmark() {
        return new ToDo(false, super.taskDescription);
    }

    /**
     * Returns a string that represents the ToDo Task
     * This string is stored in the database
     *
     * @return Formatted String that represents the ToDo Task
     */
    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase();
    }

}