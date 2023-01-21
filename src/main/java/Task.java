public class Task {
    private final String name;
    private boolean isCompleted;

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
}
