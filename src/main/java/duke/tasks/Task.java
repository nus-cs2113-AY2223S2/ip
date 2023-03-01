package duke.tasks;
/**
 * Represents a task in Duke, from which different task types such as
 * Todo, Event and Deadline are derived.
 */
public class Task {
    public String name;
    public Boolean isCompleted;

    /**
     * Task constructor
     *
     * @param name        Name of task
     * @param isCompleted Completion status of task
     */
    public Task(String name, Boolean isCompleted){
        this.name = name;
        this.isCompleted = isCompleted;
    }
    public void setStatus(String action){
        if (action.equals("mark")){
            this.isCompleted = true;
        } else if (action.equals("unmark")) {
            this.isCompleted = false;
        }
    }

    public String toString(){
        String checkbox = "[ ]";
        if(isCompleted){
            checkbox = "[X]";
        }
        return  checkbox + " " + name;
    }

    public String toTextFileFormat(){
        return name + isCompleted;
    }
}
