public class Events extends Task {
    protected String from;
    protected String to;
    Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + this.getStatusAndDescription());
    }
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

    @Override
    public String getStatusAndDescription() {
        return "[E]" + super.getStatusAndDescription() + "(from: " + this.getFrom() + " to:" + this.getTo() + ")";
    }

}
