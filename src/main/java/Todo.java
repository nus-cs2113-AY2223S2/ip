public class Todo extends Task{
    public Todo (String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String toString () {
        return "[T]" + super.toString();
    }
}
