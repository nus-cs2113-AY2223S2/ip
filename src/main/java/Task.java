public class Task {
    private final String name;
    private boolean isCompleted;
    private static final String checkbox = "X";

    public Task(String name) {
        this.name = name;
        isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public boolean getIsCompleted() {
        return isCompleted;
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
