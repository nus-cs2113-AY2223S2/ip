package Task;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    public static int numberOfTasks=0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        numberOfTasks++;
    }

    public static void decrementNumberOfTasks() {
        numberOfTasks--;
    }

    public static String printTasksList(ArrayList<Task> tasks) {
        String tasksList = new String();
        int count = 1;
        for (Task i : tasks) {
            tasksList += count + ". " + i.toString();
            if (count < numberOfTasks) {
                tasksList += System.lineSeparator();
            }
            count++;
        }
        return tasksList;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.getDescription();
    }

    public String toFileString() {
        return "Task";
    }

}