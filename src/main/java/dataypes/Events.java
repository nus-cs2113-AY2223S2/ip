package dataypes;

public class Events extends Task implements TaskFileHandler {
    protected String from;
    protected String to;
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }
    public Events(){}
//    public void printAddedEvent() {
//        System.out.println("\tGot it. I've added this task:\n");
//        System.out.println("\t\t" + this.getDescription());
//    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getStatusAndDescription() {
        return "[E]" + super.getStatusAndDescription() + "(from: " + this.getFrom() + " to:" + this.getTo() + ")";
    }
    public String enCode() {
        return "E # " + super.enCode() + " # " + this.from + " # " + this.to;
    }
}
