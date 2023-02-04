public class Event extends Deadline{
    protected String by;
    public Event(String description, String by) {
        super(description, by);
    }

    @Override
    public String getType() {
        return "event";
    }

}
