class Task {
    private final boolean status;
    private final String taskDescription;

    Task(boolean s, String td) {
        this.status = s;
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
     if (!status) {
        checkbox = "[ ] ";
     }
     return checkbox + this.taskDescription;
    }

}