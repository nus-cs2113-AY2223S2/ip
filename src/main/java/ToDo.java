public class ToDo extends Task {
    public static final String MARKER = "T";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe();
    }
}
