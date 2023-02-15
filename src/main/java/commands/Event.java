package commands;
public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    private String startEvent = this.description.split("/from")[1].split("/")[0];
    private String endEvent = this.description.split("/from")[1].split("/to")[1];
    private String task = this.description.split("/from")[0].split(" ",2)[1];

    @Override
    public String toString() {
        return ("[E]" + super.toString() + task + "(from:" + startEvent + "to:" + endEvent+ ")");
    }
}
