package tasks;

public class Todo extends Task {
//    protected boolean isDone;
//    protected String id;

    public Todo(String description) {
        super(description);
//        isDone = false;
//        id = "[T]";
    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setDone(boolean done) {
//        isDone = done;
//    }
//
//    public boolean isDone() {
//        return isDone;
//    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}