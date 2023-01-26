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

    public String toString(){
        return(isDone?"[X] "+this.name:"[ ] "+this.name);
    }



}
