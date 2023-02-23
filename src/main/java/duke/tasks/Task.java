package duke.tasks;

public class Task {
    private final String name;
    private boolean isCompleted;
    private final TaskEnum type;
    private static final String checkbox = "X";

    public Task(String name, TaskEnum type) {
        this.name = name;
        isCompleted = false;
        this.type = type;
    }

    public void setIsCompleted(boolean state) {
        isCompleted = state;
    }

    public static String getCheckbox(boolean checked) {
        return "[" + (checked ? checkbox : " ") + "]";
    }

    public static String getCheckbox(boolean checked, String marker) {
        return "[" + (checked ? marker : " ") + "]";
    }

    public String describe() {
        return getCheckbox(isCompleted) + " " + name;
    }
}
