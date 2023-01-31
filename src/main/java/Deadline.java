public class Deadline extends Task{
    public Deadline(String description) {
        super(description);
    }

    public String getDdl() {
        return description.substring(description.indexOf('/') + 4);
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask().substring(0, super.printTask().indexOf('/')) + "(by: " + getDdl() + ") \n";
    }

}
