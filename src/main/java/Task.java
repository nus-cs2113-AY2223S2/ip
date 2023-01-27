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

    public static String getListInputs (Task[] tasks){
        String listInputs = "";
        for (int i = 0; i < numberOfTasks; i++) {
            listInputs +=  String.format("%3d. ", (i+1)) + tasks[i].toString() + "\n\t";
        }
        return listInputs;
    }

    public void markAsDone () {
        this.isDone = true;
    }
    public void unmarkAsDone (){
        this.isDone = false;
    }
    public String getStatusIcon (){
        return this.isDone ? "X" : " ";
    }

    public String toString () {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.description;
    }



}
