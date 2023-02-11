public class Todo extends Task {

    public Todo(String description) {
        super(description, TypeOfTask.TODO);
    }

    /**
     * Returns the symbol for TODO task
     * @return "T"
     */
    @Override
    public String getStatusForTypeOfTask() {
        return "T";
    }
}
