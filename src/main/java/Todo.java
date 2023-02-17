public class Todo extends Task {
    Todo(String taskName) {
        super(taskName);
    }
    @Override
    public void printTaskType() {
        System.out.print("T");
    }

}
