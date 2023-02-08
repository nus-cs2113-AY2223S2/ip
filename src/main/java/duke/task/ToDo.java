package duke.task;

public class ToDo extends Task {
    public ToDo(String name){
        super(name, TaskType.TODO);
    }
    @Override
    public String toString() {
        return super.getTaskPrefixWithName();
    }
}
