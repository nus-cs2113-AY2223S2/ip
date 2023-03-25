package TaskItems;

public class Event extends Todos {

    protected String type = "E";

    public Event(String name, boolean isMarked, String type) {
        super(name, isMarked, type);
    }

    /**
     * checks what type of task item it is
     * @return a string "[E]" indicating that it is an event type
     */
    public String getType() {
        return "[E]";
    }


}

