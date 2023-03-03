package Tasks;

public class Event extends Task {
    protected String from;
    protected String to;
    protected String symbol = "[E]";

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.description = description + "(From:" + this.from + "To:" + this.to + ")";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the string in a format to be presented to the user
     * @return the formatted string output
     */
    @Override
    public String toString(){
        return "[E]" +  super.getStatusIcon() + " " + super.getDescription();
    }

    /**
     * Returns the string in a format to be stored in the text file
     * @return the formatted string to be stored in the text file
     */
    @Override
    public String toFile() {
        return this.getStatusIcon() + " : " + "E" + " : " + this.description;
    }
}
