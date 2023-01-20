public class Task {

    private String task;
    private int number;
    private boolean isDone;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String checkDone() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String task, int number, boolean isDone) {
        this.task = task;
        this.number = number;
        this.isDone = isDone;
    }
}
