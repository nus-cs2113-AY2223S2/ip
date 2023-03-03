package tasktypes;

public class Todo extends Task {

    public static final String TYPE_ICON = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }
}
