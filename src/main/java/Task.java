public class Task {
    protected static final String OPEN_SQUARE_BRACKET = "[";
    protected static final String CLOSE_SQUARE_BRACKET = "]";
    protected static final String WHITESPACE = " ";

    protected String taskName;
    protected boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        if (isCompleted) {
            return "✓";
        }
        return " ";
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void setIncomplete() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + getTaskStatus() + CLOSE_SQUARE_BRACKET
                + WHITESPACE + getTaskName();
    }
}