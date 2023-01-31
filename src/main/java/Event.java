public class Event extends Todo {
    protected String by;
    protected String end;
    public Event(String description, String by, String end) {
        super(description);
        this.by = by;
        this.end = end;
    }

    public String getBy() {
        return by;
    }

    public String getEnd() {
        return end;
    }

    public void setBy(String time) {
        by = time;
    }

    public void setEnd(String time) {
        end = time;
    }

    public void print() {
        System.out.println("    _________________________________________");
        System.out.println("    " + "added: " + description + " (from: " + by + ", to: " + end + ")");
        System.out.println("    _________________________________________");
        System.out.println("    ");
    }

    public void printInList() {
        if (isDone) {
            System.out.println(" " + "[E][X] " + description + " (from: " + by + ", to: " + end + ")");
        } else {
            System.out.println(" " + "[E][ ] " + description + " (from: " + by + ", to: " + end + ")");
        }
    }
}
