package FileUtils;

/**
 * Enum to store identifiers for the different types of task
 */
public enum TaskTypes {
    TODO("T"), 
    DEADLINE("D"), 
    EVENT("E");

    private String taskType;
    private TaskTypes(String taskType) {
        this.taskType = taskType;
    }
    
    @Override
    public String toString(){
        return taskType;
    }
}
