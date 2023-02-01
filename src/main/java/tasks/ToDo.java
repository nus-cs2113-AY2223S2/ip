package tasks;

public class ToDo extends Task {

    public ToDo(String description){
        super(description);
    }
    public String getTaskSymbol() {
        // T for tasks.ToDo
        return "T";
    }
}
