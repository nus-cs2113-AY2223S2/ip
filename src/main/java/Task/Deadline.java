package Task;

public class Deadline extends Task {
    // deadline day
    protected String deadlineDay;

    /**
     * Constructor
     *
     * @param description deadline description
     * @param deadlineDay deadline day
     * 
     */
    public Deadline(String description, String deadlineDay) {
        super(description);
        this.deadlineDay = deadlineDay;
    }

    /**
     * Constructor
     *
     * @param description deadline description
     * @param deadlineDay deadline day
     * @param isDone      whether the task is done or not
     * 
     */
    public Deadline(String description, String deadlineDay, boolean isDone) {
        super(description, isDone);
        this.deadlineDay = deadlineDay;
    }

    /**
     * toString method
     *
     * @return string description
     * 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.deadlineDay + ")";
    }

    /**
     * toString method when saving in file
     *
     * @return string description to be saved in file
     * 
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | "
                + deadlineDay;
    }

    /**
     * toString method
     *
     * @param line input string from file
     * @return deadline item
     * 
     */
    public static Deadline fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        String deadlineDay = parts[3];
        return new Deadline(description, deadlineDay, isDone);
    }

}
