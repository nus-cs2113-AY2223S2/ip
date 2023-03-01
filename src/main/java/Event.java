public class Event extends Task {
    String from;
    String to;

    Event(String content, String start, String end) {
        super(content);
        this.from = start; //start format: Date+time
        this.to = end; //end format: (Date+)time
    }

    @Override
    public String toString() {
        String returnStr = "[E]";
        if (getIsDone()) {
            returnStr = returnStr.concat("[O] ");
        } else {
            returnStr = returnStr.concat("[ ] ");
        }

        return returnStr + getContents() + " / from " + from + " / to " + to;
    }
}
