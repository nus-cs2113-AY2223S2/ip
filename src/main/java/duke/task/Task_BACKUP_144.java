package duke.task;

import java.util.ArrayList;

public class Task {

    public String taskDescription;
    public String status;
    public String taskChar;
    public String formattedTask;

    public String formattedTask;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.status = "[ ]";
        System.out.print("Added: ");
    }

    public void setDone() {
        status = "[X]";
        print();
    }

    public void setUndone() {
        status = "[ ]";
        print();
    }

    public void print() {
        formattedTask = taskChar + status + " " + taskDescription;
        System.out.println(formattedTask);
<<<<<<< HEAD
    }

    public void delete(ArrayList<Task> taskList) {
        String taskWord;
        if (taskList.size() == 1) {
            taskWord = "task";
        } else {
            taskWord = "tasks";
        }
        System.out.println("Noted! I've removed this task:\n    " + formattedTask + "\nNow you have " + taskList.size() + " " + taskWord + " in your list.");
=======
>>>>>>> branch-Level-7
    }
}
