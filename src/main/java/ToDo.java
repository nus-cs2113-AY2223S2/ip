public class ToDo extends Task {
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public ToDo(String description) {
        super(description);
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String status;
        if (isDone) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[T]" + status + " " + super.toString();
    }
}
