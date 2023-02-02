public class Event extends Task {
    protected String from;
    protected String to;

    public static Event toEvent(String instruction){
        int contentIdx = instruction.indexOf("/from");
        int fromIdx = instruction.indexOf("/to");

        if(contentIdx == -1 || fromIdx == -1) return null;

        String eventContent = instruction.substring(0, contentIdx);
        String eventFrom = instruction.substring(contentIdx + "/from ".length(), fromIdx);
        String eventTo = instruction.substring(fromIdx + "/to ".length());
        return new Event(eventContent, eventFrom, eventTo);
    }

    public Event(String description, String from, String to){
        super(description, 'E');
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
