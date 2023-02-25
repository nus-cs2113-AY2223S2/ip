public class Task {
    protected String name;
    protected boolean isDone;

    public Task() {
    }

    public Task(String description) {
        this.name = description;
    }
    public Task(String description, boolean isDone) {
        this.name = description;
        this.isDone = isDone;
    }

    /**
     * Mark if a task is done and unmark if it is not done.
     * @return String containing status and name of task.
     */
    public String toString(){
        return(isDone?"[X] "+this.name:"[ ] "+this.name);
    }



}
