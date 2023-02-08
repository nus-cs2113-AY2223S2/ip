package Duke;
public class DukeToDos extends DukeTasks{
    public DukeToDos(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return ("T");
    }
}
