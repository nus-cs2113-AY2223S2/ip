package duke.task;

public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
    }

    public String getDeadline() throws StringIndexOutOfBoundsException {
        return description.substring(description.indexOf("/by") + 4);
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask().substring(0, super.printTask().indexOf("/by")) + "(by: " + getDeadline() + ")\n";
    }

}
