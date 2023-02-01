public class ToDo extends Task {
    ToDo(String name){
        super(name, TaskType.TODO);
    }
    @Override
    public String toString() {
        return super.getTaskPrefixWithName();
    }
}
