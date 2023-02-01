public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString(){
        String task_status;

        if (isDone()){
            task_status = "X";
        }
        else{
            task_status = " ";
        }

        String result = "[" + task_status + "] " + description;
        return result;
    }

}