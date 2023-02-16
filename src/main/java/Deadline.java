public class Deadline extends Task {
    public String due;
    public String[] info;
    public String deadlineName;

    public Deadline(String description) {
        super(description);
        this.info = this.description.split("/by", 2);
        this.deadlineName = info[0];
        this.due = info[1];
    }

    @Override
    public String fileFormat() {
        return (String.format("D|%b|%s by %s", super.isDone, this.deadlineName, this.due));
    }

    @Override
    public String toString() {
        return ("[D][" + super.getStatusIcon() + "] " + this.deadlineName) + " (by:  " + this.due + ")";
    }
}
