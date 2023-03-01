package Arsdorint.task;

import java.time.LocalDate;

public class Task {
    // initial number of task must be set to 0
    public static final String VERTICAL_BAR = " | ";
    public static final String PARSE_LIMITER = "\\|";
    public static int numOfTasks = 0;
    public String description;
    public boolean isDone;
    public String taskType = "[ ]";
    public String taskName;
    public LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
    }

    public String toString() {
        return (this.taskType + this.getStatus() + " " + this.description);
    }

    //status is marked or not
    public String getStatus() {
        return (isDone) ? "[X]" : "[ ]";
    }
    public void printTask() {
        System.out.println(this.toString());
    }
    public int binaryRes() {
        return (isDone) ? 1 : 0;
    }
    public String toSave() {
        return (this.taskName + VERTICAL_BAR + binaryRes() + VERTICAL_BAR + this.description + "\n");
    }
    public boolean isDateNull() {
        return true;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    public void setIsDone(boolean status) {
        this.isDone = status;
    }
}

