package tasks;

public class Task {
    protected String name;
    protected Boolean isDone;


    public Task (String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    public String getType() {
        return "TASK";
    }
    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public String toString() {
        String checker = this.isDone ? "[X]" : "[ ]";
        return checker + " " + this.name;
    }

    public String getFileFormatString() {
        return String.format("%s | %d | %s", getType(), isDone ? 1 : 0, name);
    }
}
