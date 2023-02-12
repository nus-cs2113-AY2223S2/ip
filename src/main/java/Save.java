import tasktype.*;
import java.io.*;


public class Save {
    //rough implementation: in this class the task to be saved will be broken down according to it's tags [T], [E] or [D]
    private String taskType;
    private String description;
    private String dueDate;
    private String start;
    private String end;
    private boolean isDone;
    public Save(String taskType, boolean isDone, String description, String dueDate, String start, String end) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.description = description;
        this.dueDate = dueDate;
        this.start = start;
        this.end = end;
    }
}