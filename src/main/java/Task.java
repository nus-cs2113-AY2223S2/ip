public class Task {
    private String content;
    private boolean finished;

    public Task() {
        this("", false);
    }

    public Task(String content) {
        this(content, false);
    }

    public Task(String content, boolean finished) {
        this.content = content;
        this.finished = finished;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean isFinished() {
        return finished;
    }

    public void printTask() {
        System.out.println(this);
    }

    public void mark() {
        if (!finished) {
            finished = true;
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t " + this);
        } else {
            System.err.println("\t The task is already marked!");
        }
    }

    public void unmark() {
        if (finished) {
            finished = false;
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.println("\t " + this);
        } else {
            System.err.println("\t This task is already not marked!");
        }
    }

    @Override
    public String toString() {
        return "[" + (finished ? "X" : " ") + "]" + " " + content;
    }
}
