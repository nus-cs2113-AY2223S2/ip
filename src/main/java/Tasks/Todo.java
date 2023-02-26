package Tasks;
public class Todo extends Task {
    
    public Todo (String description) {
        super(description);
    }

    @Override
    public String describeTask() {
        return "[T]" + super.describeTask();
    }

    @Override
    public String describeTaskForFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}