public class Deadline extends Task {
    protected String by;

    public Deadline(String name, boolean isDone, int taskId, String by) {
        super(name, isDone, taskId);
        this.by = by;
    }

    public String toString() {
        if(this.getIsDone() == true) {
            return " [D][X]" + this.getName() + " (by: " + this.by + ")";
        } else {
            return " [D][ ]" + this.getName() + " (by: " + this.by + ")";
        }
    }

    public void print() {
        if (this.isIsDone() == false) {
            System.out.println((this.getTaskId() + 1) + "." + this.toString());
        } else {
            System.out.println((this.getTaskId() + 1) + "." + this.toString());
        }
    }
}
