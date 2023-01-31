public class Deadline extends Todo {
    private String by;
    public Deadline(String description, String by)
    {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return this.by;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }
    public String getTypeIcon() {
        return ("[D]"); // mark done task with X
    }
    public void setBy(String s) {
        this.by = s;
    }
}
