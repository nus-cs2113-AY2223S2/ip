package task;
public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
    }

    private String getDate() {
        return this.description.split("/by")[1];
    }
    private String getTask() {
        return this.description.split("/by")[0].split(" ",2)[1];
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + getTask() + "(by:" + getDate() + ")");
    }
}
