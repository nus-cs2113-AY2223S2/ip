package DukeMain;

public class Event extends Todos {

    protected String type = "E";

    Event(String name, boolean isMarked, String type) {
        super(name, isMarked, type);
    }

    public String getType() {
        return "[E]";
    }


}

