package duke.tasks;

public class Deadline extends Task {

    private String dueInfo;

    public Deadline(String description, String dueInfo) {
        super(description, "D");
        setDueInfo(dueInfo);
    }

    public String getDueInfo() {
        return dueInfo;
    }

    public void setDueInfo(String dueInfo) {
        this.dueInfo = dueInfo;
    }

}
