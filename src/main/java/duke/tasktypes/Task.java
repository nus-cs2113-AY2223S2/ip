package duke.tasktypes;

public abstract class Task {
    protected String content;
    private boolean isMarked;

    public Task(String content) {
        this.content = content;
        this.isMarked = false;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unMark() {
        this.isMarked = false;
    }

    public String getMarkingStatus() {
        return isMarked ? "[X]" : "[ ]";
    }

    public String getContent() {
        return this.content;
    }

    public String convertMarkingStatusToNumber() {
        if (this.getMarkingStatus().equals("[ ]")) {
            return "0";
        } else {
            return "1";
        }
    }

    public abstract String printTask();

    public abstract String putInputToDataFile();
}
