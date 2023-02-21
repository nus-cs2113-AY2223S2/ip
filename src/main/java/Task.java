/**
 * This class represents a task.
 * It contains the task name and the status of the task.
 * It also contains methods to mark the task as done and not done.
 * It also contains a toString method to print the status of the task followed by the task name.
 * It is the parent class of the Todo, Deadline and Event classes that inherits from it.
 * @param taskName the name of the task
 * @param isDone the status of the task
 * @param markAsDone method to mark the task as done
 * @param markAsNotDone method to mark the task as not done
 * @param toString method to print the status of the task followed by the task name
 * 
 */

public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * This constructor takes in the task name.
     * @param taskName the name of the task
     */
    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * This method marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * This method marks the task as not done.
     */
    public void markAsNotDone(){
        this.isDone = false;
    }

    /**
     * This method prints the status of the task followed by the task name.
     * @return the status of the task followed by the task name
     */
    public String toString(){
        if(this.isDone){
            return "[X] " + this.taskName;
        }else{
            return "[ ] " + this.taskName;
        }
    }
}

/**
 * This class represents a todo task.
 * It is a subclass of the Task class.
 * It contains the constructor to create a todo task.
 * It also contains a toString method to print the status of the task followed by the task name.
 * @param taskName the name of the task
 */
class Todo extends Task {
    protected Todo(String taskName){
        super(taskName);
    }

    /**
     * This method prints the status of the task followed by the task name.
     * @return the status of the task followed by the task name
     */
    @Override
    public String toString(){
        if(this.isDone){
            return "[T][X] " + this.taskName;
        }else{
            return "[T][ ] " + this.taskName;
        }
    }
}

/**
 * This class represents a deadline task.
 * It is a subclass of the Task class.
 * It contains the constructor to create a deadline task.
 * It also contains a toString method to print the status of the task followed by the task name.
 * @param taskName the name of the task
 * @param deadline the deadline of the task
 */
class Deadline extends Task {
    protected String deadline;
    public Deadline(String taskName, String deadline){
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * This method prints the status of the task followed by the task name.
     * @return the status of the task followed by the task name
     */
    @Override
    public String toString(){
        if(this.isDone){
            return "[D][X] " + this.taskName + " (" + this.deadline + ")";
        }else{
            return "[D][ ] " + this.taskName + " (" + this.deadline + ")";
        }
    }
}

/**
 * This class represents an event task.
 * It is a subclass of the Task class.
 * It contains the constructor to create an event task.
 * It also contains a toString method to print the status of the task followed by the task name.
 * @param taskName the name of the task
 * @param eventTime the time of the event
 */
class Event extends Task {
    protected String eventTime;
    public Event(String taskName, String eventTime){
        super(taskName);
        this.eventTime = eventTime;
    }

    /**
     * This method prints the status of the task followed by the task name.
     * @return the status of the task followed by the task name
     */
    @Override
    public String toString(){
        if(this.isDone){
            return "[E][X] " + this.taskName + " (" + this.eventTime + ")";
        }else{
            return "[E][ ] " + this.taskName + " (" + this.eventTime + ")";
        }
    }
}
