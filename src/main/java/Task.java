public class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected int taskNumber;



    public Task(String description) {
        this.taskName = description;
        this.isCompleted = false;
    }


    public void setTaskName(String name){
        this.taskName = name;

    }

    public String getTaskName(){
        return taskName;
    }


    public void printTask(){
        if (this.isCompleted){
            System.out.println( ".[T][X] " + this.taskName);
        }else {
            System.out.println( ".[T][ ] " + this.taskName);
        }

    }






    public String getStatusIcon() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public void markAsNotDone() {
        this.isCompleted = false;
    }



}
