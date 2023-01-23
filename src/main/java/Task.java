public class Task {
    private String name;
    private Boolean isDone;


    public Task (String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String toString() {
        String checker = this.isDone ? "[X]" : "[ ]";
        return String.format("%s %s \n", checker, this.name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }
}
