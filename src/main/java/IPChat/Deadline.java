package IPChat;

public class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super(description);
        if (by.contains("/by")) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String saveStuff () {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "deadline" + description + "/by" + by + " | " + save;
    }
}
