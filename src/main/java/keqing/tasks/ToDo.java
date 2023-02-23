package keqing.tasks;

public class ToDo extends Task {
    public static final String sign = "T";

    public ToDo(String description) {
        super(description);
        taskCount += 1;
    }

    @Override
    public String getTaskType() {
        return sign;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}