package duke;
/**
 * Deadline type of task. Has a deadline to do task by.
 */
public class Deadline extends Todo {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return this.by;
    }
    /**
     * Returns icon matching status type
     * @return icon that depicts status of done
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    /**
     * Returns icon matching deadline type
     * @return icon of type deadline
     */
    @Override
    public String getTypeIcon() {
        return ("[D]"); // mark done task with X
    }
    public void setBy(String s) {
        this.by = s;
    }
    @Override
    public String getInfo() {
        return by;
    }
}
