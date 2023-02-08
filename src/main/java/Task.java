import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;
    static int numberOfTasks = 0;

    public static String[] parseCommand (String command) {
        String[] commandArray = command.split(" ");
        return Arrays.copyOfRange(commandArray, 1, commandArray.length);
    }

    public Task (String[] descriptionArray) {
        this.description = descriptionArray[0];
        this.isDone = false;
        numberOfTasks++;
    }

    public Task (String[] descriptionArray, boolean isDone) {
        this(descriptionArray);
        this.isDone = isDone;
    }

    public static String getTasksList(Task[] tasks){
        String tasksList = "";
        for (int i = 0; i < numberOfTasks; i++) {
            tasksList +=  String.format("%3d. ", (i+1)) + tasks[i].toString();
            if (i < numberOfTasks - 1) {
                tasksList += System.lineSeparator()+ "\t";
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
