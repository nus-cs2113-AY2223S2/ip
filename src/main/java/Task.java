public class Task {
    protected String description;
    protected boolean isDone;
    static int numberOfTasks = 0;
    public Task (String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public Task (String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public static String getTasksList(Task[] tasks){
        String tasksList = "";
        for (int i = 0; i < numberOfTasks; i++) {
            tasksList +=  String.format("%3d. ", (i+1)) + tasks[i].toString();
            if (i < numberOfTasks - 1) {
                tasksList += "\n\t";
            }

        }
        return tasksList;
    }

    public void markAsDone () {
        this.isDone = true;
    }
    public void unmarkAsDone () {
    this.isDone = false;
    }
    public String getStatusIcon (){
        return this.isDone ? "X" : " ";
    }

    public String toString () {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.description;
    }



}
