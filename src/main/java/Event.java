public class Event extends Todo {
    private final String startTime;
    private final String endTime;
    private static final String COMMAND = "event";
    private static final String TYPE = "E";
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event create(String command) {
        final String START_KEYWORD = "/from";
        final String END_KEYWORD = "/to";
        String[] parameters = command.split(" ");
        // Wrong command for adding an event
        if (!parameters[0].equals(Event.COMMAND)) {
            return null;
        }
        StringBuilder descBuilder = new StringBuilder();
        int keyword_index = parameters.length;
        for (int i = 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(START_KEYWORD)) {
                keyword_index = i;
                break;
            }
            descBuilder.append(parameters[i]);
            if (i != (parameters.length) - 1) {
                descBuilder.append(" ");
            }
        }
        String description = descBuilder.toString();
        // No start date/time given, cannot create a valid event object
        if (keyword_index >= (parameters.length + 1)) {
            return null;
        }
        StringBuilder startTimeBuilder = new StringBuilder();
        for (int i = keyword_index + 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(END_KEYWORD)) {
                keyword_index = i;
                break;
            }
            startTimeBuilder.append(parameters[i]);
            if (i != (parameters.length) - 1) {
                startTimeBuilder.append(" ");
            }
        }
        String startTime = startTimeBuilder.toString();
        // No end date/time given, cannot create a valid event object
        if (keyword_index >= (parameters.length + 1)) {
            return null;
        }
        StringBuilder endTimeBuilder = new StringBuilder();
        for (int i = keyword_index + 1; i < parameters.length; i += 1) {
            endTimeBuilder.append(parameters[i]);
            if (i != (parameters.length) - 1) {
                endTimeBuilder.append(" ");
            }
        }
        String endTime = endTimeBuilder.toString();
        return new Event(description, startTime, endTime);
    }

    private String getStartTime() {
        return this.startTime;
    }

    private String getEndTime() {
        return this.endTime;
    }

    @Override
    protected String getType() {
        return Event.TYPE;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.getStartTime(), this.getEndTime());
    }
}
