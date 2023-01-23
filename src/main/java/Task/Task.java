package Task;

public class Task{
    private String description;
    public Task(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) {
        description = newDescription;
    }
    public String toString() {
        return description;
    }
}