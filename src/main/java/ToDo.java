public class ToDo extends Task {
    public ToDo(String[] descriptionArray) {
        super(descriptionArray);
    }

    @Override
    public String toString() {
        String todoPrefix = "[T]";
        String taskString = super.toString();
        return todoPrefix + taskString;
    }
}
