class Event extends Task {
    /** String representation of start date and time*/
    private final String startDateAndTime;
    /** String representation of end date and time*/
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

    /**
     * Returns a new Task that is marked
     */
    public Task mark() {
        return new Event(true, super.taskDescription,
                this.startDateAndTime, this.endDateAndTime);
    }

    /**
     * Returns a new Task that is unmarked
     */
    public Task unmark() {
        return new Event(false, super.taskDescription,
                this.startDateAndTime, this.endDateAndTime);
    }
    @Override
    public String toString() {
        String formattedEventDetails = "(from: " + this.startDateAndTime +
                "to:" + this.endDateAndTime + ")";
        return super.toString() + " " +formattedEventDetails;
    }

    /**
     * Formats details in an Event to be stored in the database
     *
     * @return Formatted String that represents the Event
     */
    public String toStringForDatabase() {
        return super.CommonFieldsFor_toStringForDatabase() + "," + this.startDateAndTime +
                "," + this.endDateAndTime;
    }


}