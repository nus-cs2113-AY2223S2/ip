package chronos.savehandler;

import chronos.tasktype.*;


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

    public Todo convertToTodo() {
        return new Todo(this.isDone, this.description);
    }

    public Event convertToEvent() {
        return new Event(this.description, this.start, this.end, this.isDone);
    }

    public Deadline convertToDeadline() {
        return new Deadline(this.description, this.dueDate, this.isDone);
    }

    public Task bucketConverter() {
        if (taskType.equals("[T]")){
           return convertToTodo();
        }
        else if (taskType.equals("[E]")){
            return convertToEvent();
        }
        else if (taskType.equals("[D]")){
           return convertToDeadline();
        }
        else {
            throw new RuntimeException("Unknown Task Type");
        }
    }

}