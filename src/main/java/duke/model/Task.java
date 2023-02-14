package duke.model;

import duke.exception.InvalidCommandException;

import java.util.Arrays;

public class Task {
    public String taskName;
    protected boolean isDone;
    public static int numberOfTasks = 0;

    public Task (String[] descriptionArray) throws InvalidCommandException{
        if (descriptionArray.length < 1){
            throw new InvalidCommandException("Description of task cannot be empty!");
        }
        this.taskName = descriptionArray[0];
        this.isDone = false;
        numberOfTasks++;
    }

    public Task (String[] descriptionArray, boolean isDone) throws InvalidCommandException {
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
        return "[" + this.getStatusIcon() + "]" + "\t" + this.taskName;
    }



}
