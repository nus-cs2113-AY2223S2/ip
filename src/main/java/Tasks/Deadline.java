package Tasks;

import Tasks.Task;

public class Deadline extends Task {

    protected String by;
    protected String symbol = "[D]";

    public String getDescription() {
        return super.getDescription();
    }

    public String getSymbol() {
        return symbol;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.description = description + "(By:" + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() ;
    }
    @Override
    public String toFile() {
        return this.getStatusIcon() + " : " +"D"+ " : " + this.description;
    }
}
