class Task {
    private final boolean isDone;
    private final String taskDescription;

    Task(boolean id, String td) {
        this.isDone = id;
        this.taskDescription = td;
    }

    public Task mark() {

        return new Task(true, this.taskDescription);
    }

    public Task unmark() {

        return new Task(false, this.taskDescription);
    }

    @Override
    public String toString() {
        String checkbox = "[X] ";
     if (!isDone) {
        checkbox = "[ ] ";
     }
     return checkbox + this.taskDescription;
    }

}