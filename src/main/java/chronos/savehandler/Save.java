package chronos.savehandler;

import chronos.tasktype.*;


/**
 * The Save class represents a saved task, broken down into its individual components.
 */
public class Save {
    //rough implementation: in this class the task to be saved will be broken down according to it's tags [T], [E] or [D]
    private String taskType;
    private String description;
    private String dueDate;
    private String start;
    private String end;
    private boolean isDone;

    /**
     * Constructs a Save object with the specified components.
     *
     * @param taskType    The type of task: [T] for Todo, [E] for Event, [D] for Deadline.
     * @param isDone      Indicates if the task is completed.
     * @param description The description of the task.
     * @param dueDate     The due date of the task. Null for Todo tasks.
     * @param start       The start date of the task. Null for Deadline tasks.
     * @param end         The end date of the task. Null for Deadline tasks.
     */
    public Save(String taskType, boolean isDone, String description, String dueDate, String start, String end) {
        this.taskType = taskType;
        this.isDone = isDone;
        this.description = description;
        this.dueDate = dueDate;
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a Save object into a Todo task.
     *
     * @return A new Todo task with the components from the Save object.
     */
    public Todo convertToTodo() {
        return new Todo(this.isDone, this.description);
    }

    /**
     * Converts a Save object into an Event task.
     *
     * @return A new Event task with the components from the Save object.
     */
    public Event convertToEvent() {
        return new Event(this.description, this.start, this.end, this.isDone);
    }

    /**
     * Converts a Save object into a Deadline task.
     *
     * @return A new Deadline task with the components from the Save object.
     */
    public Deadline convertToDeadline() {
        return new Deadline(this.description, this.dueDate, this.isDone);
    }

    /**
     * Converts the Save object into the appropriate task based on its taskType.
     *
     * @return A new Task object with the components from the Save object.
     * @throws RuntimeException If the taskType is not one of [T], [E], or [D].
     */
    public Task bucketConverter() {
        if (taskType.equals("[T]")) {
            return convertToTodo();
        } else if (taskType.equals("[E]")) {
            return convertToEvent();
        } else if (taskType.equals("[D]")) {
            return convertToDeadline();
        } else {
            throw new RuntimeException("Unknown Task Type");
        }
    }

}