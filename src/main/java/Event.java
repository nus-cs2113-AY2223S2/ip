class Event extends Task {
    private final String startDateAndTime;
    private final String endDateAndTime;

    Event(boolean isDone, String taskDescription, String startDateAndTime, String endDateAndTime) {
        super("E", isDone, taskDescription);
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
    }

    Event(String[] parameters) {
        this(parameters[1].equals("true") ? true : false, parameters[2],
                parameters[3], parameters[4]);
    }

    public Task mark() {
        return new Event(true, super.taskDescription,
                this.startDateAndTime, this.endDateAndTime);
    }

    public Task unmark() {
        return new Event(false, super.taskDescription,
                this.startDateAndTime, this.endDateAndTime);
    }
    public String toString() {
        String formattedEventDetails = "(from: " + this.startDateAndTime +
                "to: " + this.endDateAndTime + ")";
        return super.toString() + formattedEventDetails;
    }

    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase() + "," + this.startDateAndTime +
                "," + this.endDateAndTime;
    }


}