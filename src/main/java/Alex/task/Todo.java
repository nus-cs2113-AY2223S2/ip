package Alex.task;

public class Todo extends Task {

    public Todo(String description, String type) {
        super(description,type);

    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
