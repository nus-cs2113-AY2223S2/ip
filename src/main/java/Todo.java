public class Todo extends Task {

    public Todo(boolean isDone, String description) {
        super(TypeOfTask.TODO, isDone, description);
    }

    /**
     * Returns the symbol for TODO task
     *
     * @return "T"
     */
    @Override
    public String getStatusForTypeOfTask() {
        return "T";
    }
}
