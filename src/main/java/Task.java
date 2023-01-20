public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task() {
    }

    public String getStatusIcon(){
        return(isDone?"[X] ":"[ ] ");
    }


}
