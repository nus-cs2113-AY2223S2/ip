package Onandon.module;

// Class for the 'todo' command.
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + super.description;
    }
}