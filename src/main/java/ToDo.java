class ToDo extends Task {

    ToDo(boolean isDone, String taskDescription) {
        super("T", isDone, taskDescription);
    }

    ToDo(String[] parameters) {
        this(parameters[1].equals("true") ? true : false, parameters[2]);
    }

    public Task mark() {
        return new ToDo(true, super.taskDescription);
    }

    public Task unmark() {
        return new ToDo(false, super.taskDescription);
    }

    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase();
    }

}