class Deadline extends Task {
    private final String deadline;

    Deadline(boolean isDone, String taskDescription, String d) {
        super("D", isDone, taskDescription);
        this.deadline = d;
    }

    Deadline(String[] parameters) {
        this(parameters[1].equals("true") ? true : false,
                parameters[2], parameters[3]);
    }

    public Task mark() {
        return new Deadline(true, super.taskDescription, this.deadline);
    }

    public Task unmark() {
        return new Deadline(false, super.taskDescription, this.deadline);
    }

    @Override
    public String toString() {
        String formattedDeadline = "(by: " + this.deadline + ")";
        return super.toString() + formattedDeadline;
    }

    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase() + "," + this.deadline;
    }
}