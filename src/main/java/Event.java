public class Event extends Task {
    protected String from;
    protected String to;

    Event(String userInput, int startIdx) {
        int fromIndex = userInput.indexOf(" /from ");
        int toIndex = userInput.indexOf(" /to ");
        this.description = userInput.substring(6, fromIndex);
        this.from = userInput.substring(fromIndex + 7, toIndex);
        this.to = userInput.substring(toIndex + 5);
        this.type = "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
