public class Event extends Todo {
    private static final String COMMAND = "event";
    private static final String END_KEYWORD = "/to";
    private static final String START_KEYWORD = "/from";
    private static final String TYPE = "E";
    private final String endTime;
    private final String startTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private static String getDescriptionOf(String command) {
        String[] parameters = command.split(" ");
        StringBuilder descBuilder = new StringBuilder();
        for (int i = 1; i < parameters.length; i += 1) {
            if (parameters[i].equals(Event.START_KEYWORD)) {
                break;
            }
            if (i != 1) {
                descBuilder.append(" ");
            }
            descBuilder.append(parameters[i]);
        }
        String description = descBuilder.toString();
        return description;
    }

    private String getEndTime() {
        return this.endTime;
    }

    private static String getEndTimeOf(String command) {
        int endDateFirstIndex = command.indexOf(Event.END_KEYWORD)
                + Event.END_KEYWORD.length();
        return command.substring(endDateFirstIndex).trim();
    }

    private String getStartTime() {
        return this.startTime;
    }

    private static String getStartTimeOf(String command) {
        int startDateFirstIndex = command.indexOf(Event.START_KEYWORD)
                + Event.START_KEYWORD.length();
        int startDateLastIndex = command.indexOf(Event.END_KEYWORD) - 1;
        return command.substring(startDateFirstIndex, startDateLastIndex).trim();
    }

    @Override
    protected String getType() {
        return Event.TYPE;
    }

    public static Event create(String command) {
        String[] parameters = command.trim().split(" ");
        // Wrong command for adding an event
        if (!parameters[0].equals(Event.COMMAND)) {
            return null;
        }
        String description = Event.getDescriptionOf(command);
        String startTime = Event.getStartTimeOf(command);
        String endTime = Event.getEndTimeOf(command);
        return new Event(description, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.getStartTime(), this.getEndTime());
    }
}
