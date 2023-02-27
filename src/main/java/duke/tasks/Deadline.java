package duke.tasks;

public class Deadline extends Task {

    protected String type = "deadline";
    protected String end;
    public Deadline(String description, String end)
    {
        super(description);
        this.end = end;
    }

    @Override
    public void printTask() {
        System.out.println("[D][" + getStatusIcon() + "] " + description + "(by:" + end + ")");
    }
}