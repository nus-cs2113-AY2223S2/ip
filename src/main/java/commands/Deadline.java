package commands;
public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
    }

    private String date = this.description.split("/by")[1];
    private String task = this.description.split("/by")[0].split(" ",2)[1];

    @Override
    public String toString() {
        return ("[D]" + super.toString() + task + "(by:" + date + ")");
    }
}
