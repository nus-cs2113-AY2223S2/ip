package task;
public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    private String getStartEvent() {
        return this.description.split("/from")[1].split("/")[0];
    }
    private String getEndEvent() {
        return this.description.split("/from")[1].split("/to")[1];
    }
    private String getTask() {
        return this.description.split("/from")[0].split(" ",2)[1];
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + getTask() + "(from:" + getStartEvent() + "to:" + getEndEvent()+ ")");
    }
}
