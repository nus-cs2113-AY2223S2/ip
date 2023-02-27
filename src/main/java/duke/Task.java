package duke;

public class Task {
    private String name;
    private boolean isDone;
    private char taskType;

    public boolean isIsDone() {
        return this.isDone;
    }

    public char getTaskType() {
        return this.taskType;
    }

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        if (this.isDone == true) {
            return " [T][X]" + this.name;
        } else {
            return " [T][ ]" + this.name;
        }
    }

    public void print() {
        if (this.isDone == false) {
            System.out.println("." + this.toString());
        } else {
            System.out.println("." + this.toString());
        }
    }
}
