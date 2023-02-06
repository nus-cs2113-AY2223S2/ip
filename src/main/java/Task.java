public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String markAsDone() throws DukeException {

        if (this.isDone == true) {
            //throw new DukeException("Task is already marked as done.");
            throw new DukeException();
        }
        this.isDone = true;
        return "X";
    }

    public String unmarkAsDone() throws DukeException {
        if (this.isDone == false) {
            throw new DukeException();

        }

        this.isDone = false;
        return " ";
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] ";
    }
}