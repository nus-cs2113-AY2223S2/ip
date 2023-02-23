package Duke;
public class DukeToDos extends DukeTasks{
    /**
     * Creates a new todo task recorded by Duke and sets its marked status to false. 
     * 
     * @param description Description of the task
     */
    public DukeToDos(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return ("T");
    }
}
