public class ToDo extends Tasks {
    public ToDo(String task) {
        super(task);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
