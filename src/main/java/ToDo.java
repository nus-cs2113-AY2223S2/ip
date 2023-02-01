public class ToDo extends Task {

    private char taskType;
    
    public ToDo(String description) {
        super(description);
        setTaskType();
    }

    public void setTaskType() {
        taskType = 'T';
    }

    public char getTaskType() {
        return taskType;
    }
}
