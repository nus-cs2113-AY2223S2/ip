package siri.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString(){
        return "T | " + super.toFileString();
    }
}