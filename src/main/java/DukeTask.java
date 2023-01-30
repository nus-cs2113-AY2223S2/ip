import java.util.Optional;

public class DukeTask {
    protected String TaskName;
    protected boolean isDone;
    public DukeTask(String TaskName) {
        this.TaskName = TaskName;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        if(this.isDone) {
            System.out.println("[Warning] This task is already done!");
        }
        this.isDone = true;
    }
    public void unmarkAsDone() {
        if(!this.isDone) {
            System.out.println("[Warning] This task is not done yet!");
        }
        this.isDone = false;
    }
    public void printTask(){
        System.out.println("[T] [" + this.getStatusIcon() + "] " + this.TaskName);
    }
    public void printTask(int i) {
        System.out.println((i+1) + ".[T] [" + this.getStatusIcon() + "] " + this.TaskName);
    }
}
