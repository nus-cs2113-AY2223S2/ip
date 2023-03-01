package Alex.task;

public class Todo extends Task {

    public Todo(String description, String type) {
        super(description,type);

    }

    /**
     * Override to update standard printing format of a Todo Type
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
