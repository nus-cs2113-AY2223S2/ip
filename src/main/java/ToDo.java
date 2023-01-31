class ToDo extends Task {

    ToDo(boolean isDone, String taskDescription) {
        super("T", isDone, taskDescription);
    }

    public Task mark() {
        return new ToDo(true, super.taskDescription);
    }

    public Task unmark() {
        return new ToDo(false, super.taskDescription);
    }

}