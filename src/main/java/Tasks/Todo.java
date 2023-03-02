package Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "[T]";
    }

    @Override
    public String getDescription() {
        return ("[T]" + getStatusIcon() + super.getDescription());
    }

    @Override
    public String formatTask() {
        String saveString = "T" + "|" + super.formatTask();
        return saveString;
    }
}
