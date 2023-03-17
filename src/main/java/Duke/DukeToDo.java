package Duke;

/**
 * DukeToDo class extending DukeTask
 */
public class DukeToDo extends DukeTask{

    /**
     * Constructor for DukeToDo class
     * 
     * @param name String name of the task
     */
    public DukeToDo(String name) {
        setName(name);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
    
}
