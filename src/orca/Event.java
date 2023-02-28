package orca;

public class Event extends Task {
    protected String from;
    protected String to;

    Event(String userInput, int startIdx) throws OrcaException {
        int fromIndex = userInput.indexOf(" /from ");
        int toIndex = userInput.indexOf(" /to ");
        try {
            this.description = userInput.substring(6, fromIndex);
            this.from = userInput.substring(fromIndex + 7, toIndex);
            this.to = userInput.substring(toIndex + 5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("The description/from/to of an event cannot be empty.");
        }
        this.type = "E";
    }

    Event(String description, String from, String to, boolean isDone) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.isDone = isDone;
        this.type = "E";
    }


    /**
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
