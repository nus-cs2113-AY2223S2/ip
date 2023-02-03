public class Task {
    protected final String description;

    public Task(String description) {
        this.description = description;
    }

    protected String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
