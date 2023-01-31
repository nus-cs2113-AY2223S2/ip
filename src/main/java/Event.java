class Event extends Task {
    private final String startDateAndTime;
    private final String endDateAndTime;

    Event(boolean isDone, String taskDescription, String sdnt, String ednt) {
        super("E", isDone, taskDescription);
        this.startDateAndTime = sdnt;
        this.endDateAndTime = ednt;
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


}