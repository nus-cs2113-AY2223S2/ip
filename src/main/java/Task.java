public class Task {
    final String description;
    boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getLabel() {
        String typeIndicator = "[T]";
        String doneIndicator = "[" + (this.isDone ? "X" : " ") + "]";
        return typeIndicator + doneIndicator + " " + this.description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
