public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = TaskType.TODO;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        switch (this.taskType) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        }
        return null;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
