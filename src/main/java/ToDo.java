public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String todoPrefix = "[T]";
        String taskString = super.toString();
        return todoPrefix + taskString;
    }
}
