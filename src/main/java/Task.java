public class Task {
    protected String description;
    protected boolean isDone;

//    protected TaskType taskType;


    // constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
//        this.taskType = TaskType.TODO;

    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

//    public String getTaskTypeIcon() {
//
//        switch(this.taskType) {
//        case TODO:
//            return "T";
//        case DEADLINE:
//            return "D";
//        case EVENT:
//            return "E";
//        default:
//            return "T";
//        }
//
//    }
//
//    public enum TaskType {
//        TODO, DEADLINE, EVENT
//    }





}
