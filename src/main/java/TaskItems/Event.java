package TaskItems;

public class Event extends Todos {

    protected String type = "E";

    public Event(String name, boolean isMarked, String type) {
        super(name, isMarked, type);
    }

    public String getType() {
        return "[E]";
    }


}

