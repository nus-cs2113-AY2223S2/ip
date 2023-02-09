package duke;

public class Deadline extends Todo {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String time) {
        by = time;
    }

    public void print() {
        System.out.println("    _________________________________________");
        System.out.println("    " + "added: " + description + " (by " + by + ")");
        System.out.println("    _________________________________________");
        System.out.println("    ");
    }

    public void printInList() {
        if (isDone) {
            System.out.println(" " + "[D][X] " + description + " (by " + by + ")");
        } else {
            System.out.println(" " + "[D][ ] " + description + " (by " + by + ")");
        }
    }
}

