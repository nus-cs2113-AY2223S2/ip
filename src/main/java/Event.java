public class Event extends Todos{

    protected String type = "E";
    Event(String name, boolean marked, String type) {
        super(name, marked, type);
    }
    public String getType() {
        return "[E]";
    }





}

