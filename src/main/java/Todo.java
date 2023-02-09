public class Todo extends Task {
    Todo(String taskName, int taskNumber) {
        super(taskName, taskNumber);
    }
    @Override
    public void printTaskType() {
        System.out.print("T");
    }

}
